package com.mode.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.mode.entity.UserActionLog;

/**
 * Created by zhaoweiwei on 16/3/21.
 */
public interface UserActionLogDAO {

    /**
     * Create userActionLog
     *
     * @param userActionLog
     * @return
     */
    @Insert("INSERT INTO md_user_action_log (user_id, source_type, source_value, action, " +
            "object_type, object_value, extra_value, ctime) " +
            "VALUES (#{userId}, #{sourceType}, #{sourceValue}, #{action}, " +
            "#{objectType}, #{objectValue}, #{extraValue}, #{ctime})")
    public Integer createUserActionLog(UserActionLog userActionLog);

    /**
     * Batch create userActionLogs
     *
     * @param userActionLogList
     * @return
     */
    @Insert({"<script>",
            "insert into md_user_action_log (user_id, object_value, object_type, action, ",
            "source_type, source_value, extra_value, ctime) ",
            "values ",
            "<foreach collection='array' item='userActionLogList' index='index' ",
            "open='(' separator='),(' close=')'>",
            "#{userActionLogList.userId}, #{userActionLogList.objectValue}, ",
            "#{userActionLogList.objectType}, ",
            "#{userActionLogList.action}, #{userActionLogList.sourceType}, ",
            "#{userActionLogList.sourceValue}, ",
            "#{userActionLogList.extraValue}, #{userActionLogList.ctime}",
            "</foreach>",
            "</script>"})
    public Integer createUserActionLogBatch(UserActionLog[] userActionLogList);


    /**
     * Get user object count by action from startTime to endTime
     *
     * @param userId
     * @param objectType
     * @param action
     * @param startTime
     * @param endTime
     * @return
     */

    @Select({"<script>",
            "select count(distinct object_value) count from md_user_action_log ",
            "WHERE user_id = #{userId} and object_type = #{objectType} and action = #{action} ",
            "<if test='startTime != null'> <![CDATA[ and ctime >= #{startTime} ]]> </if>",
            "<if test='endTime != null'> <![CDATA[ and ctime <= #{endTime} ]]></if>",
            "</script>"})
    public Integer getUserObjectCountByAction(@Param("userId") Integer userId,
                                              @Param("objectType") String objectType,
                                              @Param("action") String action,
                                              @Param("startTime") Long startTime,
                                              @Param("endTime") Long endTime);


    @Select("select source_value, object_type, count(distinct user_id) count " +
            "from md_user_action_log " +
            "where object_type in ('article', 'item', 'modelook', 'video', 'collection') " +
            "and source_type in ('feed', 'headline') " +
            "group by source_value order by count desc")
    @Results({
            @Result(property = "objectType", column = "object_type"),
            @Result(property = "sourceValue", column = "source_value")})
    public List<UserActionLog> listHotObjects();


