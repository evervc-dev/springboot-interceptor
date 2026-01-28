package com.evervc.springboot.interceptor.springbootinterceptor.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    // Logger de slf4j
    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod controller = (HandlerMethod) handler;
        logger.info("LoadingTimeInterceptor: preHandle() entrando a [" + controller.getMethod().getName() + "]");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerMethod controller = (HandlerMethod) handler;
        logger.info("LoadingTimeInterceptor: postHandle() saliendo de [" + controller.getMethod().getName() + "]");
    }
}
