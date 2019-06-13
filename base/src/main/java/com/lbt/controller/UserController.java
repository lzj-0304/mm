package com.lbt.controller;

import com.jfinal.core.Controller;
import com.lbt.common.model.User;
import com.lbt.dto.UserDto;

public class UserController extends Controller {

    public void index(){
        renderText("用户主页面");
    }


    public void u01(){
        User user = getBean(User.class);
        System.out.println(user.findById(10));
        System.out.println("---------------");
        user.find("select id,user_name as userName,true_name as trueName from" +
                " t_user").forEach(System.out::println);
        System.out.println(user.deleteById(59));
        renderText("用户查询，删除操作...");
    }

    public void u02(){
        User user = getBean(User.class);
        user.setUserName("super_admin");
        System.out.println(user.save());
        renderText("添加用户.....");
    }

    public void u03(){
        User user = getBean(User.class);
        user=user.findById(71);
        /*user.setUserPwd("123456");
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());*/
        System.out.println(user.update());
        renderText("更新用户.....");
    }

    /**
     * javabean 传参 执行添加操作
     * getModel   属性名必须为数据库字段名 否则报错误
     *    url  user.user_name=xxx&user.user_pwd=zzzz&user.true_name=asasas
     */
    public void u04(){
        User user = getModel(User.class);
        System.out.println(user.toString());
        renderText("更新用户.....");
    }

    /**
     * getBean 方法
     *  url:user.userName=123&user.userPwd=admin&user.phone=324324
     *  属性必须存在set 方法 方可以实现参数赋值操作
     */
    public void u05(){
        //User user = getBean(User.class);
        // 避免使用ModelName前缀传参 ，第二个参数为空串即可 适合api接口传参情况
        User user = getBean(User.class,"");
        System.out.println(user.toString());
        renderText("更新用户.....");
    }

    /**
     * Model 属性字段为非数据库字段时 存在set方法使用getBean方法可以得到参数值  getModel获取不到！！！
     */
    public void u06(){
        User user = getBean(User.class,"");
        System.out.println(user);
        renderText("更新用户.....");
    }

    /**
     * 非实体bean  使用getBean方法  属性存在set方法即可
     */
    public void u07(){
        UserDto userDto = getBean(UserDto.class,"");
        System.out.println(userDto);
        renderText("更新用户.....");
    }


    public void u08(){
        User user = getBean(User.class);
        System.out.println(user);
        renderJson(user);
    }
}
