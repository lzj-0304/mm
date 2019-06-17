package com.sys.controller;

import com.jfinal.core.Controller;
import com.sys.constant.SysConstant;
import com.sys.model.UserModel;

public class IndexController extends Controller {

    /**
     * 后台主页
     */
    public void index(){
        set("ctx",getRequest().getContextPath());
        System.out.println((UserModel)getSessionAttr(SysConstant.USER_INFO));
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
    public void login(){
        set("ctx",getRequest().getContextPath());
        render("login.ftl");
    }



}
