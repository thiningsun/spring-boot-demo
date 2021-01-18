package com.mp.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.concurrent.Callable;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

/**
 * swagger config
 * @author caosong
 * @since 2019-08-14
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    private static final String NAME = "track-management";

    @Bean
    public Docket studentRecApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(NAME)
                .apiInfo(apiInfo())
                .select()
                .apis(basePackage("com.mp.demo"))
                .build()
                // devnote 非常重要，正确显示响应
                .genericModelSubstitutes(Callable.class)
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(NAME)
                .description(NAME)
                .version("1.0.0")
                .build();
    }

}