    @Select({"<script>",
            "select * from md_user_action_log ",
            "WHERE user_id = #{userId} and action = #{action} and object_type = #{objectType}",
            "<if test='startTime != null'> <![CDATA[ and ctime >= #{startTime} ]]> </if>",
            "<if test='endTime != null'> <![CDATA[ and ctime <= #{endTime} ]]></if>",
            " ORDER BY ctime DESC ",
            "</script>"})
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "objectValue", column = "object_value"),
            @Result(property = "objectType", column = "object_type"),
            @Result(property = "action", column = "action"),
            @Result(property = "sourceType", column = "source_type"),
            @Result(property = "sourceValue", column = "source_value"),
            @Result(property = "extraValue", column = "extra_value"),
            @Result(property = "ctime", column = "ctime")})
    public List<UserActionLog> listLogsByActionAndType(@Param("userId") Integer userId,
                                                       @Param("action") String action,
                                                       @Param("objectType") String objectType,
                                                       @Param("startTime") Long startTime,
                                                       @Param("endTime") Long endTime);


    @Select({"<script>",
            "select action, object_type, count(distinct object_value) count from " +
                    "md_user_action_log WHERE user_id = #{userId} ",
            "<if test='startTime != null'> <![CDATA[ and ctime >= #{startTime} ]]> </if>",
            "<if test='endTime != null'> <![CDATA[ and ctime <= #{endTime} ]]></if>",
            " group by action, object_type ",
            "</script>"})
    @Results({
            @Result(property = "action", column = "action"),
            @Result(property = "objectType", column = "object_type"),
            @Result(property = "count", column = "count")})
    public List<UserActionLog> listUserActionCount(@Param("userId") Integer userId,
                                                   @Param("startTime") Long startTime,
                                                   @Param("endTime") Long endTime);


    @Select({"<script>",
            "select object_value, action, count(*) count ",
            "from md_user_action_log ",
            "where object_value in ",
            "<foreach collection='list' item='articleIdList' index='index'",
            "open='(' separator=',' close=')'>",
            "#{articleIdList}",
            "</foreach>",
            "and object_type = 'article' ",
            "and action in ('view', 'share') ",
            "group by object_value, action",
            "</script>"})
    @Results({
            @Result(property = "objectValue", column = "object_value"),
            @Result(property = "action", column = "action"),
            @Result(property = "count", column = "count")})
    public List<UserActionLog> listArticleStats(List<String> articleIdList);


    @Select({"<script>",
            "select object_value, action, count(*) count, count(distinct user_id) count1 ",
            "from md_user_action_log ",
            "where action in ('view', 'share', 'like', 'save') and object_value in ",
            "<foreach collection='objectValueList' item='item' index='index'",
            "open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            " and object_type = 'article' ",
            "group by object_value, action ",
            "union all ",
            "select source_value, 'purchase', count(*) count, count(distinct user_id) count1 ",
            " from md_user_web_log a inner join ",
            "(select distinct object_value, source_value from md_user_action_log ",
            "where object_type= 'item' and source_type = 'article' and source_value in ",
            "<foreach collection='objectValueList' item='item' index='index'",
            "open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            ") b on a.item_id = b.object_value where a.navigation_type = 0 group by source_value",
            "</script>"})
    @Results({
            @Result(property = "objectValue", column = "object_value"),
            @Result(property = "action", column = "action"),
            @Result(property = "count", column = "count"),
            @Result(property = "count1", column = "count1")})
    public List<UserActionLog> listStatsbyObjectValues(@Param("objectValueList")
                                                       List<String> objectValueList);

    @Select({"<script>",
            "select action, count(*) count, count(distinct user_id) count1 ",
            "from md_user_action_log ",
            "where object_value in ",
            "<foreach collection='objectValueList' item='item' index='index'",
            "open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            " and object_type = 'article' ",
            "<if test='startTime != null'> <![CDATA[ and ctime >= #{startTime} ]]> </if>",
            "<if test='endTime != null'> <![CDATA[ and ctime < #{endTime} ]]></if>",
            "group by action ",
            "union all ",
            "select 'purchase', count(*) count, count(distinct user_id) count1 ",
            " from md_user_web_log a inner join ",
            "(select distinct object_value, source_value from md_user_action_log ",
            "where object_type= 'item' and source_type = 'article' and source_value in ",
            "<foreach collection='objectValueList' item='item' index='index'",
            "open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            ") b on a.item_id = b.object_value  ",
            "<where>",
            " a.navigation_type = 0 ",
            "<if test='startTime != null'> <![CDATA[ and a.browse_time >= #{startTime} ]]> </if>",
            "<if test='endTime != null'> <![CDATA[ and a.browse_time < #{endTime} ]]></if>",
            "</where>",
            "</script>"})
    @Results({
            @Result(property = "action", column = "action"),
            @Result(property = "count", column = "count"),
            @Result(property = "count1", column = "count1")})
    public List<UserActionLog> listStatsbyObjectValuesAndRange(@Param("objectValueList")
                                                               List<String> objectValueList,
                                                               @Param("startTime")
                                                               long startTime,
                                                               @Param("endTime")
                                                               long endTime);


    @Select("select * from " +
            "(select DATE_FORMAT(CONVERT_TZ(FROM_UNIXTIME(cast(ctime AS CHAR(10))), " +
            "@@global.time_zone,'+00:00'), '%Y-%m-%d') date, " +
            "action, count(*) count, count(distinct user_id) count1 " +
            "from md_user_action_log " +
            "where action in ('view', 'share', 'like', 'save') " +
            "and object_type = 'article' " +
            "and object_value = #{objectValue} " +
            "and ctime >= #{startTime} " +
            "group by date, action " +
            "union all " +
            "select DATE_FORMAT(CONVERT_TZ(FROM_UNIXTIME(cast(browse_time AS CHAR(10))), " +
            "@@global.time_zone,'+00:00'), '%Y-%m-%d') date, " +
            "'purchase', count(*) count, count(distinct user_id) count1 " +
            " from md_user_web_log a inner join " +
            "(select distinct object_value, source_value from md_user_action_log " +
            "where object_type= 'item' and source_type = 'article' " +
            "and source_value = #{objectValue}  " +
            ") b on a.item_id = b.object_value where browse_time >= #{startTime} group by date) " +
            "a order by date"
    )
    @Results({
            @Result(property = "date", column = "date"),
            @Result(property = "action", column = "action"),
            @Result(property = "count", column = "count"),
            @Result(property = "count1", column = "count1")})
    public List<UserActionLog> listStatsbyObjectValueAndDate(@Param("objectValue")
                                                             Integer objectValue,
                                                             @Param("startTime")
                                                             long startTime);

}
