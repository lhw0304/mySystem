package com.mode.dao;

import com.mode.entity.Profile;
import org.apache.ibatis.annotations.*;

/**
 * Created by Administrator on 2016/3/7.
 */
public interface ProfileDAO {

    @Select({
            "SELECT a.*, b.role ",
            "FROM md_profile a inner JOIN md_account b ON a.user_id = b.user_id ",
            "WHERE a.user_id = #{userId}"
    })
    @Results({
            @Result(property = "profileId", column = "profile_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "credits", column = "credits"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
            @Result(property = "description", column = "description"),
            @Result(property = "role", column = "role")})
    public Profile getProfileByUserId(@Param("userId") Integer userId);

    @Insert("INSERT INTO  md_profile(user_id, nickname, gender,ctime, utime, description) " +
            "VALUES (#{userId}, #{nickname}, #{gender},#{ctime}, #{utime},#{description})")
    public Integer createProfile(Profile profile);

    @Update({"<script>",
            "UPDATE md_profile",
            "<set>",
            "<if test='nickname != null'>nickname=#{nickname},</if>",
            "<if test='gender != null'>gender=#{gender},</if>",
            "<if test='ctime != null'>ctime=#{ctime},</if>",
            "<if test='utime != null'>utime=#{utime},</if>",
            "<if test='description != null'>description=#{description},</if>",
            "</set>",
            "WHERE user_id = #{userId}",
            "</script>"})
    public Integer updateProfile(Profile profile);

    @Delete("DELETE FROM md_profile WHERE user_id = #{userId}")
    public Integer deleteProfile(@Param("userId") Integer userId);
}
