package com.sino.newasia.neworder.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    //hanlder before calling Controller
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.printf("preHandle calling");
        HttpSession hs = httpServletRequest.getSession();
        Boolean loginStatus = false;
        if(hs.getAttribute("login_status") != null){
            loginStatus = (Boolean)hs.getAttribute("login_status");
        }else{
            httpServletResponse.sendRedirect("../../login");
        }

        return loginStatus;    //if false, stop and intercepted
    }

    //handle after calling Controller, before view renderred.
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle calling");
    }

    //handle after all request，and after view renderred by DispatcherServlet to clean the resource.
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion calling");
    }
}
