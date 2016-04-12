package com.mode.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mode.base.Message;
import com.mode.base.Response;
import com.mode.entity.Config;
import com.mode.service.ConfigurationService;

/**
 * Created by Lei on 3/24/16.
 */
@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    @Override
    public Response config(Integer userId, String type, String country, Integer limit, Integer
            offset) {
        List<Config> configs = new ArrayList<>();

        Response res = new Response(Message.SUCCESS);
        res.setPayload(configs);

        return res;
    }
}
