package com.mode.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.mode.entity.Appsflyer;

/**
 * Created by Lei on 8/27/15.
 */
public interface AppsflyerDAO {

    @Select("select * from md_appsflyer where appsflyer_device_id = #{appsflyer_device_id}")
    @Results({@Result(property = "appsflyer_device_id", column = "appsflyer_device_id")})
    public Appsflyer getAppsflyer(@Param("appsflyer_device_id") String appsflyerDeviceId);

    /**
     * Create appsflyer
     *
     * @param appsflyer
     * @return
     */
    @Insert("INSERT INTO md_appsflyer (" +
            "app_id," +
            "platform," +
            "click_time," +
            "install_time," +
            "agency," +
            "media_source," +
            "campaign," +
            "fb_campaign_name," +
            "fb_campaign_id," +
            "fb_adset_name," +
            "fb_adset_id," +
            "fb_adgroup_name," +
            "fb_adgroup_id," +
            "af_siteid," +
            "cost_per_install," +
            "country_code," +
            "city," +
            "ip," +
            "wifi," +
            "language," +
            "appsflyer_device_id," +
            "customer_user_id," +
            "idfa," +
            "idfv," +
            "mac," +
            "device_name," +
            "device_type," +
            "os_version," +
            "sdk_version," +
            "app_version," +
            "event_type," +
            "event_name," +
            "event_value," +
            "currency," +
            "event_time," +
            "af_sub1," +
            "af_sub2," +
            "af_sub3," +
            "af_sub4," +
            "af_sub5," +
            "click_url," +
            "attribution_type," +
            "http_referrer," +
            "operator," +
            "advertising_id," +
            "android_id," +
            "imei," +
            "device_brand," +
            "device_model," +
            "user_id," +
            "uuid) " +
            "VALUES(" +
            "#{app_id}," +
            "#{platform}," +
            "#{click_time}," +
            "#{install_time}," +
            "#{agency}," +
            "#{media_source}," +
            "#{campaign}," +
            "#{fb_campaign_name}," +
            "#{fb_campaign_id}," +
            "#{fb_adset_name}," +
            "#{fb_adset_id}," +
            "#{fb_adgroup_name}," +
            "#{fb_adgroup_id}," +
            "#{af_siteid}," +
            "#{cost_per_install}," +
            "#{country_code}," +
            "#{city}," +
            "#{ip}," +
            "#{wifi}," +
            "#{language}," +
            "#{appsflyer_device_id}," +
            "#{customer_user_id}," +
            "#{idfa}," +
            "#{idfv}," +
            "#{mac}," +
            "#{device_name}," +
            "#{device_type}," +
            "#{os_version}," +
            "#{sdk_version}," +
            "#{app_version}," +
            "#{event_type}," +
            "#{event_name}," +
            "#{event_value}," +
            "#{currency}," +
            "#{event_time}," +
            "#{af_sub1}," +
            "#{af_sub2}," +
            "#{af_sub3}," +
            "#{af_sub4}," +
            "#{af_sub5}," +
            "#{click_url}," +
            "#{attribution_type}," +
            "#{http_referrer}," +
            "#{operator}," +
            "#{advertising_id}," +
            "#{android_id}," +
            "#{imei}," +
            "#{device_brand}," +
            "#{device_model}," +
            "#{userId}," +
            "#{uuid})")
    public Integer createAppsflyer(Appsflyer appsflyer);

