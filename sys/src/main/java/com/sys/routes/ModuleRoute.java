package com.sys.routes;

import com.jfinal.config.Routes;
import com.sys.controller.ModuleController;

public class ModuleRoute extends Routes {
    @Override
    public void config() {
        // 用户中心路由
        setBaseViewPath("/views");
        add("/module", ModuleController.class);
    }
}
