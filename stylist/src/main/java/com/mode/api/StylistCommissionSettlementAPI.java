package com.mode.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mode.config.AppConfig;
import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.service.StylistCommissionSettlementService;
import com.mode.service.LogService;
import com.mode.util.UserLockGenerator;

/**
 * Created by zhaoweiwei on 15/11/28.
 */
@RestController
@RequestMapping("/v2")
public class StylistCommissionSettlementAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<Integer, Object> locks = new HashMap<Integer, Object>();

    @Autowired
    private StylistCommissionSettlementService stylistCommissionSettlementService;

    @Autowired
    private LogService logService;

    /**
     * @api {POST} /v2/withdraw stylist withdraw money
     * @apiName withdraw
     * @apiGroup StylistCommissionSettlement
     * @apiParam {Number} stylistId Stylist's unique id
     * @apiParam {Number} usd The number of the money
     * @apiHeader {String} X-Auth-Token Access token for authentication
     * @apiVersion 3.1.0
     * @apiDescription This API is used to withdraw money for stylists
     * @apiSuccessExample {json} Success 200:
     * {
     * "message": {
     * "code": 0,
     * "description": "Operation succeeded"
     * },
     * "payload": null
     * }
     * @apiErrorExample {json} Error:
     * {
     * "message": {
     * "code": 1,
     * "description": "Operation Failed"
     * },
     * "payload": null
     * }
     */
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public Response withdraw(@RequestParam(value = "stylistId") Integer stylistId,
                             @RequestParam(value = "usd") Float usd) {
        Response res = new Response();
        Object lock = UserLockGenerator.getInstance().getLock(stylistId);
        if (lock != null) {
            synchronized (lock) {
                try {
                    res = stylistCommissionSettlementService.withdraw(stylistId, usd);
                } catch (Exception e) {
                    e.printStackTrace();
                    logService.createSystemLog("stylist", "withdraw",
                            stylistId, null, null, "failed", e.getMessage());
                    res.setMessage(Message.FAILURE);
                    return res;
                }
            }
        } else {
            res.setMessage(Message.FAILURE);
        }

        logger.info("/v2/withdraw?stylistId={}&usd={}, {}", stylistId, usd, res.getMessage());
        return res;
    }

    /**
     * @api {POST} /v2/settlement Stylist settlement money
     * @apiName settlement
     * @apiGroup StylistCommissionSettlement
     * @apiParam {Number} stylistId Stylist's unique id
     * @apiHeader {String} X-Auth-Token Access token for authentication
     * @apiVersion 3.1.0
     * @apiDescription This API is used to settlement money for stylists
     * @apiSuccessExample {json} Success 200:
     * {
     * "message": {
     * "code": 0,
     * "description": "Operation succeeded"
     * },
     * "payload": null
     * }
     * @apiErrorExample {json} Error:
     * {
     * "message": {
     * "code": 1,
     * "description": "Operation Failed"
     * },
     * "payload": null
     * }
     */
//    @RequestMapping(value = "/settlement", method = RequestMethod.POST)
//    public Response settlement(@RequestParam(value = "stylistId") Integer stylistId) {
//        Response res = new Response();
//        Object lock = getLock(stylistId);
//        if (lock != null) {
//            synchronized (lock) {
//                try {
//                    res = stylistCommissionSettlementService.settlement(stylistId);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    res.setMessage(Message.FAILURE);
//                    return res;
//                }
//            }
//        } else {
//            res.setMessage(Message.FAILURE);
//        }
//        logger.info("/v2/settlement?stylistId={}, {}", stylistId, res.getMessage());
//        return res;
//    }

    @RequestMapping(value = "/listCommissionSettlement", method = RequestMethod.GET)
    public Response stylistCommissionSettlementServiceImpl(@RequestParam(value = "stylistId",
                                                                   required = false)
                                                           Integer stylistId,
                                                           @RequestParam(value = "type",
                                                                   required = false) String type,
                                                           @RequestParam(value = "status",
                                                                   required = false) Integer status,
                                                           @RequestParam(value = "limit",
                                                                   required = false) Integer limit,
                                                           @RequestParam(value = "offset",
                                                                   required = false)
                                                           Integer offset) {
        Response res = stylistCommissionSettlementService.listSettlementsByStylistId(stylistId,
                type, status, limit, offset);
        logger.info("/v2/listCommissionSettlement?stylistId={}&type={}&status={}&limit={}&offset" +
                        "={}, {}",
                stylistId, type, status, limit, offset, res.getMessage());
        return res;
    }

}
