package com.mode.dao;

import com.mode.entity.ShortAnswer;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface ShortAnswerDAO {

    @Insert("INSERT INTO md_short_answer(user_id,content,answer,ctime) VALUES(#{userId},#{content}," +
            "#{answer},#{ctime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createShortAnswer(ShortAnswer shortAnswer);

    @Delete("DELETE FROM md_short_answer WHERE id = #{id}")
    public Integer deleteShortAnswer(@Param("id") Integer id);

    @Select("SELECT * FROM md_short_answer WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "answer", column = "answer"),
            @Result(property = "ctime", column = "ctime")})
    public ShortAnswer getShortAnswer(@Param("id") Integer id);

    @Select({"<script>",
            "SELECT * FROM md_short_answer WHERE user_id = #{userId}",
            "order by ctime desc ",
            "<if test='limit != null'>limit #{limit} </if>",
            "<if test='offset != null'>offset #{offset} </if>",
            "</script>"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "answer", column = "answer"),
            @Result(property = "ctime", column = "ctime")})
    public List<ShortAnswer> getShortAnswerListByUserId(@Param("userId") Integer userId,
                                                      @Param("limit") Integer limit,
                                                      @Param("offset") Integer offset);

    @Update({"<script>",
            "UPDATE md_short_answer ",
            "<set>",
            "<if test='content != null'>content = #{content},</if>",
            "<if test='answer != null'>answer = #{answer},</if>",
            "<if test='ctime != null'>ctime = #{ctime},</if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateShortAnswer(ShortAnswer completion);

    @Select({"<script>",
            "SELECT count(DISTINCT id) as total  ",
            "FROM md_short_answer where user_id = #{userId} ",
            "</script>"})
    public Integer countShortAnswer(@Param("userId") Integer userId);

    @Select({
            "<script>",
            "select * from md_short_answer where user_id = #{userId} order by rand() limit 0 #{limit}",
            "</script>"
    })
    public List<ShortAnswer> getGroupList(@Param("userId") Integer userId,
                                         @Param("limit") Integer limit);
}
