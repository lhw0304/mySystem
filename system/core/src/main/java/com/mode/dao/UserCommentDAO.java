package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.UserComment;

/**
 * Created by zhaoweiwei on 16/3/21.
 */
public interface UserCommentDAO {

    /**
     * Create userComment
     *
     * @param userComment
     * @return
     */
    @Insert("INSERT INTO md_user_comment (user_id, object_type, object_id, " +
            "content, likes, ctime) " +
            "VALUES (#{userId}, #{objectType}, #{objectId}, #{content}, #{likes}, #{ctime})")
    public Integer createUserComment(UserComment userComment);


    /**
     * Update userComment
     *
     * @param userComment
     * @return
     */
    @Update({"<script>",
            "UPDATE md_user_comment ",
            "<set>",
            "<if test='userId != null'> user_id = #{userId}, </if>",
            "<if test='objectType != null'> object_type = #{objectType}, </if>",
            "<if test='objectId != null'> object_id = #{objectId}, </if>",
            "<if test='content != null'> content = #{content}, </if>",
            "<if test='likes != null'> likes = #{likes}, </if>",
            "<if test='ctime != null'> ctime = #{ctime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateUserComment(UserComment userComment);


    /**
     * Get userComment
     *
     * @param userComment
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_user_comment ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='userId != null'> AND user_id = #{userId} </if>",
            "<if test='objectType != null'> AND object_type = #{objectType} </if>",
            "<if test='objectId != null'> AND object_id = #{objectId} </if>",
            "<if test='content != null'> AND content = #{content} </if>",
            "<if test='likes != null'> AND likes = #{likes} </if>",
            "<if test='ctime != null'> AND ctime = #{ctime} </if>",
            "</otherwise>",
            "</choose>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "objectType", column = "object_type"),
            @Result(property = "objectId", column = "object_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "likes", column = "likes"),
            @Result(property = "ctime", column = "ctime")})
    public UserComment getUserComment(UserComment userComment);

    /**
     * Delete userComment
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_user_comment WHERE id = #{id}")
    public Integer deleteUserComment(Integer id);
}
