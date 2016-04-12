package com.mode.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.Transaction;

/**
 * Created by zhaoweiwei on 16/3/21.
 */
public interface TransactionDAO {

    /**
     * Create transaction
     *
     * @param transaction
     * @return
     */
    @Insert("INSERT INTO md_transaction (user_id, type, amount, unit, status,  " +
            "former_balance, comment, payment_channel, payment_account, payment_transaction " +
            "ctime, utime), " +
            "VALUES (#{userId}, #{type}, #{amount}, #{unit}, #{status}, #{formerBalance}, " +
            "#{comment}, #{paymentChannel}, #{paymentAccount}, #{paymentTransaction}, " +
            "#{ctime}, #{utime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id",
            before = false, resultType = Integer.class)
    public Integer createTransaction(Transaction transaction);


    /**
     * Update transaction
     *
     * @param transaction
     * @return
     */
    @Update({"<script>",
            "UPDATE md_transaction ",
            "<set>",
            "<if test='userId != null'> user_id = #{userId}, </if>",
            "<if test='type != null'> type = #{type}, </if>",
            "<if test='amount != null'> amount = #{amount}, </if>",
            "<if test='unit != null'> unit = #{unit}, </if>",
            "<if test='status != null'> status = #{status}, </if>",
            "<if test='formerBalance != null'> former_balance = #{formerBalance}, </if>",
            "<if test='comment != null'> comment = #{comment}, </if>",
            "<if test='paymentChannel != null'> payment_channel = #{paymentChannel}, </if>",
            "<if test='paymentAccount != null'> payment_account = #{paymentAccount}, </if>",
            "<if test='paymentTransaction != null'> payment_transaction = #{paymentTransaction}, </if>",
            "<if test='ctime != null'> ctime = #{ctime}, </if>",
            "<if test='utime != null'> utime = #{utime} </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"})
    public Integer updateTransaction(Transaction transaction);


    /**
     * Get transaction
     *
     * @param transaction
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM md_transaction ",
            "<where>",
            "<choose>",
            "<when test='id != null'> id = #{id} </when>",
            "<otherwise>",
            "<if test='userId != null'> AND user_id = #{userId} </if>",
            "<if test='type != null'> AND type = #{type} </if>",
            "<if test='amount != null'> AND amount = #{amount} </if>",
            "<if test='unit != null'> AND unit = #{unit} </if>",
            "<if test='status != null'> AND status = #{status} </if>",
            "<if test='formerBalance != null'> AND former_balance = #{formerBalance} </if>",
            "<if test='comment != null'> AND comment = #{comment} </if>",
            "<if test='paymentChannel != null'> AND payment_channel = #{paymentChannel} </if>",
            "<if test='paymentAccount != null'> AND payment_account = #{paymentAccount} </if>",
            "<if test='paymentTransaction != null'> AND payment_transaction = " +
                    "#{paymentTransaction} </if>",
            "<if test='ctime != null'> AND ctime = #{ctime} </if>",
            "<if test='utime != null'> AND utime = #{utime} </if>",
            "</otherwise>",
            "</choose>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "type", column = "type"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "unit", column = "unit"),
            @Result(property = "status", column = "status"),
            @Result(property = "formerBalance", column = "former_balance"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "paymentChannel", column = "payment_channel"),
            @Result(property = "paymentAccount", column = "payment_account"),
            @Result(property = "paymentTransaction", column = "payment_transaction"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime")})
    public Transaction getTransaction(Transaction transaction);

    /**
     * Delete transaction
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM md_transaction WHERE id = #{id}")
    public Integer deleteTransaction(Integer id);
}
