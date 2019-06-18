package com.sys.interceptors;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.sys.common.model.annoations.SysPermission;
import com.sys.constant.SysConstant;
import com.sys.model.ResultInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PermissionInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        HttpServletRequest request=inv.getController().getRequest();
        HttpServletResponse response=inv.getController().getResponse();
        List<String> permissions = (List<String>) request.getSession().getAttribute(SysConstant.USER_PERMISSIONS);
        SysPermission sysPermission=inv.getMethod().getAnnotation(SysPermission.class);
        if(null !=sysPermission){
            /**
             * 获取权限码
             */
           String code= sysPermission.code();
           if(!permissions.contains(code)){
               ResultInfo resultInfo=new ResultInfo();
               resultInfo.setCode(8000);
               resultInfo.setMsg("暂无操作权限!");
               inv.getController().renderJson(resultInfo);
           }else{
               //执行目标方法
               inv.invoke();
           }
        }else {
            inv.invoke();// 没有配置注解 直接放行处理
        }
    }
}
