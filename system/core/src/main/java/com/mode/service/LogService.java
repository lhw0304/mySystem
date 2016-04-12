package com.mode.service;

import java.io.IOException;

import com.mode.base.Response;

/**
 * Created by zhaoweiwei on 16/3/24.
 */
public interface LogService {

    public Response log(String type, String jsonLog) throws IOException;

    public Response logBatch(String jsonLogs) throws IOException;

}