package com.evervc.springboot.interceptor.springbootinterceptor;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class AppConfiguration implements WebMvcConfigurer {

    @Qualifier("timeInterceptor")
    private final HandlerInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Añadiendo rutas donde se desea usar el interceptor HTTP
        //registry.addInterceptor(timeInterceptor).addPathPatterns("/api/foo", "api/bar");
        // Añadiendo una ruta base donde se desea usar el interceptor HTTP (todas las de la api)
        //registry.addInterceptor(timeInterceptor).addPathPatterns("/api/**");
        // Excluyendo rutas donde no usar el interceptor HTTP
        registry.addInterceptor(timeInterceptor).excludePathPatterns("/api/bar");
    }
}
