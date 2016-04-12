package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.UserPrize;

/**
 * Created by zhaoweiwei on 16/3/21.
 */
public interface UserPrizeDAO {

    /**
     * Create userPrize
     *
     * @param userPrize
     * @return
     */
    @Insert("INSERT INTO md_user_prize (type, user_id, source_id, usd, address, " +
            "comment, status, ctime, utime) " +
            "VALUES (#{type}, #{userId}, #{sourceId}, #{usd}, #{address}, " +
            "#{comment}, #{status}, #{ctime}, #{utime})")
    public Integer createUserPrize(UserPrize userPrize);


    /**
     * Update userPrize
     *
     * @param userPrize
     * @return
     */
    @Update({"<script>",
            "UPDATE md_user_prize ",
            "<set>",
            "<if test='type != null'> type = #{type}, </if>",
            "<if test='userId != null'> user_id = #{userId </if>",
            "<if test='sourceId != null'> source_id = #{sourceId}, </if>",
            "<if test='usd != null'> usd = #{usd}, </if>",
            "<if test='address != null'> address = #{address}, </if>",
            "<if test='comment != null'> comment = #{comment}, </if>",
            "<if test='status != null'> status = #{status}, </if>",
            "<if test='ctime != null'> ctime = #{ctime}, </if>",
            "<if test='utime != null'> utime = #{utime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateUserPrize(UserPrize userPrize);


    /**
     * Get userPrize
     *
     * @param userPrize
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_user_prize ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='type != null'> AND type = #{type} </if>",
            "<if test='userId != null'> AND user_id = #{userId} </if>",
            "<if test='sourceId != null'> AND source_id = #{sourceId} </if>",
            "<if test='usd != null'> AND usd = #{usd} </if>",
            "<if test='address != null'> AND address = #{address} </if>",
            "<if test='comment != null'> AND comment = #{comment} </if>",
            "<if test='status != null'> AND status = #{status} </if>",
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
            @Result(property = "userId", column = "user_id"),
            @Result(property = "sourceId", column = "source_id"),
            @Result(property = "usd", column = "usd"),
            @Result(property = "address", column = "address"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "status", column = "status"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime")})
    public UserPrize getUserPrize(UserPrize userPrize);

    /**
     * Delete userPrize
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_user_prize WHERE id = #{id}")
    public Integer deleteUserPrize(Integer id);
}
