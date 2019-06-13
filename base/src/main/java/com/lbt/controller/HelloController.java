package com.lbt.controller;

import com.jfinal.core.Controller;

public class HelloController extends Controller {

    public void index() {
        render("index.html");
    }

    public void login() {
        render("page/login/login.html");
    }

    public void test() {
        renderText("这是测试页面。。。。。");
    }
    public void test02() {
        renderJson("{\"userId\":10}");
    }

}
