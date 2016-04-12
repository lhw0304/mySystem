package com.mode.dao;

import org.apache.ibatis.annotations.Insert;

import com.mode.entity.UserCreditLog;

/**
 * Created by zhaoweiwei on 16/3/21.
 */
public interface UserCreditLogDAO {

    /**
     * Create userCreditLog
     *
     * @param userCreditLog
     * @return
     */
    @Insert("INSERT INTO md_user_credit_log (user_id, task_id, type, credit, balance, " +
            "remarks, ctime) " +
            "VALUES (#{userId}, #{taskId}, #{type}, #{credit}, #{balance}, " +
            "#{remarks}, #{ctime})")
    public Integer createUserCreditLog(UserCreditLog userCreditLog);
}
