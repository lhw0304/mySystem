package com.mode.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.Account;

public interface AccountDAO {

    /**
     * Find an account by userId
     *
     * @param userId
     * @return Account
     */
    @Select("SELECT * FROM md_account WHERE user_id = #{userId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
            @Result(property = "locked", column = "locked"),
            @Result(property = "accessToken", column = "access_token"),
            @Result(property = "role", column = "role"),
            @Result(property = "status", column = "status"),
            @Result(property = "expireTime", column = "expire_time")})
    public Account getAccountByUserId(Integer userId);

    /**
     * Find an account by username
     *
     * @param username
     * @return Account
     */
    @Select("SELECT * FROM md_account WHERE username = #{username}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
            @Result(property = "locked", column = "locked"),
            @Result(property = "accessToken", column = "access_token"),
            @Result(property = "role", column = "role"),
            @Result(property = "status", column = "status"),
            @Result(property = "expireTime", column = "expire_time")})
    public Account getAccountByUsername(String username);

    /**
     * Create a new account.
     *
     * @param account
     * @return
     */
    @Insert("INSERT INTO md_account (username, password, ctime, utime, locked, role) " +
            "VALUES (#{username}, #{password}, #{ctime}, #{utime}, #{locked}, #{role})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "userId", keyColumn = "user_id",
            before = false, resultType = Integer.class)
    public Integer createAccount(Account account);

    /**
     * Update account access token.
     *
     * @param accessToken
     * @param username
     * @param expireTime
     * @return
     */
    @Update("UPDATE md_account " +
            "SET access_token = #{accessToken}, expire_time = #{expireTime}, utime = #{now} " +
            "WHERE username = #{username}")
    public Integer updateAccessToken(@Param("accessToken") String accessToken,
                                     @Param("username") String username,
                                     @Param("expireTime") Long expireTime,
                                     @Param("now") Long now);

    /**
     * Delete an account
     *
     * @param userid
     * @return
     */

    @Delete("DELETE FROM md_account WHERE user_id = #{userid}")
    public Integer deleteAccount(@Param("userid") Integer userid);

    /**
     * update merchant lock to unlock
     *
     * @param userId
     * @return
     */
    @Update("UPDATE md_account SET locked =#{unLock}  " +
            "WHERE user_id = #{userId}")
    public Integer checkMerchant(@Param("userId") Integer userId,
                                 @Param("unLock") Integer unLock);


    @Update("UPDATE md_account " +
            "SET password = #{password} " +
            "WHERE user_id = #{userId}")
    public Integer updatePassword(@Param("userId") Integer userId,
                                  @Param("password") String password);




    @Update("UPDATE md_account SET role = #{role} WHERE user_id = #{userId}")
    public Integer updateUserRole(@Param("userId") Integer userId,
                                  @Param("role") String role);

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

}