package com.sys.routes;

import com.jfinal.config.Routes;
import com.sys.controller.UserController;

public class UserRoute extends Routes {
    @Override
    public void config() {
        // 用户中心路由
        setBaseViewPath("/views");
        add("/user", UserController.class);
    }
}
