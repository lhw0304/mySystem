package com.mode.init;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by Lei on 10/29/15.
 */
@Configuration
@PropertySource(value = {"classpath:config/thread.properties",
        "classpath:config/jdbc.properties"})
public class PropertiesConfig {

    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}