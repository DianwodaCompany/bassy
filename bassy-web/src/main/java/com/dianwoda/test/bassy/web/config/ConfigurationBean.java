package com.dianwoda.test.bassy.web.config;

import com.dianwoda.test.bassy.web.support.ResponseBodyWrapFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/** registry bean auto here
 * @author lichengkai
 * 2018 - 04 - 28 : 15:56
 * Copyright(c) for dianwoda
 */
@Configuration
public class ConfigurationBean {
    @Bean
    public ResponseBodyWrapFactoryBean getResponseBodyWrap() {
        return  new ResponseBodyWrapFactoryBean();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
