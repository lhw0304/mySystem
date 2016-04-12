package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.Account;

/**
 * Created by Lei on 3/11/16.
 */
public interface AccountDAO {


    /**
     * Create account
     *
     * @param account
     * @return
     */
    @Insert("INSERT INTO md_account (username, password, email, mobile, access_token, " +
            "expire_time, role, status, activate_key, reset_password_key, source, ctime, utime) " +
            "VALUES (#{username}, #{password}, #{email}, #{mobile}, #{accessToken}, " +
            "#{expireTime}, #{role}, #{status}, #{activateKey}, #{resetPasswordKey}, " +
            "#{source}, #{ctime}, #{utime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createAccount(Account account);


    /**
     * Update account
     *
     * @param account
     * @return
     */
    @Update({"<script>",
            "UPDATE md_account ",
            "<set>",
            "<if test='username != null'> username = #{username}, </if>",
            "<if test='password != null'> password = #{password}, </if>",
            "<if test='email != null'> email = #{email}, </if>",
            "<if test='mobile != null'> mobile = #{mobile}, </if>",
            "<if test='accessToken != null'> access_token = #{accessToken}, </if>",
            "<if test='expireTime != null'> expire_time = #{expireTime}, </if>",
            "<if test='role != null'> role = #{role}, </if>",
            "<if test='status != null'> status = #{status}, </if>",
            "<if test='activateKey != null'> activate_key = #{activateKey}, </if>",
            "<if test='resetPasswordKey != null'> reset_password_key = #{resetPasswordKey}, </if>",
            "<if test='source != null'> source = #{source}, </if>",
            "<if test='ctime != null'> ctime = #{ctime}, </if>",
            "<if test='utime != null'> utime = #{utime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateAccount(Account account);


    /**
     * Get account
     *
     * @param account
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_account ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='username != null'> AND username = #{username} </if>",
            "<if test='password != null'> AND password = #{password} </if>",
            "<if test='email != null'> AND email = #{email} </if>",
            "<if test='mobile != null'> AND mobile = #{mobile} </if>",
            "<if test='role != null'> AND role like \"%\"#{role}\"%\" </if>",
            "<if test='status != null'> AND status = #{status} </if>",
            "<if test='activateKey != null'> AND activate_key = #{activateKey} </if>",
            "<if test='resetPasswordKey != null'> AND reset_password_key = #{resetPasswordKey} </if>",
            "<if test='source != null'> AND source = #{source} </if>",
            "<if test='ctime != null'> AND ctime = #{ctime} </if>",
            "<if test='utime != null'> AND utime = #{utime} </if>",
            "</otherwise>",
            "</choose>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "accessToken", column = "access_token"),
            @Result(property = "expireTime", column = "expire_time"),
            @Result(property = "role", column = "role"),
            @Result(property = "status", column = "status"),
            @Result(property = "activateKey", column = "activate_key"),
            @Result(property = "resetPasswordKey", column = "reset_password_key"),
            @Result(property = "source", column = "source"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime")})
    public Account getAccount(Account account);

    /**
     * Delete account
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_account WHERE id = #{id}")
    public Integer deleteAccount(Integer id);

}
