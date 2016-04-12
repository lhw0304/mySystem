package com.mode.service;

import com.mode.entity.SystemLog;

/**
 * Created by zhaoweiwei on 15/12/9.
 */
public interface LogService {

    public void createSystemLog(SystemLog systemLog);

    public void createSystemLog(String module, String function, Integer userId, String
            attributeName, String attributeValue, String status, String extraValue);

}
