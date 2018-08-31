package com.sino.newasia.neworder.Servlet;

import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@Order(10)
@WebFilter(filterName = "myFilter", urlPatterns = "/asiaTour/*", initParams = {
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*") })         //忽略资源
public class MyFilter1 extends WebStatFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         System.out.println("Need to check user priv...");
         chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {}
    @Override
    public void destroy() {}
}
