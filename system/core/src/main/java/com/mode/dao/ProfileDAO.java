package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.Profile;

/**
 * Created by zhaoweiwei on 16/3/18.
 */
public interface ProfileDAO {

    /**
     * Create profile
     *
     * @param profile
     * @return
     */
    @Insert("INSERT INTO md_profile (user_id, gender, nickname, level, avatar, birthday, " +
            "description, payment, credits, address, usd, invite_by, default_image, saves, " +
            "ctime, utime) " +
            "VALUES (#{userId}, #{gender}, #{nickname}, #{level}, #{avatar}, #{birthday}, " +
            "#{description}, #{payment}, #{credits}, #{address}, #{usd}, #{inviteBy}, " +
            "#{defaultImage}, #{saves}, #{ctime}, #{utime})")
    public Integer createProfile(Profile profile);


    /**
     * Update profile
     *
     * @param profile
     * @return
     */
    @Update({"<script>",
            "UPDATE md_profile ",
            "<set>",
            "<if test='gender != null'> gender = #{gender}, </if>",
            "<if test='nickname != null'> nickname = #{nickname}, </if>",
            "<if test='level != null'> level = #{level}, </if>",
            "<if test='avatar != null'> avatar = #{avatar}, </if>",
            "<if test='birthday != null'> birthday = #{birthday}, </if>",
            "<if test='description != null'> description = #{description}, </if>",
            "<if test='payment != null'> payment = #{payment}, </if>",
            "<if test='credits != null'> credits = #{credits}, </if>",
            "<if test='address != null'> address = #{address}, </if>",
            "<if test='usd != null'> usd = #{usd}, </if>",
            "<if test='inviteBy != null'> invite_by = #{inviteBy}, </if>",
            "<if test='defaultImage != null'> default_image = #{defaultImage}, </if>",
            "<if test='saves != null'> saves = #{saves}, </if>",
            "<if test='ctime != null'> ctime = #{ctime}, </if>",
            "<if test='utime != null'> utime = #{utime} </if>",
            "</set>",
            "WHERE user_id = #{userId}",
            "</script>"})
    public Integer updateProfile(Profile profile);


    /**
     * Get profile
     *
     * @param profile
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_profile ",
            "<where>",
            "<choose>",
            "<when test='userId != null'> user_id = #{userId} </when>",
            "<otherwise>",
            "<if test='gender != null'> AND gender = #{gender} </if>",
            "<if test='nickname != null'> AND nickname = #{nickname} </if>",
            "<if test='level != null'> AND level = #{level} </if>",
            "<if test='avatar != null'> AND avatar = #{avatar} </if>",
            "<if test='birthday != null'> AND birthday = #{birthday} </if>",
            "<if test='description != null'> AND description = #{description} </if>",
            "<if test='payment != null'> AND payment = #{payment} </if>",
            "<if test='credits != null'> AND credits = #{credits} </if>",
            "<if test='address != null'> AND address = #{address} </if>",
            "<if test='usd != null'> AND usd = #{usd} </if>",
            "<if test='inviteBy != null'> AND invite_by = #{inviteBy} </if>",
            "<if test='defaultImage != null'> AND default_image = #{defaultImage} </if>",
            "<if test='saves != null'> AND saves = #{saves} </if>",
            "<if test='ctime != null'> AND ctime = #{ctime} </if>",
            "<if test='utime != null'> AND utime = #{utime} </if>",
            "</otherwise>",
            "</choose>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "level", column = "level"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "description", column = "description"),
            @Result(property = "payment", column = "payment"),
            @Result(property = "credits", column = "credits"),
            @Result(property = "address", column = "address"),
            @Result(property = "usd", column = "usd"),
            @Result(property = "inviteBy", column = "invite_by"),
            @Result(property = "defaultImage", column = "default_image"),
            @Result(property = "saves", column = "saves"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime")})
    public Profile getProfile(Profile profile);

    /**
     * Delete profile
     *
     * @param userId
     * @return
     */
    @Delete("DELETE FROM md_profile WHERE user_id = #{userId}")
    public Integer deleteProfile(Integer userId);
}
