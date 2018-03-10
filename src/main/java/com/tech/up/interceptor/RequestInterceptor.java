package com.tech.up.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);
    private ThreadLocal<Long> time = new ThreadLocal<>();


    @Autowired
    private HibernateInterceptor hibernateInterceptor;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        hibernateInterceptor.startCounter();
        time.set(System.currentTimeMillis());
        return true;
    }



    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        long duration = System.currentTimeMillis() - time.get();
        Long queryCount = hibernateInterceptor.getQueryCount();
        time.remove();
        hibernateInterceptor.clearCounter();
        log.info("[Request: {} {}] [Database calls: {}]  [Time: {} ms]",
                request.getMethod(), request.getRequestURI(), queryCount, duration);
    }


    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response,
                                               Object handler) throws Exception {
        //concurrent handling cannot be supported here

        time.remove();
        hibernateInterceptor.clearCounter();
    }

}
