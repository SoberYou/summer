package com.cq.summer.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author summer
 * @Date 2018-08-10 14:39
 * @Description 配置多数据源
 **/
@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.apple")
    public DataSource APPLEDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.banana")
    public DataSource BANANADataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.cherry")
    public DataSource CHERRYDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public DynamicDataSource dataSource(DataSource APPLEDataSource, DataSource BANANADataSource, DataSource CHERRYDataSource){
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceNames.APPLE, APPLEDataSource);
        targetDataSource.put(DataSourceNames.BANANA, BANANADataSource);
        targetDataSource.put(DataSourceNames.CHERRY, CHERRYDataSource);
        return new DynamicDataSource(APPLEDataSource, targetDataSource);
    }
}
