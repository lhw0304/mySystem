package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.FeedDraft;

/**
 * Created by zhaoweiwei on 16/3/18.
 */
public interface FeedDraftDAO {

    /**
     * Create feedDraft
     *
     * @param feedDraft
     * @return
     */
    @Insert("INSERT INTO md_feed_draft (type, author_id, title, content, " +
            "description, status, comment, ctime, utime) " +
            "VALUES (#{type}, #{authorId}, #{title}, #{content}, " +
            "#{description}, #{status}, #{comment}, #{ctime}, #{utime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createFeedDraft(FeedDraft feedDraft);


    /**
     * Update feedDraft
     *
     * @param feedDraft
     * @return
     */
    @Update({"<script>",
            "UPDATE md_feed_draft ",
            "<set>",
            "<if test='type != null'> type = #{type}, </if>",
            "<if test='authorId != null'> author_id = #{authorId}, </if>",
            "<if test='title != null'> title = #{title}, </if>",
            "<if test='content != null'> content = #{content}, </if>",
            "<if test='description != null'> description = #{description}, </if>",
            "<if test='status != null'> status = #{status}, </if>",
            "<if test='comment != null'> comment = #{comment}, </if>",
            "<if test='ctime != null'> ctime = #{ctime}, </if>",
            "<if test='utime != null'> utime = #{utime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateFeedDraft(FeedDraft feedDraft);


    /**
     * Get feedDraft
     *
     * @param feedDraft
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_feed_draft ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='type != null'> AND type = #{type} </if>",
            "<if test='authorId != null'> AND author_id = #{authorId} </if>",
            "<if test='title != null'> AND title = #{title} </if>",
            "<if test='content != null'> AND content = #{content} </if>",
            "<if test='description != null'> AND description = #{description} </if>",
            "<if test='status != null'> AND status = #{status} </if>",
            "<if test='comment != null'> AND comment = #{comment} </if>",
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
            @Result(property = "content", column = "content"),
            @Result(property = "description", column = "description"),
            @Result(property = "status", column = "status"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime")})
    public FeedDraft getFeedDraft(FeedDraft feedDraft);

    /**
     * Delete feedDraft
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_feed_draft WHERE id = #{id}")
    public Integer deleteFeedDraft(Integer id);

}
