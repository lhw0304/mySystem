package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.UserLoginLog;

/**
 * Created by zhaoweiwei on 16/3/21.
 */
public interface UserLoginLogDAO {

    /**
     * Create userLoginLog
     *
     * @param userLoginLog
     * @return
     */
    @Insert("INSERT INTO md_user_login_log (user_id, action, device_type, phone_number, " +
            "phone_name, device_token, system_version, app_version, uuid, imsi, model, " +
            "simserialnum, pixel, ip_address, mac_address, latitude, longitude, language, " +
            "country, time_zone, ctime) " +
            "VALUES (#{userId}, #{action}, #{deviceType}, #{phoneNumber}, #{phoneName}, " +
            "#{deviceToken}, #{systemVersion}, #{appVersion}, #{uuid}, #{imsi}, #{model}, " +
            "#{simserialnum}, #{pixel}, #{ipAddress}, #{macAddress}, #{latitude}, #{longitude}, " +
            "#{language}, #{country}, #{timeZone}, #{ctime})")
    public Integer createUserLoginLog(UserLoginLog userLoginLog);



}
