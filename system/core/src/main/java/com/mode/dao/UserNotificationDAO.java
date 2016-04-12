package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.UserNotification;

/**
 * Created by zhaoweiwei on 16/3/21.
 */
public interface UserNotificationDAO {

    /**
     * Create userNotification
     *
     * @param userNotification
     * @return
     */
    @Insert("INSERT INTO md_user_notification (type, target, user_id, title, content, " +
            "description, default_image, status, ctime, utime) " +
            "VALUES (#{type}, #{target}, #{userId}, #{title}, #{content}, " +
            "#{description}, #{defaultImage}, #{status}, #{ctime}, #{utime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createUserNotification(UserNotification userNotification);


    /**
     * Update userNotification
     *
     * @param userNotification
     * @return
     */
    @Update({"<script>",
            "UPDATE md_user_notification ",
            "<set>",
            "<if test='type != null'> type = #{type}, </if>",
            "<if test='target != null'> target = #{target}, </if>",
            "<if test='userId != null'> user_id = #{userId}, </if>",
            "<if test='title != null'> title = #{title}, </if>",
            "<if test='content != null'> content = #{content}, </if>",
            "<if test='description != null'> description = #{description}, </if>",
            "<if test='defaultImage != null'> default_image = #{defaultImage}, </if>",
            "<if test='status != null'> status = #{status}, </if>",
            "<if test='ctime != null'> ctime = #{ctime}, </if>",
            "<if test='utime != null'> utime = #{utime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateUserNotification(UserNotification userNotification);


    /**
     * Get userNotification
     *
     * @param userNotification
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_user_notification ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='type != null'> AND type = #{type} </if>",
            "<if test='target != null'> AND target = #{target} </if>",
            "<if test='userId != null'> AND user_id = #{userId} </if>",
            "<if test='title != null'> AND title = #{title} </if>",
            "<if test='content != null'> AND content = #{content} </if>",
            "<if test='description != null'> AND description = #{description} </if>",
            "<if test='defaultImage != null'> AND default_image = #{defaultImage} </if>",
            "<if test='status != null'> AND status = #{status} </if>",
            "<if test='ctime != null'> AND ctime = #{ctime} </if>",
            "<if test='utime != null'> AND utime = #{utime} </if>",
            "</otherwise>",
            "</choose>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "type", column = "type"),
            @Result(property = "target", column = "target"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "description", column = "description"),
            @Result(property = "defaultImage", column = "default_image"),
            @Result(property = "status", column = "status"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime")})
    public UserNotification getUserNotification(UserNotification userNotification);

    /**
     * Delete userNotification
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_user_notification WHERE id = #{id}")
    public Integer deleteUserNotification(Integer id);
}
