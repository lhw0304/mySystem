package com.mode.dao;

import com.mode.entity.Config;
import org.apache.ibatis.annotations.*;

/**
 * Created by Administrator on 2016/3/10.
 */
public interface ConfigDAO {

    @Insert("INSERT INTO md_config(type,attribute_name,attribute_value) VALUES(#{type},#{attributeName}," +
            "#{attributeValue}")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createConfig(Config config);

    @Delete("DELETE FROM md_config WHERE id = #{id}")
    public Integer deleteConfig(@Param("id") Integer id);

    @Select("SELECT * FROM md_config WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "attributeName", column = "attribute_name"),
            @Result(property = "type", column = "type"),
            @Result(property = "attributeValue", column = "attribute_value")})
    public Config getConfig(@Param("id") Integer id);
}
