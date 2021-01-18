package com.mp.demo.controller;

import com.mp.demo.config.ChProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/config")
//@EnableConfigurationProperties(ChProperties.class) 可能需要申明为  @Configuration注解 否则采用Autowired注入方式
public class ConfigurationPropertiesController {


    @Autowired
    private ChProperties chProperties;

    @GetMapping("/test")
    @ResponseBody
    public Map<String, Object> test(){

        Map<String, Object> map = new HashMap<>();
        System.out.println(chProperties.toString());
        System.out.println("111:"+chProperties.getUsername());
        System.out.println("222:"+chProperties.getPassword());
        map.put("userName", chProperties.getUsername());
        map.put("password", chProperties.getPassword());
        return map;
    }
}
