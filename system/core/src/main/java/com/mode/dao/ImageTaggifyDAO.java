package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.ImageTaggify;

/**
 * Created by zhaoweiwei on 16/3/18.
 */
public interface ImageTaggifyDAO {

    /**
     * Create imageTaggify
     *
     * @param imageTaggify
     * @return
     */
    @Insert("INSERT INTO md_image_taggify (taggified_image, author_id, draft_id, ctime, utime) " +
            "VALUES (#{taggifiedImage}, #{authorId}, #{draftId}, #{ctime}, #{utime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createImageTaggify(ImageTaggify imageTaggify);


    /**
     * Update imageTaggify
     *
     * @param imageTaggify
     * @return
     */
    @Update({"<script>",
            "UPDATE md_image_taggify ",
            "<set>",
            "<if test='taggifiedImage != null'> taggified_image = #{taggifiedImage}, </if>",
            "<if test='authorId != null'> author_id = #{authorId}, </if>",
            "<if test='draftId != null'> draft_id = #{draftId}, </if>",
            "<if test='ctime != null'> ctime = #{ctime}, </if>",
            "<if test='utime != null'> utime = #{utime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateImageTaggify(ImageTaggify imageTaggify);


    /**
     * Get imageTaggify
     *
     * @param imageTaggify
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_image_taggify ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='taggifiedImage != null'> AND taggified_image = #{taggifiedImage} </if>",
            "<if test='authorId != null'> AND author_id = #{authorId} </if>",
            "<if test='draftId != null'> AND draft_id = #{draftId} </if>",
            "<if test='ctime != null'> AND ctime = #{ctime} </if>",
            "<if test='utime != null'> AND utime = #{utime} </if>",
            "</otherwise>",
            "</choose>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "taggifiedImage", column = "taggified_image"),
            @Result(property = "authorId", column = "author_id"),
            @Result(property = "draftId", column = "draft_id"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime")})
    public ImageTaggify getImageTaggify(ImageTaggify imageTaggify);

    /**
     * Delete imageTaggify
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_image_taggify WHERE id = #{id}")
    public Integer deleteImageTaggify(Integer id);
}
