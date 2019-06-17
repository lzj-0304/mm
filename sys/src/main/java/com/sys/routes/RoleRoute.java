package com.sys.routes;

import com.jfinal.config.Routes;
import com.sys.controller.RoleController;

public class RoleRoute extends Routes {
    @Override
    public void config() {
        // 用户中心路由
        setBaseViewPath("/views");
        add("/role", RoleController.class);
    }
}
