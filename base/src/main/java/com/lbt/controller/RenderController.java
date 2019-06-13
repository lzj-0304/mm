package com.lbt.controller;

import com.jfinal.core.Controller;


/**
 * render 视图渲染方法
 * view 参数最终指向的模板文件规则:
 *    baseViewPath + viewPath + view
 * 其中 view 即为 render(String view) 方法所携带的参数值，
 *    而 baseViewPath、viewPath 则是在路由配置时指定的两个值
 */
public class RenderController extends Controller {
    public void index(){
        render("newsList.html");
    }

    public void r01(){
        renderQrCode("JFinal,给你想要的！！！",600,360);
    }

    public  void r02(){
        System.out.println("文件下载");
        renderFile("test.txt");
    }

}
