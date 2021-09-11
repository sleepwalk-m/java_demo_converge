package com.mask.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author: Mask.m
 * @create: 2021/09/11 20:21
 * @description: 自定义拦截器，进行日志处理
 */
@Slf4j
public class MyLogInterceptor implements HandlerInterceptor {

    // 用本地线程记录controller运行的起始时间
    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 强转为handermethod对象
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();// 获取被拦截的方法对象
        MyLog myLog = method.getAnnotation(MyLog.class);// 获取该方法上的注解
        if (myLog != null){
            // 说明加上了我们自定义的注解，那就进行处理
            log.info("handler：" + method.getName() + " 开始运行");
            long startTime = System.currentTimeMillis();
            THREAD_LOCAL.set(startTime);
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 强转为handermethod对象
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();// 获取被拦截的方法对象
        MyLog myLog = method.getAnnotation(MyLog.class);// 获取该方法上的注解
        if (myLog != null){
            // 说明加上了我们自定义的注解，那就进行处理
            log.info("handler：" + method.getName() + " 处理完成");

            Long startTime = THREAD_LOCAL.get();
            Long endTime = System.currentTimeMillis();
            long time = endTime -startTime;

            String requestURI = request.getRequestURI();
            String methodName = method.getDeclaringClass().getName() + "." +
                    method.getName();
            String methodDesc = myLog.desc();

            log.info("请求uri：" + requestURI);
            log.info("请求方法名：" + methodName);
            log.info("方法描述：" + methodDesc);
            log.info("方法执行时间：" + time + "ms");
        }
    }
}
