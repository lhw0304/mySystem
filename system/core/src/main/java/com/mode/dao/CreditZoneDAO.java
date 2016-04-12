package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.CreditZone;

/**
 * Created by zhaoweiwei on 16/3/18.
 */
public interface CreditZoneDAO {

    /**
     * Create creditZone
     *
     * @param creditZone
     * @return
     */
    @Insert("INSERT INTO md_credit_zone (prize_detail, status, country_code, sort_order, " +
            "ctime, utime) " +
            "VALUES (#{prizeDetail}, #{status}, #{countryCode}, #{sortOrder}, " +
            " #{ctime}, #{utime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createCreditZone(CreditZone creditZone);


    /**
     * Update creditZone
     *
     * @param creditZone
     * @return
     */
    @Update({"<script>",
            "UPDATE md_credit_zone ",
            "<set>",
            "<if test='prizeDetail != null'> prize_detail = #{prizeDetail}, </if>",
            "<if test='status != null'> status = #{status}, </if>",
            "<if test='countryCode != null'> country_code = #{countryCode}, </if>",
            "<if test='sortOrder != null'> sort_order = #{sortOrder}, </if>",
            "<if test='ctime != null'> ctime = #{ctime}, </if>",
            "<if test='utime != null'> utime = #{utime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateCreditZone(CreditZone creditZone);


    /**
     * Get creditZone
     *
     * @param creditZone
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_credit_zone ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='prizeDetail != null'> AND prize_detail = #{prizeDetail} </if>",
            "<if test='status != null'> AND status = #{status} </if>",
            "<if test='countryCode != null'> AND country_code like \"%\"#{countryCode}\"%\" </if>",
            "<if test='sortOrder != null'> AND sort_order = #{sortOrder} </if>",
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
            @Result(property = "status", column = "status"),
            @Result(property = "countryCode", column = "country_code"),
            @Result(property = "sortOrder", column = "sort_order"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime")})
    public CreditZone getCreditZone(CreditZone creditZone);

    /**
     * Delete creditZone
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_credit_zone WHERE id = #{id}")
    public Integer deleteCreditZone(Integer id);
}
