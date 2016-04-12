package com.mode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mode.base.BaseConfig;
import com.mode.base.Response;
import com.mode.dao.UserPrizeDAO;
import com.mode.entity.CreditZone;
import com.mode.entity.HttpResponseEntity;
import com.mode.entity.UserPrize;
import com.mode.exception.ModeTestException;

/**
 * Created by Lei on 3/21/16.
 */
@Service
public class CreditZoneServiceTest extends BaseService{

    @Autowired
    private UserPrizeDAO userPrizeDAO;

    public static void listCreditZoneItemsService(HttpResponseEntity entity){
        successResponseStatusCodeCheck(entity.getStatus());
    }

    public void redeemItemInCreditZoneService(HttpResponseEntity entity, CreditZone creditZone,
                                              Integer userId){
        /* Check if the request/response is successful with 200 status code.
           And check if the result is what we expected. */
        Response res = parseResponseBody(entity.getBody());

        // Check if the status code and message are correct.
        successResponseStatusCodeCheck(entity.getStatus());
        successResponseMessageCheck(res.getCode(), res.getMessage());

        // Check if the redeem prize is stored to the user and check if the log was recorded.
        UserPrize userPrize = new UserPrize();
        userPrize.setUserId(userId);
        userPrize.setSourceId(creditZone.getId());
        userPrize.setType(BaseConfig.USER_PRICE_TYPE_REDEEM);
        userPrize = userPrizeDAO.getUserPrize(userPrize);

        if(userPrize.getId() == null){
            throw new ModeTestException("The redeem item is not sent to the user.");
        }
    }
}
