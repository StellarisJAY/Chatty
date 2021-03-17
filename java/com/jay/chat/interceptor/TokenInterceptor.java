package com.jay.chat.interceptor;

import com.alibaba.fastjson.JSON;
import com.jay.chat.model.CommonResult;
import com.jay.chat.util.TokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token == null || TokenUtil.isTokenExpired(token)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(new CommonResult(403, "未授权访问")));
            return false;
        };
        return true;
    }
}
