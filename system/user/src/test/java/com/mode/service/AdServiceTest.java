package com.mode.service;

import com.mode.entity.HttpResponseEntity;

/**
 * Created by Lei on 3/22/16.
 */
public class AdServiceTest extends BaseService {

    public static void listAdsService(HttpResponseEntity entity) {
        successResponseStatusCodeCheck(entity.getStatus());
    }
}
