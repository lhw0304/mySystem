package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.Stylist;

/**
 * Created by zhaoweiwei on 16/3/21.
 */
public interface StylistDAO {

    /**
     * Create stylist
     *
     * @param stylist
     * @return
     */
    @Insert("INSERT INTO md_stylist (user_id, name, logo, description, status, " +
            "country_code, followers, articles, ctime, utime) " +
            "VALUES (#{userId}, #{name}, #{logo}, #{description}, #{status}, " +
            "#{countryCode}, #{followers}, #{articles}, #{ctime}, #{utime})")
    public Integer createStylist(Stylist stylist);


    /**
     * Update stylist
     *
     * @param stylist
     * @return
     */
    @Update({"<script>",
            "UPDATE md_stylist ",
            "<set>",
            "<if test='name != null'> name = #{name}, </if>",
            "<if test='logo != null'> logo = #{logo}, </if>",
            "<if test='description != null'> description = #{description}, </if>",
            "<if test='status != null'> status = #{status}, </if>",
            "<if test='countryCode != null'> country_code = #{countryCode}, </if>",
            "<if test='followers != null'> followers = #{followers}, </if>",
            "<if test='articles != null'> articles = #{articles}, </if>",
            "<if test='ctime != null'> ctime = #{ctime}, </if>",
            "<if test='utime != null'> utime = #{utime} </if>",
            "</set>",
            "WHERE user_id = #{userId}",
            "</script>"})
    public Integer updateStylist(Stylist stylist);


    /**
     * Get stylist
     *
     * @param stylist
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_stylist ",
            "<where>",
            "<choose>",
            "<when test='userId != null'> user_id = #{userId} </when>",
            "<otherwise>",
            "<if test='name != null'> AND name = #{name} </if>",
            "<if test='logo != null'> AND logo = #{logo} </if>",
            "<if test='description != null'> AND description = #{description} </if>",
            "<if test='status != null'> AND status = #{status} </if>",
            "<if test='countryCode != null'> AND country_code like \"%\"#{countryCode}\"%\" </if>",
            "<if test='followers != null'> AND followers = #{followers} </if>",
            "<if test='articles != null'> AND articles = #{articles} </if>",
            "<if test='ctime != null'> AND ctime = #{ctime} </if>",
            "<if test='utime != null'> AND utime = #{utime} </if>",
            "</otherwise>",
            "</choose>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "logo", column = "logo"),
            @Result(property = "description", column = "description"),
            @Result(property = "status", column = "status"),
            @Result(property = "countryCode", column = "country_code"),
            @Result(property = "followers", column = "followers"),
            @Result(property = "articles", column = "articles"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime")})
    public Stylist getStylist(Stylist stylist);

    /**
     * Delete stylist
     *
     * @param userId
     * @return
     */
    @Delete("DELETE FROM md_stylist WHERE user_id = #{userId}")
    public Integer deleteStylist(Integer userId);

}
