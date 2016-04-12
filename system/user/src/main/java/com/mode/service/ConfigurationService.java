package com.mode.service;

import com.mode.base.Response;

/**
 * Created by Lei on 3/24/16.
 */
public interface ConfigurationService {

    /**
     * List configs for the users.
     *
     * @param userId
     * @param type
     * @param country
     * @param limit
     * @param offset
     * @return
     */
    public Response config(Integer userId, String type, String country, Integer limit, Integer
            offset);
}
