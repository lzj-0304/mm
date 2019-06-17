package com.sys.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.sys.common.model.Role;
import com.sys.constant.SysConstant;
import com.sys.exceptions.ParamException;
import com.sys.model.ResultInfo;
import com.sys.query.RoleQuery;
import com.sys.service.RoleService;

public class RoleController extends Controller {

    @Inject
    RoleService roleService;
    public void index(){
        setAttr("ctx",getRequest().getContextPath());
        render("roleList.ftl");
    }






    public void roleList(){
        RoleQuery roleQuery=getBean(RoleQuery.class,"");
        renderJson(roleService.roleList(roleQuery));
    }

    public void roleUpdate(){
        set("role",roleService.queryRoleById(getParaToInt("roleId")));
        set("ctx",getRequest().getContextPath());
        render("roleUpdate.ftl");
    }




    public void saveOrUpdate(){
        ResultInfo resultInfo=new ResultInfo();
        try {
           roleService.saveOrUpdateRole(getBean(Role.class,"")); ;
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
            roleService.del(getParaToInt("roleId"));
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
