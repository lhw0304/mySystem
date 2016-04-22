package com.mode.dao;

import com.mode.entity.SingleSelect;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Administrator on 2016/3/7.
 */
public interface SingleSelectDAO {

    @Select({"<script>",
            "SELECT * FROM md_single_select  ",
            "<where>",
            "<if test='userId != null'> user_id = #{userId} </if>",
            "</where>",
            "order by ctime desc ",
            "<if test='limit != null'>limit #{limit} </if>",
            "<if test='offset != null'>offset #{offset} </if>",
            "</script>"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "a", column = "a"),
            @Result(property = "b", column = "b"),
            @Result(property = "c", column = "c"),
            @Result(property = "d", column = "d"),
            @Result(property = "answer", column = "answer"),
            @Result(property = "knowledge", column = "knowledge"),
            @Result(property = "ctime", column = "ctime")})
    public List<SingleSelect> getSingleSelectListByUserId(@Param("userId") Integer userId,
                                                          @Param("limit") Integer limit,
                                                          @Param("offset") Integer offset);


    @Insert("INSERT INTO md_single_select(user_id,content,a,b,c,d,answer,knowledge,ctime) VALUES(#{userId},#{content},#{a},#{b}," +
            "#{c},#{d},#{answer},#{knowledge},#{ctime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createSingleSelect(SingleSelect singleSelect);

    @Delete("DELETE FROM md_single_select WHERE id = #{id}")
    public Integer deleteSingleSelect(@Param("id") Integer id);

    @Select("SELECT * FROM md_single_select WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "a", column = "a"),
            @Result(property = "b", column = "b"),
            @Result(property = "c", column = "c"),
            @Result(property = "d", column = "d"),
            @Result(property = "answer", column = "answer"),
            @Result(property = "knowledge", column = "knowledge"),
            @Result(property = "ctime", column = "ctime")})
    public SingleSelect getSingleSelect(@Param("id") Integer id);

    @Update({"<script>",
            "UPDATE md_single_select ",
            "<set>",
            "<if test='content != null'>content = #{content},</if>",
            "<if test='a != null'>a = #{a},</if>",
            "<if test='b != null'>b = #{b},</if>",
            "<if test='c != null'>c = #{c},</if>",
            "<if test='d != null'>d = #{d},</if>",
            "<if test='answer != null'>answer = #{answer},</if>",
            "<if test='ctime != null'>ctime = #{ctime},</if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateSingleSelect(SingleSelect singleSelect);

    @Select({"<script>",
            "SELECT count(DISTINCT id) as total  ",
            "FROM md_single_select ",
            "<where>",
            "<if test='userId != null'> user_id = #{userId} </if>",
            "</where>",
            "</script>"})
    public Integer countSingleSelect(@Param("userId") Integer userId);

    @Select({"<script>",
            "SELECT count(DISTINCT knowledge) as total  ",
            "FROM md_single_select ",
            "<where>",
            "<if test='userId != null'> user_id = #{userId} </if>",
            "</where>",
            "</script>"})
    public Integer countSingleKnowledge(@Param("userId") Integer userId);

    @Select({"<script>",
            "SELECT  (SELECT id FROM md_single_select  WHERE knowledge = main.knowledge ORDER BY rand()  LIMIT 0,1) as id",
            "FROM md_single_select main  ",
            "<where>",
            "<if test='userId != null'> user_id = #{userId} </if>",
            "</where>",
            "GROUP BY knowledge limit #{limit}",
            "</script>"})
    public List<Integer> getSingleSelectIdList(@Param("userId") Integer userId,
                                             @Param("limit") Integer limit);

    @Select({"<script>",
            "select * from (",
            "SELECT (SELECT id FROM md_single_select  WHERE user_id = #{userId} and knowledge = main.knowledge " ,
            "ORDER BY rand()  LIMIT 0,1) as id",
            "FROM md_single_select main GROUP BY knowledge union ",
            "(select id from md_single_select where user_id = #{userId} order by rand() limit #{restCount}) )a limit #{singleCount}",
            "</script>"})
    public List<Integer> getSingleSelectIdList2(@Param("userId") Integer userId,
                                              @Param("restCount") Integer restCount,
                                              @Param("singleCount") Integer singleCount);

    @Select({
            "<script>",
            "select * from md_single_select where id in (${stringSingleIds})",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "a", column = "a"),
            @Result(property = "b", column = "b"),
            @Result(property = "c", column = "c"),
            @Result(property = "d", column = "d"),
            @Result(property = "answer", column = "answer"),
            @Result(property = "knowledge", column = "knowledge"),
            @Result(property = "ctime", column = "ctime")})
    public List<SingleSelect> getGroupList(@Param("stringSingleIds") String stringSingleIds);

}
