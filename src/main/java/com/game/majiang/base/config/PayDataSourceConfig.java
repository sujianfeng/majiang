package com.game.majiang.base.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(locations = "classpath:db_${spring.profiles.active}.properties", prefix = "datasource.pay")
public class PayDataSourceConfig extends DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        super.initDataSource(dataSource);
        return dataSource;
    }
}
