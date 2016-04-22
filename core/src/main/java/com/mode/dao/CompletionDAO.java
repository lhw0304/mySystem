package com.mode.dao;

import com.mode.entity.Completion;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface CompletionDAO {

    @Insert("INSERT INTO md_completion(user_id,content,answer,knowledge,ctime) VALUES(#{userId},#{content}," +
            "#{answer},#{knowledge},#{ctime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createCompletion(Completion completion);

    @Delete("DELETE FROM md_completion WHERE id = #{id}")
    public Integer deleteCompletion(@Param("id") Integer id);

    @Select("SELECT * FROM md_completion WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "answer", column = "answer"),
            @Result(property = "knowledge", column = "knowledge"),
            @Result(property = "ctime", column = "ctime")})
    public Completion getCompletion(@Param("id") Integer id);

    @Select({"<script>",
            "SELECT * FROM md_completion ",
            "<where>",
            "<if test='userId != null'> user_id = #{userId} </if>",
            "</where>",
            "order by ctime desc ",
            "<if test='limit != null'>limit #{limit} </if>",
            "<if test='offset != null'>offset  #{offset} </if>",
            "</script>"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "answer", column = "answer"),
            @Result(property = "knowledge", column = "knowledge"),
            @Result(property = "ctime", column = "ctime")})
    public List<Completion> getCompletionListByUserId(@Param("userId") Integer userId,
                                            @Param("limit") Integer limit,
                                            @Param("offset") Integer offset);

    @Update({"<script>",
            "UPDATE md_completion ",
            "<set>",
            "<if test='content != null'>content = #{content},</if>",
            "<if test='answer != null'>answer = #{answer},</if>",
            "<if test='ctime != null'>ctime = #{ctime},</if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateCompletion(Completion completion);

    @Select({"<script>",
            "SELECT count(DISTINCT id) as total  ",
            "FROM md_completion ",
            "<where>",
            "<if test='userId != null'> user_id = #{userId} </if>",
            "</where>",
            "</script>"})
    public Integer countCompletion(@Param("userId") Integer userId);

    @Select({"<script>",
            "SELECT count(DISTINCT knowledge) as total  ",
            "FROM md_completion ",
            "<where>",
            "<if test='userId != null'> user_id = #{userId} </if>",
            "</where>",
            "</script>"})
    public Integer countCompletionKnowledge(@Param("userId") Integer userId);

    @Select({"<script>",
            "SELECT  (SELECT id FROM md_completion  WHERE knowledge = main.knowledge ORDER BY rand()  LIMIT 0,1) as id",
            "FROM md_completion main  ",
            "<where>",
            "<if test='userId != null'> user_id = #{userId} </if>",
            "</where>",
            "GROUP BY knowledge limit #{limit}",
            "</script>"})
    public List<Integer> getCompletionIdList(@Param("userId") Integer userId,
                                        @Param("limit") Integer limit);

    @Select({"<script>",
            "select * from (",
            "SELECT (SELECT id FROM md_completion  WHERE user_id = #{userId} and knowledge = main.knowledge " ,
            "ORDER BY rand()  LIMIT 0,1) as id",
            "FROM md_completion main GROUP BY knowledge union ",
            "(select id from md_completion where user_id = #{userId} order by rand() limit #{restCount}) )a limit #{completionCount}",
            "</script>"})
    public List<Integer> getCompletionIdList2(@Param("userId") Integer userId,
                                         @Param("restCount") Integer restCount,
                                         @Param("completionCount") Integer completionCount);

    @Select({
            "<script>",
            "select * from md_completion where id in (${completionIds})",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "answer", column = "answer"),
            @Result(property = "knowledge", column = "knowledge"),
            @Result(property = "ctime", column = "ctime")})
    public List<Completion> getGroupList(@Param("completionIds") String completionIds);

}
