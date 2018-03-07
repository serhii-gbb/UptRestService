package com.tech.up.configuration;

import com.tech.up.interceptor.HibernateInterceptor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.util.Collections;

@Configuration
public class HibernateConfig {

    @Autowired
    private HibernateInterceptor hibernateInterceptor;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder factory, DataSource dataSource) {

        return factory.dataSource(dataSource).packages("com/tech/up/entity")
                .properties(Collections.singletonMap("hibernate.ejb.interceptor", hibernateInterceptor))
                .build();
    }

}
