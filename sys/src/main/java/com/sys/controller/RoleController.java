package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.sys.common.model.Role;
import com.sys.constant.SysConstant;
import com.sys.exceptions.ParamException;
import com.sys.interceptors.LoginInterceptor;
import com.sys.model.ResultInfo;
import com.sys.query.RoleQuery;
import com.sys.service.RoleService;

@Before(LoginInterceptor.class)
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

    public void allRoles(){
        renderText(JSON.toJSONString(roleService.queryAllRoles(getParaToInt(
                "userId"))),"application/json");
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



    public void roleGrant(){
        set("ctx",getRequest().getContextPath());
        set("roleId",getPara("roleId"));
        render("roleGrant.ftl");
    }


    /**
     * 执行授权
     */
    public void doGrant(@Para(value = "mids[]")Integer[] mids,
                        @Para(value = "roleId") Integer roleId){
        System.out.println("角色id-->"+roleId);
        ResultInfo resultInfo=new ResultInfo();
        try {
            roleService.addGrant(mids,roleId);
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
