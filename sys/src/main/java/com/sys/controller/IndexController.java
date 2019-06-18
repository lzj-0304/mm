package com.sys.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.sys.interceptors.LoginInterceptor;

@Before(LoginInterceptor.class)
public class IndexController extends Controller {

    /**
     * 后台主页
     */
    public void index(){
        set("ctx",getRequest().getContextPath());
        render("index.ftl");
    }

    /**
     * 内容页
     */
    public void main(){
        set("ctx",getRequest().getContextPath());
        render("main.ftl");
    }


    /**
     * 登录页
     */
    @Clear(LoginInterceptor.class)
    public void login(){
        set("ctx",getRequest().getContextPath());
        render("login.ftl");
    }



}
