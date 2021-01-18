package com.mp.demo.controller;


import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.mp.demo.config.ChProperties;
import com.mp.demo.utils.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Tiger 2018/10/10.
 */
@RestController
@RequestMapping("/apollo")
public class ApolloDemoController {

    /**
     * 从apollo获取配置信息
     * */
    @ApolloConfig
    private Config config;

    @GetMapping("/read_demo")
    public Properties apolloReadDemo(){

        /**
         * 得到当前app.id中的配置
         * */
        Set<String> set = config.getPropertyNames();
        for (String key : set) {
            String value = config.getProperty(key, "1");
            System.out.println(key + "==" + value);
        }

        for(String key : set){
            PropertiesUtils.properties.setProperty(key,config.getProperty(key,null));
        }


        for(String key : PropertiesUtils.properties.stringPropertyNames()){
            System.out.println(key+">>>"+PropertiesUtils.properties.getProperty(key));
        }

        return PropertiesUtils.properties;
    }
}