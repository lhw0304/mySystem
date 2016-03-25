package com.mode.dao;

import com.mode.entity.Essay;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2016/3/10.
 */
public interface EssayDAO {

    @Insert("INSERT INTO md_essay(user_id,content,answer,ctime) VALUES(#{userId},#{content}," +
            "#{answer},#{ctime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createEssay(Essay essay);

    @Delete("DELETE FROM md_essay WHERE id = #{id}")
    public Integer deleteEssay(@Param("id") Integer id);

    @Select("SELECT * FROM md_essay WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "answer", column = "answer"),
            @Result(property = "ctime", column = "ctime")})
    public Essay getEssay(@Param("id") Integer id);

    @Select({"<script>",
            "SELECT * FROM md_essay WHERE user_id = #{userId}",
            "<if test='limit != null'> AND limit  #{limit} </if>",
            "<if test='offset != null'> AND offset  #{offset} </if>",
            "</script>"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "answer", column = "answer"),
            @Result(property = "ctime", column = "ctime")})
    public List<Essay> getEssayListByUserId(@Param("userId") Integer userId,
                                                      @Param("limit") Integer limit,
                                                      @Param("offset") Integer offset);

    @Update({"<script>",
            "UPDATE md_essay ",
            "<set>",
            "<if test='content != null'>content = #{content},</if>",
            "<if test='answer != null'>answer = #{answer},</if>",
            "<if test='ctime != null'>ctime = #{ctime},</if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateEssay(Essay essay);

    @Select({"<script>",
            "SELECT count(DISTINCT id) as total  ",
            "FROM md_essay where user_id = #{userId} ",
            "</script>"})
    public Integer countEssay(@Param("userId") Integer userId);

    @Select({
            "<script>",
            "select * from md_essay where user_id = #{userId} order by rand() limit 0 #{limit}",
            "</script>"
    })
    public List<Essay> getGroupList(@Param("userId") Integer userId,
                                         @Param("limit") Integer limit);

}
