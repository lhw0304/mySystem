package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.Task;

/**
 * Created by zhaoweiwei on 16/3/21.
 */
public interface TaskDAO {

    /**
     * Create task
     *
     * @param task
     * @return
     */
    @Insert("INSERT INTO md_task (`group`, type, name, description, tips, " +
            "object_type, action, rule, start_time, end_time, priority, " +
            "status, country_code, ctime, utime) " +
            "VALUES (#{group}, #{type}, #{name}, #{description}, #{tips}, " +
            "#{objectType}, #{action}, #{rule}, #{startTime}, #{endTime}, " +
            "#{priority}, #{status}, #{countryCode}, #{ctime}, #{utime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createTask(Task task);


    /**
     * Update task
     *
     * @param task
     * @return
     */
    @Update({"<script>",
            "UPDATE md_task ",
            "<set>",
            "<if test='group != null'> group = #{group}, </if>",
            "<if test='type != null'> type = #{type}, </if>",
            "<if test='name != null'> name = #{name}, </if>",
            "<if test='description != null'> description = #{description}, </if>",
            "<if test='tips != null'> tips = #{tips}, </if>",
            "<if test='objectType != null'> object_type = #{objectType}, </if>",
            "<if test='action != null'> action = #{action}, </if>",
            "<if test='rule != null'> rule = #{rule}, </if>",
            "<if test='startTime != null'> start_time = #{startTime}, </if>",
            "<if test='endTime != null'> end_time = #{endTime}, </if>",
            "<if test='priority != null'> priority = #{priority}, </if>",
            "<if test='status != null'> status = #{status}, </if>",
            "<if test='countryCode != null'> country_code = #{countryCode}, </if>",
            "<if test='ctime != null'> ctime = #{ctime}, </if>",
            "<if test='utime != null'> utime = #{utime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateTask(Task task);


    /**
     * Get task
     *
     * @param task
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_task ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='group != null'> AND group = #{group} </if>",
            "<if test='type != null'> AND type = #{type} </if>",
            "<if test='name != null'> AND name = #{name} </if>",
            "<if test='description != null'> AND description = #{description} </if>",
            "<if test='tips != null'> AND tips = #{tips} </if>",
            "<if test='objectType != null'> AND object_type = #{objectType} </if>",
            "<if test='action != null'> AND action = #{action} </if>",
            "<if test='rule != null'> AND rule = #{rule} </if>",
            "<if test='startTime != null'> AND start_time = #{startTime} </if>",
            "<if test='endTime != null'> AND end_time = #{endTime} </if>",
            "<if test='priority != null'> AND priority = #{priority} </if>",
            "<if test='status != null'> AND status = #{status} </if>",
            "<if test='countryCode != null'> AND country_code like \"%\"#{countryCode}\"%\" </if>",
            "<if test='ctime != null'> AND ctime = #{ctime} </if>",
            "<if test='utime != null'> AND utime = #{utime} </if>",
            "</otherwise>",
            "</choose>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "group", column = "group"),
            @Result(property = "type", column = "type"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "tips", column = "tips"),
            @Result(property = "objectType", column = "object_type"),
            @Result(property = "action", column = "action"),
            @Result(property = "rule", column = "rule"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "priority", column = "priority"),
            @Result(property = "status", column = "status"),
            @Result(property = "countryCode", column = "country_code"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime")})
    public Task getTask(Task task);

    /**
     * Delete task
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_task WHERE id = #{id}")
    public Integer deleteTask(Integer id);
}
