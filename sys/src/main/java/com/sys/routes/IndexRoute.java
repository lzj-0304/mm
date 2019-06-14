package com.sys.routes;

import com.jfinal.config.Routes;
import com.sys.controller.IndexController;

public class IndexRoute extends Routes {
    @Override
    public void config() {
        setBaseViewPath("/views");
        // 首页路由
        add("/", IndexController.class);
    }
}
