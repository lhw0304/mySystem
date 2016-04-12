package com.mode.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mode.config.AppConfig;
import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.dao.StylistCommissionSettlementDAO;
import com.mode.dao.StylistCommissionStatsDAO;
import com.mode.dao.ProfileDAO;
import com.mode.entity.StylistCommissionSettlement;
import com.mode.entity.Profile;
import com.mode.service.StylistCommissionSettlementService;

/**
 * Created by zhaoweiwei on 15/11/27.
 */
@Service
public class StylistCommissionSettlementServiceImpl implements StylistCommissionSettlementService {

    @Autowired
    private StylistCommissionSettlementDAO stylistCommissionSettlementDAO;

    @Autowired
    private StylistCommissionStatsDAO stylistCommissionStatsDAO;

    @Autowired
    private ProfileDAO profileDAO;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Response withdraw(Integer stylistId, Float usd) throws Exception {
        Response res = new Response();
        try {
            long now = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            if (usd != null && usd > 0) {
                Float balance = profileDAO.getUSDByUserId(stylistId);
                if (balance == null || usd > balance) {
                    res.setMessage(Message.NO_MORE_BLANCE);
                    return res;
                }
                StylistCommissionSettlement stylistCommissionSettlement = new
                        StylistCommissionSettlement();
                stylistCommissionSettlement.setDate(Integer.parseInt(sdf.format(now)));
                stylistCommissionSettlement.setStylistId(stylistId);
                stylistCommissionSettlement.setType(AppConfig.STYLIST_COMMISSION_TYPE_WITHDRAW);
                stylistCommissionSettlement.setUsd(-usd);
                stylistCommissionSettlement.setBalance(balance - usd);
                stylistCommissionSettlement.setCtime(now);
                stylistCommissionSettlement.setStatus(0);
                stylistCommissionSettlementDAO.createStylistCommissionSettlement
                        (stylistCommissionSettlement);
                Profile profile = new Profile();
                profile.setUserId(stylistId);
                profile.setUsd(balance - usd);
                profileDAO.updateProfile(profile);
            }
        } catch (Exception e) {
            throw e;
        }
        res.setMessage(Message.SUCCESS);
        return res;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void settlement(Integer stylistId) throws Exception {
        try {
            long now = System.currentTimeMillis();
            Integer endDate = stylistCommissionStatsDAO.
                    getLatestStatsDateByStylistId(stylistId);
            Float usd = null;
            if (endDate != null) {
                Integer date = stylistCommissionSettlementDAO.
                        getLatestSettlementsDateByStylistId
                                (stylistId, AppConfig.STYLIST_COMMISSION_TYPE_SETTLEMENT);
                Float balance = profileDAO.getUSDByUserId(stylistId);
                if (date == null) {
                    usd = stylistCommissionStatsDAO.getUsdInRange(stylistId, 0, endDate);
                } else {
                    usd = stylistCommissionStatsDAO.getUsdInRange(stylistId, date, endDate);
                }

                if (usd != null && usd > 0) {
                    if (balance != null)
                        balance = balance + usd;
                    else
                        balance = usd;
                    StylistCommissionSettlement stylistCommissionSettlement = new
                            StylistCommissionSettlement();
                    stylistCommissionSettlement.setDate(endDate);
                    stylistCommissionSettlement.setStylistId(stylistId);
                    stylistCommissionSettlement.setType(AppConfig.STYLIST_COMMISSION_TYPE_SETTLEMENT);
                    stylistCommissionSettlement.setUsd(usd);
                    stylistCommissionSettlement.setBalance(balance);
                    stylistCommissionSettlement.setCtime(now);
                    stylistCommissionSettlementDAO.createStylistCommissionSettlement
                            (stylistCommissionSettlement);
                    Profile profile = new Profile();
                    profile.setUserId(stylistId);
                    profile.setUsd(balance);
                    profileDAO.updateProfile(profile);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Response listSettlementsByStylistId(Integer stylistId, String type, Integer status,
                                               Integer limit, Integer offset) {
        Response res = new Response();
        try {
            List<StylistCommissionSettlement> list = stylistCommissionSettlementDAO.listSettlementsByStylistId
                    (stylistId, type, status, limit, offset);
            Float balance = profileDAO.getProfileByUserId(stylistId).getUsd();
            if (list == null || list.isEmpty()) {
                res.setMessage(Message.NO_MORE_RECORD);
                return res;
            }

            Integer total = stylistCommissionSettlementDAO.getSettlementCountByStylistId
                    (stylistId, type, null);

            Map<String, Object> payload = new HashMap<String, Object>();
            payload.put("balance",balance);
            payload.put("list", list);
            payload.put("total", total);
            res.setMessage(Message.SUCCESS);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }
        return res;
    }
}
