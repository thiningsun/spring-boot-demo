package com.mp.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author xinghong.gao
 * @date 2020-07-16
 *
 */
@Data
@ConfigurationProperties(prefix = "zm.spring.clickhouse")
@Component
public class ChProperties {

    private String address;

    private String username;

    private String password;

    private String db;

    private Integer socketTimeout;

}
