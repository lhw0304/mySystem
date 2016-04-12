package com.mode.service.data;

import com.mode.entity.Config;

/**
 * Created by Lei on 4/8/16.
 */
public class ConfigData {

    public static Config getConfig(){
        Config config = new Config();

        config.setType("userConfig");
        config.setAttributeName("LuckyDrawCostCredits");
        config.setAttributeValue("20");
        config.setCountryCode("US");

        return config;
    }
}
