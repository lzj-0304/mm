package com.sys;

import com.jfinal.config.*;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.sys.common.model._MappingKit;
import com.sys.routes.IndexRoute;
import com.sys.routes.UserRoute;

public class Starter extends JFinalConfig {

    public static void main(String[] args) {
        UndertowServer.start(Starter.class);
    }
    @Override
    public void configConstant(Constants me) {
        //开发模式
        me.setDevMode(true);
        // 设置视图类型
        me.setViewType(ViewType.FREE_MARKER);
        // 加载配置
        PropKit.use("config.properties");
        // 开启注入功能
        me.setInjectDependency(true);
    }

    @Override
    public void configRoute(Routes me) {
        me.add(new IndexRoute());
        me.add(new UserRoute());
    }

    @Override
    public void configEngine(Engine me) {
    }

    @Override
    public void configPlugin(Plugins me) {
        DruidPlugin dp =new DruidPlugin(PropKit.get("jdbc.url"),PropKit.get("jdbc.user"),
                PropKit.get("jdbc.password"));
        me.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        me.add(arp);
        _MappingKit.mapping(arp);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new SessionInViewInterceptor(true));
    }

    @Override
    public void configHandler(Handlers me) {
    }
}
