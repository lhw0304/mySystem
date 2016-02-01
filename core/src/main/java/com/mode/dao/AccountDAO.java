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
    @Insert("INSERT INTO md_account (username, password, ctime, utime, locked, role, status) " +
            "VALUES (#{username}, #{password}, #{ctime}, #{utime}, #{locked}, #{role}, #{status})")
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

    /**
     * list account to check
     *
     * @return
     */
    @Select("SELECT * FROM md_account WHERE locked = 1")
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
    public List<Account> listMerchant();

    /**
     * list account to check
     *
     * @return
     */
    @Select("SELECT * FROM md_account WHERE role like '%STYLIST%'")
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
    public List<Account> listStylists();


    @Update("UPDATE md_account " +
            "SET password = #{password} " +
            "WHERE user_id = #{userId}")
    public Integer updatePassword(@Param("userId") Integer userId,
                                  @Param("password") String password);


    /**
     * List user id of some role
     *
     * @param role
     * @return
     */
    @Select("SELECT user_id FROM md_account WHERE role like #{role} ")
    public List<Integer> listUserIdOfSomeRole(@Param("role") String role);


    @Update("UPDATE md_account SET role = #{role} WHERE user_id = #{userId}")
    public Integer updateUserRole(@Param("userId") Integer userId,
                                  @Param("role") String role);

    @Select("SELECT username FROM md_account " +
            "WHERE role = 'USER' " +
            "AND user_id > #{startUserId} AND user_id < #{endUserId} " +
            "AND username LIKE #{emailPattern} ")
    public List<String> listUsername(@Param("startUserId") String startUserId,
                                     @Param("endUserId") String endUserId,
                                     @Param("emailPattern") String emailPattern);
}