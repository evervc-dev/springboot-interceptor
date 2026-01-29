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
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    // Logger de slf4j
    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod controller = (HandlerMethod) handler;
        logger.info("LoadingTimeInterceptor: preHandle() entrando a [" + controller.getMethod().getName() + "]");
        // start: Es el tiempo en el que se inicializa el request
        long start = System.currentTimeMillis();
        request.setAttribute("start", start); // Se almacena en el objeto request para poder acceder a el valor en el postHandle

        // Simulando delay con un valor generado aleatoriamente (maximo 500mls)
        Random random = new Random();
        int delay = random.nextInt(500);
        Thread.sleep(delay);
        //return true;

        // Personalizando response en caso de no completarse alguna acción
        // puede usarse para validar que si alguien no se ha loggeado se retorne false y el mensaje
        Map<String, String> json = new HashMap<>();
        json.put("message", "No se ha completado el request");
        json.put("error", "Error 500");

        ObjectMapper maper = new ObjectMapper();
        String jsonStr = maper.writeValueAsString(json);
        response.setContentType("applicarion/json");
        response.setStatus(500);
        response.getWriter().write(jsonStr);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerMethod controller = (HandlerMethod) handler;
        logger.info("LoadingTimeInterceptor: postHandle() saliendo de [" + controller.getMethod().getName() + "]");
        long start = (long) request.getAttribute("start"); // Se obtiene el tiempo de inicio del request
        long end = System.currentTimeMillis(); // Tiempo de respuesra
        long elapsedTime = end - start; // Calcula la diferencia de ms transcurridos
        logger.warn("Tiempo transcurrido: " + elapsedTime + "ms"); // Mostrando el valor
    }
}
