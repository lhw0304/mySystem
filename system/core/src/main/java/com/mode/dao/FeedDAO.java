package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.Feed;

/**
 * Created by zhaoweiwei on 16/3/18.
 */
public interface FeedDAO {

    /**
     * Create feed
     *
     * @param feed
     * @return
     */
    @Insert("INSERT INTO md_feed (type, author_id, title, default_image, content, " +
            "description, section, status, likes, saves, comments, shares, ctime, utime) " +
            "VALUES (#{type}, #{authorId}, #{title}, #{defaultImage}, #{content}, " +
            "#{description}, #{section}, #{status}, #{likes}, #{saves}, #{comments}, #{shares}, " +
            "#{ctime}, #{utime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createFeed(Feed feed);


    /**
     * Update feed
     *
     * @param feed
     * @return
     */
    @Update({"<script>",
            "UPDATE md_feed ",
            "<set>",
            "<if test='type != null'> type = #{type}, </if>",
            "<if test='authorId != null'> author_id = #{authorId}, </if>",
            "<if test='title != null'> title = #{title}, </if>",
            "<if test='defaultImage != null'> default_image = #{defaultImage}, </if>",
            "<if test='content != null'> content = #{content}, </if>",
            "<if test='description != null'> description = #{description}, </if>",
            "<if test='section != null'> section = #{section}, </if>",
            "<if test='status != null'> status = #{status}, </if>",
            "<if test='likes != null'> likes = #{likes}, </if>",
            "<if test='saves != null'> saves = #{saves}, </if>",
            "<if test='comments != null'> comments = #{comments}, </if>",
            "<if test='shares != null'> shares = #{shares}, </if>",
            "<if test='ctime != null'> ctime = #{ctime}, </if>",
            "<if test='utime != null'> utime = #{utime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateFeed(Feed feed);


    /**
     * Get feed
     *
     * @param feed
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_feed ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='type != null'> AND type = #{type} </if>",
            "<if test='authorId != null'> AND author_id = #{authorId} </if>",
            "<if test='title != null'> AND title = #{title} </if>",
            "<if test='defaultImage != null'> AND default_image = #{defaultImage} </if>",
            "<if test='content != null'> AND content = #{content} </if>",
            "<if test='description != null'> AND description = #{description} </if>",
            "<if test='section != null'> AND section = #{section} </if>",
            "<if test='status != null'> AND status = #{status} </if>",
            "<if test='likes != null'> AND likes = #{likes} </if>",
            "<if test='saves != null'> AND saves = #{saves} </if>",
            "<if test='comments != null'> AND comments = #{comments} </if>",
            "<if test='shares != null'> AND shares = #{shares} </if>",
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
            @Result(property = "authorId", column = "author_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "defaultImage", column = "default_image"),
            @Result(property = "content", column = "content"),
            @Result(property = "description", column = "description"),
            @Result(property = "section", column = "section"),
            @Result(property = "status", column = "status"),
            @Result(property = "likes", column = "likes"),
            @Result(property = "saves", column = "saves"),
            @Result(property = "comments", column = "comments"),
            @Result(property = "shares", column = "shares"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime")})
    public Feed getFeed(Feed feed);

    /**
     * Delete feed
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_feed WHERE id = #{id}")
    public Integer deleteFeed(Integer id);
}
