package com.sys.controller;

import com.jfinal.core.Controller;

public class IndexController extends Controller {
    public void index(){
        set("ctx",getRequest().getContextPath());
        render("index.ftl");
    }

    public void main(){
        set("ctx",getRequest().getContextPath());
        render("main.ftl");
    }


    public void login(){
        set("ctx",getRequest().getContextPath());
        render("login.ftl");
    }

}
