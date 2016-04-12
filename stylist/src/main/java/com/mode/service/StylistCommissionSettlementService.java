package com.mode.service;

import org.apache.ibatis.annotations.Param;

import com.mode.config.Response;

/**
 * Created by zhaoweiwei on 15/11/27.
 */
public interface StylistCommissionSettlementService {

    public Response withdraw(Integer stylistId, Float usd) throws Exception;

    public void settlement(Integer stylistId) throws Exception;

    public Response listSettlementsByStylistId(Integer stylistId, String type, Integer status,
                                               Integer limit, Integer offset);

}
