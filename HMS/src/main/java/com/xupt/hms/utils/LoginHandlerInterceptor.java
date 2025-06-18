package com.xupt.hms.utils;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginHandlerInterceptor implements HandlerInterceptor {
    // 目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 静态资源放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Object user = request.getSession().getAttribute("user");
        Object admin = request.getSession().getAttribute("admin");
        // 如果获取的request的session中的user参数为空（未登录），就返回登录页，否则放行访问
        if (admin == null && user == null) {
            // 未登录，给出错误信息，
            request.setAttribute("user", "无权限请先登录");
            // 获取request返回页面到登录页
            request.getRequestDispatcher("/userLogin").forward(request, response);
            return false;
        } else {
            // 已登录，放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}