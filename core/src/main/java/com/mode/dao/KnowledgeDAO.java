package com.mode.dao;

import com.mode.entity.Knowledge;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
public interface KnowledgeDAO {

    @Insert("INSERT INTO md_knowledge(user_id,knowledge,ctime) VALUES(#{userId},#{knowledge},#{ctime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id", before = false, resultType = Integer.class)
    public Integer createCheck(Knowledge knowledge);

    @Delete("DELETE FROM md_knowledge WHERE id = #{id}")
    public Integer deleteKnowledge(@Param("id") Integer id);

    @Select("SELECT * FROM md_knowledge WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "knowledge", column = "knowledge"),
            @Result(property = "ctime", column = "ctime")})
    public Knowledge getKnowledge(@Param("id") Integer id);

    @Select({"<script>",
            "SELECT * FROM md_knowledge ",
            "<where>",
            "<if test='userId != null'> user_id = #{userId} </if>",
            "</where>",
            " order by ctime desc",
            "<if test='limit != null'>  LIMIT  #{limit}</if>",
            "<if test='offset != null'> OFFSET  #{offset}</if>",
            "</script>"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "knowledge", column = "knowledge"),
            @Result(property = "ctime", column = "ctime")})
    public List<Knowledge> getKnowledgeListByUserId(@Param("userId") Integer userId,
                                            @Param("limit") Integer limit,
                                            @Param("offset") Integer offset);

    @Update({"<script>",
            "UPDATE md_knowledge ",
            "<set>",
            "<if test='knowledge != null'>knowledge = #{knowledge},</if>",
            "<if test='ctime != null'>ctime = #{ctime},</if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateKnowledge(Knowledge knowledge);

    @Select({"<script>",
            "SELECT count(DISTINCT id) as total  ",
            "FROM md_knowledge ",
            "<where>",
            "<if test='userId != null'> user_id = #{userId} </if>",
            "</where>",
            "</script>"})
    public Integer countKnowledge(@Param("userId") Integer userId);
}
