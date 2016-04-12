package com.mode.service;

import com.mode.entity.HttpResponseEntity;

/**
 * Created by Lei on 3/21/16.
 */
public class LoggingServiceTest extends BaseService {

    public static void loggingService(HttpResponseEntity entity) {
        successResponseStatusCodeCheck(entity.getStatus());
    }
}
