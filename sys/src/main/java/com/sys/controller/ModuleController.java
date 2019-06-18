package com.sys.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.sys.common.model.Module;
import com.sys.service.ModuleService;

import java.util.List;

public class ModuleController extends Controller {

    @Inject
    ModuleService moduleService;
    public void index(){
        setAttr("ctx",getRequest().getContextPath());
        render("userList.ftl");
    }

    public void allModules(@Para(value = "roleId")Integer roleId){
        List<Module> modules=moduleService.queryAllModules03(roleId);
        renderJson(modules);
    }

    /* public void test(){
       renderJson(Db.find("select title,titleStyle as icon,url as href from " +
                "t_module "));
    }*/







}
