package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.FeedCountry;

/**
 * Created by zhaoweiwei on 16/3/18.
 */
public interface FeedCountryDAO {

    /**
     * Create feedCountry
     *
     * @param feedCountry
     * @return
     */
    @Insert("INSERT INTO md_feed_country (feed_id, country_code, ctime)" +
            "VALUES (#{feedId}, #{countryCode}, #{ctime})")
    public Integer createFeedCountry(FeedCountry feedCountry);


    /**
     * Update feedCountry
     *
     * @param feedCountry
     * @return
     */
    @Update({"<script>",
            "UPDATE md_feed_country ",
            "<set>",
            "<if test='feedId != null'> feed_id = #{feedId}, </if>",
            "<if test='countryCode != null'> country_code = #{countryCode}, </if>",
            "<if test='ctime != null'> ctime = #{ctime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateFeedCountry(FeedCountry feedCountry);


    /**
     * Get feedCountry
     *
     * @param feedCountry
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_feed_country ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='feedId != null'> AND feed_id = #{feedId} </if>",
            "<if test='countryCode != null'> AND country_code = #{countryCode} </if>",
            "<if test='ctime != null'> AND ctime = #{ctime} </if>",
            "</otherwise>",
            "</choose>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "feedId", column = "feed_id"),
            @Result(property = "countryCode", column = "country_code"),
            @Result(property = "ctime", column = "ctime")})
    public FeedCountry getFeedCountry(FeedCountry feedCountry);

    /**
     * Delete feedCountry
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_feed_country WHERE id = #{id}")
    public Integer deleteFeedCountry(Integer id);
}
