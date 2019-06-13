package com.lbt.routes;

import com.lbt.controller.UserController;
import com.jfinal.config.Routes;

public class UserRoute extends Routes {
    @Override
    public void config() {
        add("/user", UserController.class);
    }
}
