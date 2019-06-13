package com.lbt.routes;

import com.lbt.controller.ParamsController;
import com.jfinal.config.Routes;

public class ParamsRoute extends Routes {
    @Override
    public void config() {
        add("/params", ParamsController.class);
    }
}
