package com.lbt.routes;

import com.lbt.controller.RenderController;
import com.jfinal.config.Routes;

public class RenderRoute extends Routes {
    @Override
    public void config() {
        setBaseViewPath("/page");
        // 访问模块根路径 ip:port/news
        add("/news", RenderController.class);
    }
}
