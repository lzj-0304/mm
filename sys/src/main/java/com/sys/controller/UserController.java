package com.sys.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.sys.common.model.User;
import com.sys.constant.SysConstant;
import com.sys.exceptions.ParamException;
import com.sys.model.ResultInfo;
import com.sys.model.UserModel;
import com.sys.query.UserQuery;
import com.sys.service.UserService;

public class UserController extends Controller {

    @Inject
    UserService userService;
    public void index(){
        setAttr("ctx",getRequest().getContextPath());
        render("userList.ftl");
    }


    public void doLogin(){
        ResultInfo resultInfo=new ResultInfo();
        try {
            System.out.println(userService);
            UserModel userModel= userService.doLogin(get("userName"),get(
                    "userPwd"));
            setSessionAttr(SysConstant.USER_INFO,userModel);
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


    public  void exit(){
        removeSessionAttr(SysConstant.USER_INFO);
        redirect(getRequest().getContextPath()+"/login");
    }


    public void userList(){
        UserQuery userQuery=getBean(UserQuery.class,"");
        System.out.println("-------->"+userQuery);
        renderJson(userService.userList(userQuery));
    }

    public void userUpdate(){
        set("user",userService.queryUserById(getParaToInt("uid")));
        set("ctx",getRequest().getContextPath());
        render("userUpdate.ftl");
    }




    public void saveOrUpdate(){
        ResultInfo resultInfo=new ResultInfo();
        try {
           userService.saveOrUpdateUser(getBean(User.class,"")); ;
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