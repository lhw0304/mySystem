package com.mode.dao;

import com.mode.entity.MultiSelect;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface MultiSelectDAO {
    @Insert("INSERT INTO md_multi_select(user_id,content,a,b,c,d,answer,ctime) VALUES(#{userId},#{content},#{a},#{b}," +
            "#{c},#{d},#{answer},#{ctime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createMultiSelect(MultiSelect multiSelect);

    @Delete("DELETE FROM md_multi_select WHERE id = #{id}")
    public Integer deleteMultiSelect(@Param("id") Integer id);

    @Select("SELECT * FROM md_multi_select WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "a", column = "a"),
            @Result(property = "b", column = "b"),
            @Result(property = "c", column = "c"),
            @Result(property = "d", column = "d"),
            @Result(property = "answer", column = "answer"),
            @Result(property = "ctime", column = "ctime")})
    public MultiSelect getMultiSelect(@Param("id") Integer id);

    @Select({"<script>",
            "SELECT * FROM md_multi_select WHERE user_id = #{userId}",
            "<if test='limit != null'> AND limit  #{limit} </if>",
            "<if test='offset != null'> AND offset  #{offset} </if>",
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
            @Result(property = "ctime", column = "ctime")})
    public List<MultiSelect> getMultiSelectListByUserId(@Param("userId") Integer userId,
                                                          @Param("limit") Integer limit,
                                                          @Param("offset") Integer offset);

    @Update({"<script>",
            "UPDATE md_multi_select ",
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
    public Integer updateMultiSelect(MultiSelect multiSelect);

    @Select({"<script>",
            "SELECT count(DISTINCT id) as total  ",
            "FROM md_multi_select where user_id = #{userId} ",
            "</script>"})
    public Integer countMultiSelect(@Param("userId") Integer userId);
}
