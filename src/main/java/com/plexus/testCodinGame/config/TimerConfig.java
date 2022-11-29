package com.plexus.testCodinGame.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class TimerConfig {

    private final static Logger Log = LoggerFactory.getLogger(TimerConfig.class);

    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> executionTimeLoggingFilter(){
        return new FilterRegistrationBean<OncePerRequestFilter>() {{
            setOrder(OrderedFilter.REQUEST_WRAPPER_FILTER_MAX_ORDER);
            setFilter(new OncePerRequestFilter() {

                @Override
                protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
                    StopWatch watch = new StopWatch();
                    watch.start();
                    try {
                        chain.doFilter(req, res);
                    } finally {
                        watch.stop();
                        //log the time
                        Log.info("REQUEST: {} completed within {} ms", req.getRequestURI(), watch.getTotalTimeMillis());
                    }

                }
            });
        }};
    }
}
