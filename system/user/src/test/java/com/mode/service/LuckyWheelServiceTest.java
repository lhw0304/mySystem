package com.mode.service;

import com.mode.entity.HttpResponseEntity;

/**
 * Created by Lei on 3/21/16.
 */
public class LuckyWheelServiceTest extends BaseService {

    public static void listLuckyWheelItemsService(HttpResponseEntity entity){
        successResponseStatusCodeCheck(entity.getStatus());
    }

    public static void spinLuckyWheelService(HttpResponseEntity entity){
        successResponseStatusCodeCheck(entity.getStatus());
    }
}
