package com.sys.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.sys.common.model.Module;
import com.sys.constant.SysConstant;
import com.sys.exceptions.ParamException;
import com.sys.interceptors.LoginInterceptor;
import com.sys.model.ResultInfo;
import com.sys.query.ModuleQuery;
import com.sys.service.ModuleService;

import java.util.List;

@Before(LoginInterceptor.class)
public class ModuleController extends Controller {

    @Inject
    ModuleService moduleService;
    public void index(){
        setAttr("ctx",getRequest().getContextPath());
        render("moduleList.ftl");
    }

    public void allModules(@Para(value = "roleId")Integer roleId){
        List<Module> modules=moduleService.queryAllModules03(roleId);
        renderJson(modules);
    }


    public void moduleList(){
        ModuleQuery moduleQuery=getBean(ModuleQuery.class,"");
        renderJson(moduleService.moduleList(moduleQuery));
    }

    public void moduleUpdate(){
        set("module",moduleService.queryModuleById(getParaToInt("mid")));
        set("ctx",getRequest().getContextPath());
        render("moduleUpdate.ftl");
    }

    public void saveOrUpdate(){
        ResultInfo resultInfo=new ResultInfo();
        try {
            moduleService.saveOrUpdateRole(getBean(Module.class,"")); ;
        } catch (ParamException e) {
            e.printStackTrace();
            resultInfo.setCode(e.getErrorCode());
            resultInfo.setMsg(e.getErrorMsg());
        }catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(SysConstant.OPS_FAILED_CODE);
            resultInfo.setMsg(SysConstant.OPS_FAILED_MSG);
        }
        renderJson(resultInfo);
    }



    public void del(){
        ResultInfo resultInfo=new ResultInfo();
        try {
            moduleService.del(getParaToInt("mid"));
        } catch (ParamException e) {
            e.printStackTrace();
            resultInfo.setCode(e.getErrorCode());
            resultInfo.setMsg(e.getErrorMsg());
        }catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(SysConstant.OPS_FAILED_CODE);
            resultInfo.setMsg(SysConstant.OPS_FAILED_MSG);
        }
        renderJson(resultInfo);
    }










}
