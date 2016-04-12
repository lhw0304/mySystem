package com.mode.dao;

import com.mode.entity.UserFeedback;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zhaoweiwei on 16/3/21.
 */
public interface UserFeedbackDAO {

    /**
     * Create userFeedback
     *
     * @param userFeedback
     * @return
     */
    @Insert("INSERT INTO md_user_feedback (type, sender, receiver, content, status, ctime) " +
            "VALUES (#{type}, #{sender}, #{receiver}, #{content}, #{status}, #{ctime})")
    public Integer createUserFeedback(UserFeedback userFeedback);


    /**
     * Get userFeedback
     *
     * @param userFeedback
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_user_feedback ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='type != null'> AND type = #{type} </if>",
            "<if test='sender != null'> AND sender = #{sender} </if>",
            "<if test='receiver != null'> AND receiver = #{receiver} </if>",
            "<if test='content != null'> AND content = #{content} </if>",
            "<if test='status != null'> AND status = #{status} </if>",
            "<if test='ctime != null'> AND ctime = #{ctime} </if>",
            "</otherwise>",
            "</choose>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "type", column = "type"),
            @Result(property = "sender", column = "sender"),
            @Result(property = "receiver", column = "receiver"),
            @Result(property = "content", column = "content"),
            @Result(property = "status", column = "status"),
            @Result(property = "ctime", column = "ctime")})
    public UserFeedback getUserFeedback(UserFeedback userFeedback);

    /**
     * Delete userFeedback
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_user_feedback WHERE id = #{id}")
    public Integer deleteUserFeedback(Integer id);
}
