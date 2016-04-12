package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.Ad;


/**
 * Created by zhaoweiwei on 16/3/18.
 */
public interface AdDAO {

    /**
     * Create ad
     *
     * @param ad
     * @return
     */
    @Insert("INSERT INTO md_ad (type, title, display_page, default_image, content, " +
            "description, status, country_code, ctime, utime) " +
            "VALUES (#{type}, #{title}, #{displayPage}, #{defaultImage}, #{content}, " +
            "#{description}, #{status}, #{countryCode}, #{ctime}, #{utime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createAd(Ad ad);


    /**
     * Update ad
     *
     * @param ad
     * @return
     */
    @Update({"<script>",
            "UPDATE md_ad ",
            "<set>",
            "<if test='type != null'> type = #{type}, </if>",
            "<if test='title != null'> title = #{title}, </if>",
            "<if test='displayPage != null'> display_page = #{displayPage}, </if>",
            "<if test='defaultImage != null'> default_image = #{defaultImage}, </if>",
            "<if test='content != null'> content = #{content}, </if>",
            "<if test='description != null'> description = #{description}, </if>",
            "<if test='status != null'> status = #{status}, </if>",
            "<if test='countryCode != null'> country_code = #{countryCode}, </if>",
            "<if test='ctime != null'> ctime = #{ctime}, </if>",
            "<if test='utime != null'> utime = #{utime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateAd(Ad ad);


    /**
     * Get ad
     *
     * @param ad
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_ad ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='type != null'> AND type = #{type} </if>",
            "<if test='title != null'> AND title = #{title} </if>",
            "<if test='displayPage != null'> AND display_page = #{displayPage} </if>",
            "<if test='defaultImage != null'> AND default_image = #{defaultImage} </if>",
            "<if test='content != null'> AND content = #{content} </if>",
            "<if test='description != null'> AND description = #{description} </if>",
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
            @Result(property = "type", column = "type"),
            @Result(property = "title", column = "title"),
            @Result(property = "displayPage", column = "display_page"),
            @Result(property = "defaultImage", column = "default_image"),
            @Result(property = "content", column = "content"),
            @Result(property = "description", column = "description"),
            @Result(property = "status", column = "status"),
            @Result(property = "countryCode", column = "country_code"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime")})
    public Ad getAd(Ad ad);

    /**
     * Delete ad
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_ad WHERE id = #{id}")
    public Integer deleteAd(Integer id);
}
