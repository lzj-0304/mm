package com.lbt.routes;

import com.lbt.controller.HelloController;
import com.jfinal.config.Routes;

public class HelloRoute extends Routes {
    @Override
    public void config() {
        add("/", HelloController.class);
    }
}
