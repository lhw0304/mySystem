package com.mode.service;

import org.springframework.stereotype.Service;

import com.mode.entity.HttpResponseEntity;

/**
 * Created by Lei on 3/21/16.
 */
@Service
public class ConfigurationServiceTest extends BaseService{

    public static void configService(HttpResponseEntity entity) throws
            Exception {
        // Check the status code
        successResponseStatusCodeCheck(entity.getStatus());
    }
}
