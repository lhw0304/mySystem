package com.mode.service;

import com.mode.base.Response;

/**
 * Created by Lei on 3/24/16.
 */
public interface AdService {

    /**
     * Get a list of ads
     *
     * @param displayPage
     * @param country
     * @param limit
     * @param offset
     * @return
     */
    public Response listAds(String displayPage, String country, Integer limit, Integer offset);
}
