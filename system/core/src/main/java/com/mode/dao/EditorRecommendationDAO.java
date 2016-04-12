package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.EditorRecommendation;

/**
 * Created by zhaoweiwei on 16/3/18.
 */
public interface EditorRecommendationDAO {

    /**
     * Create editorRecommendation
     *
     * @param editorRecommendation
     * @return
     */
    @Insert("INSERT INTO md_editor_recommendation (title, item_id, description, default_image, " +
            "status, author, country_code, sort_order, ctime, utime) " +
            "VALUES (#{title}, #{itemId}, #{description}, #{defaultImage}, #{status}, " +
            "#{author}, #{countryCode}, #{sortOrder}, #{ctime}, #{utime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createEditorRecommendation(EditorRecommendation editorRecommendation);


    /**
     * Update editorRecommendation
     *
     * @param editorRecommendation
     * @return
     */
    @Update({"<script>",
            "UPDATE md_editor_recommendation ",
            "<set>",
            "<if test='title != null'> title = #{title}, </if>",
            "<if test='itemId != null'> item_id = #{itemId}, </if>",
            "<if test='description != null'> description = #{description}, </if>",
            "<if test='defaultImage != null'> default_image = #{defaultImage}, </if>",
            "<if test='status != null'> status = #{status}, </if>",
            "<if test='author != null'> author = #{author}, </if>",
            "<if test='countryCode != null'> country_code = #{countryCode}, </if>",
            "<if test='sortOrder != null'> sort_order = #{sortOrder}, </if>",
            "<if test='ctime != null'> ctime = #{ctime}, </if>",
            "<if test='utime != null'> utime = #{utime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateEditorRecommendation(EditorRecommendation editorRecommendation);


    /**
     * Get editorRecommendation
     *
     * @param editorRecommendation
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_editor_recommendation ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='title != null'> AND title = #{title} </if>",
            "<if test='itemId != null'> AND item_id = #{itemId} </if>",
            "<if test='description != null'> AND description = #{description} </if>",
            "<if test='defaultImage != null'> AND default_image = #{defaultImage} </if>",
            "<if test='status != null'> AND status = #{status} </if>",
            "<if test='author != null'> AND author = #{author} </if>",
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
            @Result(property = "title", column = "title"),
            @Result(property = "itemId", column = "item_id"),
            @Result(property = "description", column = "description"),
            @Result(property = "defaultImage", column = "default_image"),
            @Result(property = "status", column = "status"),
            @Result(property = "author", column = "author"),
            @Result(property = "countryCode", column = "country_code"),
            @Result(property = "sortOrder", column = "sort_order"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime")})
    public EditorRecommendation getEditorRecommendation(EditorRecommendation editorRecommendation);

    /**
     * Delete editorRecommendation
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_editor_recommendation WHERE id = #{id}")
    public Integer deleteEditorRecommendation(Integer id);
}
