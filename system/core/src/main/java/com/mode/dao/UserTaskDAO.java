package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.UserTask;

/**
 * Created by zhaoweiwei on 16/3/21.
 */
public interface UserTaskDAO {

    /**
     * Create userTask
     *
     * @param userTask
     * @return
     */
    @Insert("INSERT INTO md_user_task (user_id, task_id, status, invite_code, ctime)" +
            "VALUES (#{userId}, #{taskId}, #{status}, #{inviteCode}, #{ctime})")
    public Integer createUserTask(UserTask userTask);

    /**
     * Get userTask
     *
     * @param userTask
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_user_task ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='userId != null'> AND user_id = #{userId} </if>",
            "<if test='taskId != null'> AND task_id = #{taskId} </if>",
            "<if test='status != null'> AND status = #{status} </if>",
            "<if test='inviteCode != null'> AND invite_code = #{inviteCode} </if>",
            "<if test='ctime != null'> AND ctime = #{ctime} </if>",
            "</otherwise>",
            "</choose>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "taskId", column = "task_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "inviteCode", column = "invite_code"),
            @Result(property = "ctime", column = "ctime")})
    public UserTask getUserTask(UserTask userTask);

    /**
     * Delete userTask
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_user_task WHERE id = #{id}")
    public Integer deleteUserTask(Integer id);
}
