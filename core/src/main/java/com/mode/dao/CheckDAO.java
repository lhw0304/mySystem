package com.mode.dao;

import com.mode.entity.Check;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface CheckDAO {

    @Insert("INSERT INTO md_check(user_id,content,answer,ctime) VALUES(#{userId},#{content}," +
            "#{answer},#{ctime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createCheck(Check check);

    @Delete("DELETE FROM md_check WHERE id = #{id}")
    public Integer deleteCheck(@Param("id") Integer id);

    @Select("SELECT * FROM md_check WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "answer", column = "answer"),
            @Result(property = "ctime", column = "ctime")})
    public Check getCheck(@Param("id") Integer id);

    @Select({"<script>",
            "SELECT * FROM md_check WHERE user_id = #{userId}",
            "<if test='limit != null'> AND limit  #{limit} </if>",
            "<if test='offset != null'> AND offset  #{offset} </if>",
            "</script>"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "answer", column = "answer"),
            @Result(property = "ctime", column = "ctime")})
    public List<Check> getCheckListByUserId(@Param("userId") Integer userId,
                                                        @Param("limit") Integer limit,
                                                        @Param("offset") Integer offset);

    @Update({"<script>",
            "UPDATE md_check ",
            "<set>",
            "<if test='content != null'>content = #{content},</if>",
            "<if test='answer != null'>answer = #{answer},</if>",
            "<if test='ctime != null'>ctime = #{ctime},</if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateCheck(Check check);

    @Select({"<script>",
            "SELECT count(DISTINCT id) as total  ",
            "FROM md_check where user_id = #{userId} ",
            "</script>"})
    public Integer countCheck(@Param("userId") Integer userId);
}
