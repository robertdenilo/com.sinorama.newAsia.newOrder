package com.sino.newasia.neworder.Servlet;


import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.core.annotation.Order;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * druid过滤器.
 */
@Order(1)
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = {
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid2/*") })  // 忽略资源
public class DruidStatFilter extends WebStatFilter {

}
