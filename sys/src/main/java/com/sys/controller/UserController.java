package com.sys.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.sys.common.model.User;
import com.sys.common.model.annoations.SysPermission;
import com.sys.constant.SysConstant;
import com.sys.exceptions.ParamException;
import com.sys.interceptors.LoginInterceptor;
import com.sys.interceptors.PermissionInterceptor;
import com.sys.model.ResultInfo;
import com.sys.model.UserModel;
import com.sys.query.UserQuery;
import com.sys.service.ModuleService;
import com.sys.service.UserService;

import java.util.List;

public class UserController extends Controller {

    @Inject
    UserService userService;

    @Inject
    ModuleService moduleService;
    @Before({LoginInterceptor.class, PermissionInterceptor.class})
    @SysPermission(code = "1010")  //用户管理操作码
    public void index(){
        setAttr("ctx",getRequest().getContextPath());
        render("userList.ftl");
    }


    @Clear(LoginInterceptor.class)
    public void doLogin(){
        ResultInfo resultInfo=new ResultInfo();
        try {
            System.out.println(userService);
            UserModel userModel= userService.doLogin(get("userName"),get(
                    "userPwd"));
            setSessionAttr(SysConstant.USER_INFO,userModel);
            List<String> records= moduleService.queryUserHasModulesByUserId(userModel.getUserId());
            // 查询用户所属角色拥有权限 并放入session
            setSessionAttr(SysConstant.USER_PERMISSIONS, records);
            resultInfo.setResult(userModel);
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

    @Clear(LoginInterceptor.class)
    public  void exit(){
        removeSessionAttr(SysConstant.USER_INFO);
        removeSessionAttr(SysConstant.USER_PERMISSIONS);
        redirect(getRequest().getContextPath()+"/login");
    }


    @Before({LoginInterceptor.class,PermissionInterceptor.class})
    @SysPermission(code = "101010")   // 用户查询
    public void userList(){
        UserQuery userQuery=getBean(UserQuery.class,"");
        System.out.println("-------->"+userQuery);
        renderJson(userService.userList(userQuery));
    }
    @Before({LoginInterceptor.class,PermissionInterceptor.class})
    @SysPermission(code = "101020")  // 用户预览
    public void userUpdate(){
        set("user",userService.queryUserById(getParaToInt("uid")));
        set("ctx",getRequest().getContextPath());
        render("userUpdate.ftl");
    }



    @Before({LoginInterceptor.class,PermissionInterceptor.class})
    @SysPermission(code = "101030")
    public void saveOrUpdate(){
        ResultInfo resultInfo=new ResultInfo();
        try {
            User user = getBean(User.class,"");
            System.out.println("角色id-->"+user.getRoleIds());
            userService.saveOrUpdateUser(user);
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


    /*public void del(@Para(value = "ids[]") Integer[] ids){
        ResultInfo resultInfo=new ResultInfo();
        try {
            // 或者getParaValues("ids[]");
            userService.del(ids);
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
    }*/
    @Before({LoginInterceptor.class,PermissionInterceptor.class})
    @SysPermission(code = "101040")  //用户删除
    public void del(){
        ResultInfo resultInfo=new ResultInfo();
        try {
            userService.del(getParaToInt("userId"));
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
