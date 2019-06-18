package com.sys.interceptors;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.sys.constant.SysConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        System.out.println("拦截目标方法"+inv.getController()+"--"+inv.getMethodName());
        HttpServletRequest request=inv.getController().getRequest();
        HttpServletResponse response=inv.getController().getResponse();
        if(null== request.getSession().getAttribute(SysConstant.USER_INFO)){
            try {
                response.sendRedirect(request.getContextPath()+"/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            inv.invoke();
        }

    }
}
