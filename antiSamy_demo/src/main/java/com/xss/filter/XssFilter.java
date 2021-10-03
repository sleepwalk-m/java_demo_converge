package com.xss.filter;

import com.xss.wrapper.XssRequestWrapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*
*过滤所有提交到服务器的请求参数
* 通常来说 使用@WebFilter注解来配置过滤器，这里采用的手动配置方式 配合启动类注解@ServletComponentScan(指定扫描filter包)
*/
@WebFilter(filterName = "xssFilter",urlPatterns = "/*")
@Order(1)
public class XssFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        //传入重写后的Request
        filterChain.doFilter(new XssRequestWrapper(request),servletResponse);
    }
}
