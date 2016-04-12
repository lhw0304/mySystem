package com.mode.service;

import com.mode.base.Response;

/**
 * Created by Lei on 3/23/16.
 */
public interface CreditZoneService {

    /**
     * Get a list of credit zone items
     *
     * @param limit
     * @param offset
     * @param country
     * @return
     */
    public Response listCreditZoneItems(Integer limit, Integer offset, String country);

    /**
     * Redeem item in credit zone
     *
     * @param userId
     * @param creditzonesId
     * @param costCredit
     * @param usd
     * @return
     */
    public Response redeemItemInCreditZone(Integer userId, Integer creditzonesId, Integer
            costCredit, Integer usd);
}
