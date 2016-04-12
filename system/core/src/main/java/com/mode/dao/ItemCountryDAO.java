package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.ItemCountry;

/**
 * Created by zhaoweiwei on 16/3/18.
 */
public interface ItemCountryDAO {

    /**
     * Create itemCountry
     *
     * @param itemCountry
     * @return
     */
    @Insert("INSERT INTO md_item_country (item_id, country_code, ctime)" +
            "VALUES (#{itemId}, #{countryCode}, #{ctime})")
    public Integer createItemCountry(ItemCountry itemCountry);


    /**
     * Update itemCountry
     *
     * @param itemCountry
     * @return
     */
    @Update({"<script>",
            "UPDATE md_item_country ",
            "<set>",
            "<if test='itemId != null'> item_id = #{itemId}, </if>",
            "<if test='countryCode != null'> country_code = #{countryCode}, </if>",
            "<if test='ctime != null'> ctime = #{ctime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateItemCountry(ItemCountry itemCountry);


    /**
     * Get itemCountry
     *
     * @param itemCountry
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_item_country ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='itemId != null'> AND item_id = #{itemId} </if>",
            "<if test='countryCode != null'> AND country_code = #{countryCode} </if>",
            "<if test='ctime != null'> AND ctime = #{ctime} </if>",
            "</otherwise>",
            "</choose>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "itemId", column = "item_id"),
            @Result(property = "countryCode", column = "country_code"),
            @Result(property = "ctime", column = "ctime")})
    public ItemCountry getItemCountry(ItemCountry itemCountry);

    /**
     * Delete itemCountry
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_item_country WHERE id = #{id}")
    public Integer deleteItemCountry(Integer id);
}
