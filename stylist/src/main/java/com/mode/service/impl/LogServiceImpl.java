package com.mode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mode.dao.SystemLogDAO;
import com.mode.entity.SystemLog;
import com.mode.service.LogService;

/**
 * Created by zhaoweiwei on 15/12/9.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private SystemLogDAO systemLogDAO;

    @Override
    public void createSystemLog(SystemLog systemLog) {
        if (systemLog.getCtime() == null)
            systemLog.setCtime(System.currentTimeMillis());
        systemLogDAO.createSystemLog(systemLog);
    }

    @Override
    public void createSystemLog(String module, String function, Integer userId,
                                String attributeName, String attributeValue,
                                String status, String extraValue) {
        SystemLog systemLog = new SystemLog();
        systemLog.setModule(module);
        systemLog.setFunction(function);
        systemLog.setUserId(userId);
        systemLog.setAttributeName(attributeName);
        systemLog.setAttributeValue(attributeValue);
        systemLog.setStatus(status);
        systemLog.setExtraValue(extraValue);
        createSystemLog(systemLog);
    }
}
