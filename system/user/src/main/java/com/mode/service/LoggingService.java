package com.mode.service;

import com.mode.base.Response;
import com.mode.entity.UserActionLog;

/**
 * Created by Lei on 3/24/16.
 */
public interface LoggingService {

    /**
     * Inert logging record to the server
     *
     * @param userActionLogs
     * @return
     */
    public Response logging(UserActionLog[] userActionLogs);
}
