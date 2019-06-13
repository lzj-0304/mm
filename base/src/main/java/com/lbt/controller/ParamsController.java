package com.lbt.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.lbt.common.model.User;

/**
 * 参数获取形式
 *   url参数格式:v1-v2-v3-v4-...
 *
 *   第一种类型为第一个形参为String的getPara系列方法。该系列方法是对HttpServletRequest.getParameter(String name)的封装，
 *   这类方法都是转调了HttpServletRequest.getParameter(String name)。
 *
 *   第二种类型为第一个形参为int或无形参的getPara系列方法。该系列方法是去获取urlPara中所带的参数值。
 *   getParaMap与getParaNames分别对应HttpServletRequest的getParameterMap与getParameterNames。
 *
 * 获取参数值:
 *   getParam():获取参数整体值
 *       v0-v1-v2-v3-v4-...
 *   getPara()：获取所有参数  0-v1-v2-v3-v4-...
 *   getPara(index):根据索引获取对应值  从0开始
 *   getParaToXXX(index):获取指定参数值并进行转换(int date boolean 等)
 *   getPara() 简写 get()
 */
public class ParamsController extends Controller {

    /**
     * url
     * admin-123456
     * getPara(index) 指定索引获取参数值
     */
    public void index(){
        System.out.println(getPara());
        renderText("params");
    }

    /**
     * url-->userName=admin&userPwd=123
     * getPara()指定形参名获取参数值
     */
    public void params01(){
        System.out.println("-------->"+getPara("userName")+"&"+getPara(
                "userPwd"));
        renderText("hello params");
    }

    /**
     * url-->userName=admin&userPwd=123
     * 字符串形参绑定
     * @param userName
     * @param userPwd
     */
    public void params02(String userName,String userPwd){
        System.out.println("-------->"+userName+"&"+userPwd);
        renderText("hello params");
    }

    /**
     * 参数默认值
     * @param userId
     *url-->userId=123&userName=admin&userPwd=123
     */
    public void params03(@Para(value = "userId",defaultValue = "10")Integer userId
            ,@Para(value = "userName")String userName){
        System.out.println("-------->"+userId+"------->"+userName);
        System.out.println(getParaToInt("userId"));
        renderText("hello params");
    }

    /**
     * 数组参数
     * ids=10&ids=20&ids=30
     */
    public void params04(Integer[] ids){
        for (Integer id : ids) {
            System.out.println(id);
        }
        renderText("hello params");
    }

    /**
     * javabean  前提是没有继承Model 继承Model 需要关联数据库 可使用getBean|getModel方法获取值
     * url参数形式
     * user.userName=admin&user.id=20&user.phone=123456
     */
    public void params05(User user){
        System.out.println(user);
        renderText("hello params");
    }

    public void params06(){
        renderText("hello params");
    }

}
