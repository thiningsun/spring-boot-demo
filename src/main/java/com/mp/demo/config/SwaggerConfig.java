package com.mp.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

//@Configuration //让容器发现配置,并加载
//@EnableSwagger2 //开始swagger2的配置
public class SwaggerConfig {
     //groupName("A") 用来创建多个组，通过不同的组显示其相关的方法，
    //适合分组开发项目**
   /* @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("ABC");
    }*/


    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean  //配置swagger2的docket bean 实例
    public Docket docket(Environment environment){

        //设置什么环境启动swagger
        Profiles profiles = Profiles.of("dev","test");

        //acceptsProfiles(profiles) **判断启动的环境变量在设定的环境里面吗，生产环境不启动**
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .groupName("酷学Java")
                //enable是否启用swagger,如果false,不启用
                .enable(flag)
                //RequestHandlerSelectors 配置要扫描的方式：请求映射扫描器
                //any()全部扫描
                //none()全不扫描
                //basePackage() 指定包扫描
                //withClassAnnotation(RestController.class) 扫描类上的注解，指定注解的反射对象
                //withMethodAnnotation() 扫描方法上的注解，指定注解的反射对象
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.ku.swaggerdemo.controller"))
                //paths 过滤路径
                //.paths(PathSelectors.ant("/kuang/**"))
                .build();
    }

    //修改API信息
    private ApiInfo apiInfo(){

        //连接人信息
        Contact contact = new Contact("酷学Java", "localhost", "1440653830@qq.com");

        return new ApiInfo(
                "酷学Java",
                "苦尽甘来",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }
}
