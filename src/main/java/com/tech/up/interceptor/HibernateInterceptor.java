package com.tech.up.interceptor;

import org.hibernate.EmptyInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HibernateInterceptor extends EmptyInterceptor {

    private static final Logger log = LoggerFactory.getLogger(HibernateInterceptor.class);
    private ThreadLocal<Long> queryCount = new ThreadLocal<>();



    public void startCounter() {
        queryCount.set(0L);
    }


    public Long getQueryCount() {
        return queryCount.get();
    }


    public void clearCounter() {
        queryCount.remove();
    }


    @Override
    public String onPrepareStatement(String sql) {
        Long count = queryCount.get();
        if (count != null) {
            queryCount.set(count + 1);
        }
//        log.info(sql);
        return super.onPrepareStatement(sql);
    }

}
