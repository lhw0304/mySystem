package com.mode.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mode.base.BaseConfig;
import com.mode.base.Message;
import com.mode.base.Response;
import com.mode.dao.AppsflyerDAO;
import com.mode.dao.UserActionLogDAO;
import com.mode.dao.UserLoginLogDAO;
import com.mode.dao.UserShoppingLogDAO;
import com.mode.entity.UserActionLog;
import com.mode.entity.UserLoginLog;
import com.mode.entity.UserShoppingLog;
import com.mode.exception.ModeException;
import com.mode.service.LogService;

/**
 * Created by zhaoweiwei on 16/3/24.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserActionLogDAO userActionLogDAO;

    @Autowired
    private UserLoginLogDAO userLoginLogDAO;

    @Autowired
    private UserShoppingLogDAO userShoppingLogDAO;

    @Autowired
    private AppsflyerDAO appsflyerDAO;

    @Override
    public Response log(String type, String jsonLog) throws IOException {
        long now = System.currentTimeMillis();
        if (BaseConfig.LOG_TYPE_USER_ACTION.equalsIgnoreCase(type)) {
            UserActionLog userActionLog =
                    objectMapper.readValue(jsonLog, UserActionLog.class);
            if (userActionLog.getCtime() == null) {
                userActionLog.setCtime(now);
            }
            userActionLogDAO.createUserActionLog(userActionLog);
        } else if (BaseConfig.LOG_TYPE_USER_LOGIN.equalsIgnoreCase(type)) {
            UserLoginLog userLoginLog =
                    objectMapper.readValue(jsonLog, UserLoginLog.class);
            if (userLoginLog.getCtime() == null) {
                userLoginLog.setCtime(now);
            }
            userLoginLogDAO.createUserLoginLog(userLoginLog);
        } else if (BaseConfig.LOG_TYPE_USER_SHOPPING.equalsIgnoreCase(type)) {
            UserShoppingLog userShoppingLog =
                    objectMapper.readValue(jsonLog, UserShoppingLog.class);
            userShoppingLogDAO.createUserShoppingLog(userShoppingLog);
        } else {
            throw new ModeException(Message.INVALID_PARAMETER);
        }

        return new Response(Message.SUCCESS);
    }

    /**
     * Only used for batch logging user actions.
     *
     * @param jsonLogs
     * @return
     */
    @Override
    public Response logBatch(String jsonLogs) throws IOException {
        long now = System.currentTimeMillis();
        UserActionLog[] userActionLogs =
                objectMapper.readValue(jsonLogs, UserActionLog[].class);
        for (UserActionLog userActionLog : userActionLogs) {
            if (userActionLog.getCtime() == null)
                userActionLog.setCtime(now);
        }
        userActionLogDAO.createUserActionLogBatch(userActionLogs);

        return new Response(Message.SUCCESS);
    }
}
