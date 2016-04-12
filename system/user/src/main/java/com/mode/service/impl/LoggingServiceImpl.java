package com.mode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mode.base.BaseConfig;
import com.mode.base.Message;
import com.mode.base.Response;
import com.mode.dao.UserActionLogDAO;
import com.mode.entity.UserActionLog;
import com.mode.exception.ModeException;
import com.mode.service.LogService;
import com.mode.service.LoggingService;

/**
 * Created by Lei on 3/24/16.
 */
@Service
public class LoggingServiceImpl implements LoggingService {

    @Autowired
    private LogService logService;

    @Autowired
    private ObjectMapper om;

    /**
     * Inert logging record to the server
     *
     * @param userActionLogs
     * @return
     */
    @Override
    public Response logging(UserActionLog[] userActionLogs) {

        try {
            logService.logBatch(om.writeValueAsString(userActionLogs));
        } catch (Exception e){
            throw new ModeException(Message.LOG_FAILURE);
        }

        Response res = new Response(Message.SUCCESS);
        return res;
    }
}
