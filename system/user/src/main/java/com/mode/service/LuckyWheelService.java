package com.mode.service;

import com.mode.base.Response;

/**
 * Created by Lei on 3/23/16.
 */
public interface LuckyWheelService {

    /**
     * Get a list of lucky wheel items
     *
     * @param country
     * @return
     */
    public Response listLuckyWheelItems(String country);

    /**
     * User spin the lucky wheel and get the reward.
     *
     * @param userId
     * @param country
     * @return
     */
    public Response spinLuckyWheel(Integer userId, String country);
}
