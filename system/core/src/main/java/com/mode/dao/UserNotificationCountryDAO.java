package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.UserNotificationCountry;

/**
 * Created by zhaoweiwei on 16/3/21.
 */
public interface UserNotificationCountryDAO {

    /**
     * Create userNotificationCountry
     *
     * @param userNotificationCountry
     * @return
     */
    @Insert("INSERT INTO md_user_notification_country (notification_id, country_code, ctime)" +
            "VALUES (#{notificationId}, #{countryCode}, #{ctime})")
    public Integer createUserNotificationCountry(UserNotificationCountry userNotificationCountry);


    /**
     * Update userNotificationCountry
     *
     * @param userNotificationCountry
     * @return
     */
    @Update({"<script>",
            "UPDATE md_user_notification_country ",
            "<set>",
            "<if test='notificationId != null'> notification_id = #{notificationId}, </if>",
            "<if test='countryCode != null'> country_code = #{countryCode}, </if>",
            "<if test='ctime != null'> ctime = #{ctime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateUserNotificationCountry(UserNotificationCountry userNotificationCountry);


    /**
     * Get userNotificationCountry
     *
     * @param userNotificationCountry
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_user_notification_country ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='notificationId != null'> AND notification_id = #{notificationId} </if>",
            "<if test='countryCode != null'> AND country_code = #{countryCode} </if>",
            "<if test='ctime != null'> AND ctime = #{ctime} </if>",
            "</otherwise>",
            "</choose>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "notificationId", column = "notification_id"),
            @Result(property = "countryCode", column = "country_code"),
            @Result(property = "ctime", column = "ctime")})
    public UserNotificationCountry getUserNotificationCountry(UserNotificationCountry userNotificationCountry);

    /**
     * Delete userNotificationCountry
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_user_notification_country WHERE id = #{id}")
    public Integer deleteUserNotificationCountry(Integer id);
}
