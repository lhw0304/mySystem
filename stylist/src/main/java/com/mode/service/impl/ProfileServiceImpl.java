package com.mode.service.impl;

import com.mode.config.AppConfig;
import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.dao.ProfileAddressDAO;
import com.mode.dao.ProfileDAO;
import com.mode.entity.Profile;
import com.mode.entity.ProfileAddress;
import com.mode.service.ProfileService;
import com.mode.util.ImageHandler;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileDAO profileDAO;

    @Autowired
    private ProfileAddressDAO profileAddressDAO;

    /**
     * Given a userId, return his/her profile plus
     *
     * @param userId
     * @return
     */
    public Response getProfileByUserId(Integer userId) {
        Response res = new Response();

        try {
            Profile profile = profileDAO.getProfileByUserId(userId);
            if (profile == null) {
                res.setMessage(Message.PROFILE_NOT_EXIST);
                return res;
            }
            ProfileAddress profileAddress = profileAddressDAO.getProfileAddress(userId);

            // Add profile to the response.
            res.setMessage(Message.SUCCESS);
            Map<String, Object> payload = new HashMap<String, Object>();
            payload.put("profile", profile);
            payload.put("address",profileAddress);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }

        return res;
    }

    /**
     * Upload the avatar image.
     *
     * @param avatar
     * @param userId
     * @return
     */
    public Response uploadAvatar(MultipartFile avatar, Integer userId) {
        Response res = new Response();
        try {
            ImageHandler imageHandler = new ImageHandler();

            String avatarURL = imageHandler.getImageURL(avatar, AppConfig.FOLDER_AVATAR, 0, 0, 0,
                    0);

            if (avatarURL == null) {
                res.setMessage(Message.FAILURE);
                return res;
            }

            final Long now = System.currentTimeMillis();

            Profile profile = new Profile();
            profile.setUserId(userId);
            profile.setAvatar(avatarURL);
            profile.setUtime(now);
            if (profileDAO.updateProfile(profile) == 0) {
                res.setMessage(Message.DATABASE);
            } else {
                res.setMessage(Message.SUCCESS);
                Map<String, String> payload = new HashMap<String, String>();
                payload.put("imageURL", avatarURL);
                res.setPayload(payload);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }
        return res;
    }

    /**
     * Update the profile's nickname and description
     *
     * @param userId
     * @return
     */
    public Response updateProfile(Integer userId, MultipartHttpServletRequest mRequest) {
        Response res = new Response();

        try {
            final Long now = System.currentTimeMillis();
            String nickname = mRequest.getParameter("nickname");
            String description = mRequest.getParameter("description");
            String paypal = mRequest.getParameter("paypal");
            String country = mRequest.getParameter("country");
            String address = mRequest.getParameter("address");
            String phone = mRequest.getParameter("phone");
            Profile p = new Profile();
            p.setUtime(now);
            p.setNickname(nickname);
            p.setDescription(description);
            p.setUserId(userId);
            p.setPaypal(paypal);

            if (profileDAO.updateProfile(p) == 0) {
                res.setMessage(Message.FAILURE);
            } else {
                res.setMessage(Message.SUCCESS);
            }
            ProfileAddress profileAddress = new ProfileAddress();
            profileAddress.setUserId(userId);
            profileAddress.setCountry(country);
            profileAddress.setAddress(address);
            profileAddress.setPhone(phone);
            if (profileAddressDAO.updateProfileAddress(profileAddress) == 0) {
                res.setMessage(Message.FAILURE);
            } else {
                res.setMessage(Message.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.DATABASE);
        }

        return res;
    }
}