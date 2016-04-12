package com.mode.dao;

import org.apache.ibatis.annotations.Insert;

import com.mode.entity.ApiLog;


/**
 * Created by zhaoweiwei on 16/3/18.
 */
public interface ApiLogDAO {

    /**
     * Create apiLog
     *
     * @param apiLog
     * @return
     */
    @Insert("INSERT INTO md_api_log (api, httpMethod, httpStatus, ip, userId, " +
            "source, ctime) " +
            "VALUES (#{api}, #{http_method}, #{http_status}, #{ip}, #{user_id}, " +
            "#{source}, #{ctime})")
    public Integer createApiLog(ApiLog apiLog);

}
