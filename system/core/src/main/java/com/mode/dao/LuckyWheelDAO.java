package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.LuckyWheel;

/**
 * Created by zhaoweiwei on 16/3/18.
 */
public interface LuckyWheelDAO {

    /**
     * Create luckyWheel
     *
     * @param luckyWheel
     * @return
     */
    @Insert("INSERT INTO md_lucky_wheel (prize_detail, level, base, probability, " +
            "start_range, end_range, status, country_code, ctime, utime) " +
            "VALUES (#{prizeDetail}, #{level}, #{base}, #{probability}, #{startRange}, " +
            "#{endRange}, #{status}, #{countryCode}, #{ctime}, #{utime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createLuckyWheel(LuckyWheel luckyWheel);


    /**
     * Update luckyWheel
     *
     * @param luckyWheel
     * @return
     */
    @Update({"<script>",
            "UPDATE md_lucky_wheel ",
            "<set>",
            "<if test='prizeDetail != null'> prize_detail = #{prizeDetail}, </if>",
            "<if test='level != null'> level = #{level}, </if>",
            "<if test='base != null'> base = #{base}, </if>",
            "<if test='probability != null'> probability = #{probability}, </if>",
            "<if test='startRange != null'> start_range = #{startRange}, </if>",
            "<if test='endRange != null'> end_range = #{endRange}, </if>",
            "<if test='status != null'> status = #{status}, </if>",
            "<if test='countryCode != null'> country_code = #{countryCode}, </if>",
            "<if test='ctime != null'> ctime = #{ctime}, </if>",
            "<if test='utime != null'> utime = #{utime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateLuckyWheel(LuckyWheel luckyWheel);


    /**
     * Get luckyWheel
     *
     * @param luckyWheel
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_lucky_wheel ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='prizeDetail != null'> AND prize_detail = #{prizeDetail} </if>",
            "<if test='level != null'> AND level = #{level} </if>",
            "<if test='base != null'> AND base = #{base} </if>",
            "<if test='probability != null'> AND probability = #{probability} </if>",
            "<if test='startRange != null'> AND start_range = #{startRange} </if>",
            "<if test='endRange != null'> AND end_range = #{endRange} </if>",
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
            @Result(property = "prizeDetail", column = "prize_detail"),
            @Result(property = "level", column = "level"),
            @Result(property = "base", column = "base"),
            @Result(property = "probability", column = "probability"),
            @Result(property = "startRange", column = "start_range"),
            @Result(property = "endRange", column = "end_range"),
            @Result(property = "status", column = "status"),
            @Result(property = "countryCode", column = "country_code"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime")})
    public LuckyWheel getLuckyWheel(LuckyWheel luckyWheel);

    /**
     * Delete luckyWheel
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_lucky_wheel WHERE id = #{id}")
    public Integer deleteLuckyWheel(Integer id);
}
