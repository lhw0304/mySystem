package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.Config;

/**
 * Created by zhaoweiwei on 16/3/18.
 */
public interface ConfigDAO {

    /**
     * Create config
     *
     * @param config
     * @return
     */
    @Insert("INSERT INTO md_config (type, attribute_name, attribute_value, country_code) " +
            "VALUES (#{type}, #{attributeName}, #{attributeValue}, #{countryCode})")
    public Integer createConfig(Config config);


    /**
     * Update config
     *
     * @param config
     * @return
     */
    @Update({"<script>",
            "UPDATE md_config ",
            "<set>",
            "<if test='type != null'> type = #{type}, </if>",
            "<if test='attributeName != null'> attribute_name = #{attributeName}, </if>",
            "<if test='attributeValue != null'> attribute_value = #{attributeValue}, </if>",
            "<if test='countryCode != null'> country_code = #{countryCode} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateConfig(Config config);


    /**
     * Get config
     *
     * @param config
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_config ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='type != null'> AND type = #{type} </if>",
            "<if test='attributeName != null'> AND attribute_name = #{attributeName} </if>",
            "<if test='attributeValue != null'> AND attribute_value = #{attributeValue} </if>",
            "<if test='countryCode != null'> AND country_code like \"%\"#{countryCode}\"%\" </if>",
            "</otherwise>",
            "</choose>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "type", column = "type"),
            @Result(property = "attributeName", column = "attribute_name"),
            @Result(property = "attributeValue", column = "attribute_value"),
            @Result(property = "countryCode", column = "country_code")})
    public Config getConfig(Config config);

    /**
     * Delete config
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_config WHERE id = #{id}")
    public Integer deleteConfig(Integer id);
}
