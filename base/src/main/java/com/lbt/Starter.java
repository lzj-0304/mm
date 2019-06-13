package com.lbt;

import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.lbt.common.model._MappingKit;
import com.lbt.routes.HelloRoute;
import com.lbt.routes.ParamsRoute;
import com.lbt.routes.RenderRoute;
import com.lbt.routes.UserRoute;

public class Starter extends JFinalConfig {

    public static void main(String[] args) {
        UndertowServer.start(Starter.class);
    }

    // 常量配置方法
    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
    }

    // 访问路由
    @Override
    public void configRoute(Routes routes) {
        /*routes.add("/", HelloController.class);
        routes.add("/login/login", HelloController.class);
        routes.add("/test/test", HelloController.class);
        routes.add("/t01/test02", HelloController.class);*/
        // 路由模块化
        routes.add(new UserRoute());
        routes.add(new HelloRoute());
        routes.add(new ParamsRoute());
        routes.add(new RenderRoute());
    }


    //configEngine
    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin dp =new DruidPlugin("jdbc:mysql://localhost/lbt_pm?characterEncoding=utf8","root","root");
        plugins.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        plugins.add(arp);
        _MappingKit.mapping(arp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }

    static Prop p;
    static void loadConfig() {
        if (p == null) {
            p = PropKit.useFirstFound("config.txt");
        }
    }
    public static DruidPlugin createDruidPlugin() {
        loadConfig();
        return new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));
    }
}
