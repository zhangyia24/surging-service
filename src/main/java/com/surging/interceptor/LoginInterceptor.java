package com.surging.interceptor;

import com.surging.entity.Administrator;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangdongmao on 2019/1/13.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Administrator administrator=(Administrator) request.getSession().getAttribute("administrator");
        if(administrator!=null){
            return true;
        }
        response.sendRedirect(request.getContextPath()+"/surgingAdmin/index");
        return false;
    }
}