    /**
     * Update appsflyer
     *
     * @param appsflyer
     * @return
     */
    @Update({"<script>",
            "UPDATE md_appsflyer ",
            "<set>",
            "<if test=' app_id != null'>app_id = #{app_id},</if>",
            "<if test=' platform != null'>platform = #{platform},</if>",
            "<if test=' click_time != null'>click_time = #{click_time},</if>",
            "<if test=' install_time != null'>install_time = #{install_time},</if>",
            "<if test=' agency != null'>agency = #{agency},</if>",
            "<if test=' media_source != null'>media_source = #{media_source},</if>",
            "<if test=' campaign != null'>campaign = #{campaign},</if>",
            "<if test=' fb_campaign_name != null'>fb_campaign_name = #{fb_campaign_name},</if>",
            "<if test=' fb_campaign_id != null'>fb_campaign_id = #{fb_campaign_id},</if>",
            "<if test=' fb_adset_name != null'>fb_adset_name = #{fb_adset_name},</if>",
            "<if test=' fb_adset_id != null'>fb_adset_id = #{fb_adset_id},</if>",
            "<if test=' fb_adgroup_name != null'>fb_adgroup_name = #{fb_adgroup_name},</if>",
            "<if test=' fb_adgroup_id != null'>fb_adgroup_id = #{fb_adgroup_id},</if>",
            "<if test=' af_siteid != null'>af_siteid = #{af_siteid},</if>",
            "<if test=' cost_per_install != null'>cost_per_install = #{cost_per_install},</if>",
            "<if test=' country_code != null'>country_code = #{country_code},</if>",
            "<if test=' city != null'>city = #{city},</if>",
            "<if test=' ip != null'>ip = #{ip},</if>",
            "<if test=' wifi != null'>wifi = #{wifi},</if>",
            "<if test=' language != null'>language = #{language},</if>",
            "<if test=' appsflyer_device_id != null'>appsflyer_device_id = #{appsflyer_device_id},</if>",
            "<if test=' customer_user_id != null'>customer_user_id = #{customer_user_id},</if>",
            "<if test=' idfa != null'>idfa = #{idfa},</if>",
            "<if test=' idfv != null'>idfv = #{idfv},</if>",
            "<if test=' mac != null'>mac = #{mac},</if>",
            "<if test=' device_name != null'>device_name = #{device_name},</if>",
            "<if test=' device_type != null'>device_type = #{device_type},</if>",
            "<if test=' os_version != null'>os_version = #{os_version},</if>",
            "<if test=' sdk_version != null'>sdk_version = #{sdk_version},</if>",
            "<if test=' app_version!= null'>app_version = #{app_version},</if>",
            "<if test=' event_type != null'>event_type = #{event_type},</if>",
            "<if test=' event_name != null'>event_name = #{event_name},</if>",
            "<if test=' event_value != null'>event_value = #{event_value},</if>",
            "<if test=' currency != null'>currency = #{currency},</if>",
            "<if test=' event_time != null'>event_time = #{event_time},</if>",
            "<if test=' af_sub1 != null'>af_sub1 = #{af_sub1},</if>",
            "<if test=' af_sub2 != null'>af_sub2 = #{af_sub2},</if>",
            "<if test=' af_sub3 != null'>af_sub3 = #{af_sub3},</if>",
            "<if test=' af_sub4 != null'>af_sub4 = #{af_sub4},</if>",
            "<if test=' af_sub5 != null'>af_sub5 = #{af_sub5},</if>",
            "<if test=' click_url != null'>click_url = #{click_url},</if>",
            "<if test=' attribution_type != null'>attribution_type = #{attribution_type},</if>",
            "<if test=' http_referrer != null'>http_referrer = #{http_referrer},</if>",
            "<if test=' operator != null'>operator = #{operator},</if>",
            "<if test=' advertising_id != null'>advertising_id = #{advertising_id},</if>",
            "<if test=' android_id != null'>android_id = #{android_id},</if>",
            "<if test=' imei != null'>imei = #{imei},</if>",
            "<if test=' device_brand != null'>device_brand = #{device_brand},</if>",
            "<if test=' device_model != null'>device_model = #{device_model},</if>",
            "<if test=' userId != null'>user_id = #{userId},</if>",
            "<if test=' uuid != null'>uuid =#{uuid},</if>",
            "</set>",
            "WHERE appsflyer_device_id = #{appsflyer_device_id}",
            "</script>"})
    public Integer updateAppsflyer(Appsflyer appsflyer);
}
