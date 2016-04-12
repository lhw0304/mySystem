package com.mode.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mode.base.Message;
import com.mode.base.Response;
import com.mode.entity.Ad;
import com.mode.service.AdService;

/**
 * Created by Lei on 3/24/16.
 */
@Service
public class AdServiceImpl implements AdService {

    @Override
    public Response listAds(String displayPage, String country, Integer limit, Integer offset) {
        List<Ad> ads = new ArrayList<>();

        Response res = new Response(Message.SUCCESS);
        res.setPayload(ads);

        return res;
    }
}
