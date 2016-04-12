package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.Item;

/**
 * Created by zhaoweiwei on 16/3/18.
 */
public interface ItemDAO {

    /**
     * Create item
     *
     * @param item
     * @return
     */
    @Insert("INSERT INTO md_item (type, item_name, brand_id, default_image, " +
            "extra_image, description, price, on_sale, sale_time, sale_price, sale_percent, " +
            "sku, size, color, product_link, status, views, saves, shares, ctime, utime) " +
            "VALUES (#{type}, #{itemName}, #{brandId}, #{defaultImage}, #{extraImage}, " +
            "#{description}, #{price}, #{onSale}, #{saleTime}, #{salePrice}, #{salePercent}, " +
            "#{sku}, #{size}, #{color}, #{productLink}, #{status}, #{views}, #{saves}, " +
            "#{shares}, #{ctime}, #{utime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createItem(Item item);


    /**
     * Update item
     *
     * @param item
     * @return
     */
    @Update({"<script>",
            "UPDATE md_item ",
            "<set>",
            "<if test='type != null'> type = #{type}, </if>",
            "<if test='itemName != null'> item_name = #{itemName}, </if>",
            "<if test='brandId != null'> brand_id = #{brandId}, </if>",
            "<if test='defaultImage != null'> default_image = #{defaultImage}, </if>",
            "<if test='extraImage != null'> extra_image = #{extraImage}, </if>",
            "<if test='description != null'> description = #{description}, </if>",
            "<if test='price != null'> price = #{price}, </if>",
            "<if test='onSale != null'> on_sale = #{onSale}, </if>",
            "<if test='saleTime != null'> sale_time = #{saleTime}, </if>",
            "<if test='salePrice != null'> sale_price = #{salePrice}, </if>",
            "<if test='salePercent != null'> sale_percent = #{salePercent}, </if>",
            "<if test='sku != null'> sku = #{sku}, </if>",
            "<if test='size != null'> size = #{size}, </if>",
            "<if test='color != null'> color = #{color}, </if>",
            "<if test='productLink != null'> product_link = #{productLink}, </if>",
            "<if test='status != null'> status = #{status}, </if>",
            "<if test='views != null'> views = #{views}, </if>",
            "<if test='saves != null'> saves = #{saves}, </if>",
            "<if test='shares != null'> shares = #{shares}, </if>",
            "<if test='ctime != null'> ctime = #{ctime}, </if>",
            "<if test='utime != null'> utime = #{utime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateItem(Item item);


    /**
     * Get item
     *
     * @param item
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_item ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='type != null'> AND type = #{type} </if>",
            "<if test='itemName != null'> AND item_name = #{itemName} </if>",
            "<if test='brandId != null'> AND brand_id = #{brandId} </if>",
            "<if test='defaultImage != null'> AND default_image = #{defaultImage} </if>",
            "<if test='extraImage != null'> AND extra_image = #{extraImage} </if>",
            "<if test='description != null'> AND description = #{description} </if>",
            "<if test='price != null'> AND price = #{price} </if>",
            "<if test='onSale != null'> AND on_sale = #{onSale} </if>",
            "<if test='saleTime != null'> AND sale_time = #{saleTime} </if>",
            "<if test='salePrice != null'> AND sale_price = #{salePrice} </if>",
            "<if test='salePercent != null'> AND sale_percent = #{salePercent} </if>",
            "<if test='sku != null'> AND sku = #{sku} </if>",
            "<if test='size != null'> AND size = #{size} </if>",
            "<if test='color != null'> AND color = #{color} </if>",
            "<if test='productLink != null'> AND product_link = #{productLink} </if>",
            "<if test='status != null'> AND status = #{status} </if>",
            "<if test='views != null'> AND views = #{views} </if>",
            "<if test='saves != null'> AND saves = #{saves} </if>",
            "<if test='shares != null'> AND shares = #{shares} </if>",
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
            @Result(property = "itemName", column = "item_name"),
            @Result(property = "brandId", column = "brand_id"),
            @Result(property = "defaultImage", column = "default_image"),
            @Result(property = "extraImage", column = "extra_image"),
            @Result(property = "description", column = "description"),
            @Result(property = "price", column = "price"),
            @Result(property = "onSale", column = "on_sale"),
            @Result(property = "saleTime", column = "sale_time"),
            @Result(property = "salePrice", column = "sale_price"),
            @Result(property = "salePercent", column = "sale_percent"),
            @Result(property = "sku", column = "sku"),
            @Result(property = "size", column = "size"),
            @Result(property = "color", column = "color"),
            @Result(property = "productLink", column = "product_link"),
            @Result(property = "status", column = "status"),
            @Result(property = "views", column = "views"),
            @Result(property = "saves", column = "saves"),
            @Result(property = "shares", column = "shares"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime")})
    public Item getItem(Item item);

    /**
     * Delete item
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_item WHERE id = #{id}")
    public Integer deleteItem(Integer id);
}
