package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.UserEngagement;

/**
 * Created by zhaoweiwei on 16/3/21.
 */
public interface UserEngagementDAO {

    /**
     * Create userEngagement
     *
     * @param userEngagement
     * @return
     */
    @Insert("INSERT INTO md_user_engagement (user_id, action, object_type, object_id, ctime) " +
            "VALUES (#{userId}, #{action}, #{objectType}, #{objectId}, #{ctime})")
    public Integer createUserEngagement(UserEngagement userEngagement);


    /**
     * Update userEngagement
     *
     * @param userEngagement
     * @return
     */
    @Update({"<script>",
            "UPDATE md_user_engagement ",
            "<set>",
            "<if test='userId != null'> user_id = #{userId}, </if>",
            "<if test='action != null'> action = #{action}, </if>",
            "<if test='objectType != null'> object_type = #{objectType}, </if>",
            "<if test='objectId != null'> object_id = #{objectId}, </if>",
            "<if test='ctime != null'> ctime = #{ctime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateUserEngagement(UserEngagement userEngagement);


    /**
     * Get userEngagement
     *
     * @param userEngagement
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_user_engagement ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='userId != null'> AND user_id = #{userId} </if>",
            "<if test='action != null'> AND action = #{action} </if>",
            "<if test='objectType != null'> AND object_type = #{objectType} </if>",
            "<if test='objectId != null'> AND object_id = #{objectId} </if>",
            "<if test='ctime != null'> AND ctime = #{ctime} </if>",
            "</otherwise>",
            "</choose>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "action", column = "action"),
            @Result(property = "objectType", column = "object_type"),
            @Result(property = "objectId", column = "object_id"),
            @Result(property = "ctime", column = "ctime")})
    public UserEngagement getUserEngagement(UserEngagement userEngagement);

    /**
     * Delete userEngagement
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_user_engagement WHERE id = #{id}")
    public Integer deleteUserEngagement(Integer id);
}
