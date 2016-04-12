package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.UserShoppingLog;

/**
 * Created by zhaoweiwei on 16/3/21.
 */
public interface UserShoppingLogDAO {

    /**
     * Create userShoppingLog
     *
     * @param userShoppingLog
     * @return
     */
    @Insert("INSERT INTO md_user_shopping_log (user_id, item_id, feed_id, url, price, " +
            "navigation_type, source, browse_time) " +
            "VALUES (#{userId}, #{itemId}, #{feedId}, #{url}, #{price}, " +
            "#{navigationType}, #{source}, #{browseTime})")
    public Integer createUserShoppingLog(UserShoppingLog userShoppingLog);


}
