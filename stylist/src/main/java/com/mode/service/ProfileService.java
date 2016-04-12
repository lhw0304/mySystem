package com.mode.service;

import org.springframework.web.multipart.MultipartFile;

import com.mode.config.Response;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface ProfileService {

    /**
     * Given a userId, return his/her profile
     *
     * @param userId
     * @return
     */
    public Response getProfileByUserId(Integer userId);

    /**
     * Upload avatar image.
     *
     * @param avatar
     * @param userId
     * @return
     */
    public Response uploadAvatar(MultipartFile avatar, Integer userId);

    /**
     * Update the profile's nickname and description
     *
     * @param userId
     * @return
     */
    public Response updateProfile(Integer userId, MultipartHttpServletRequest mRequest);
}