package com.sys.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.sys.common.model.User;
import com.sys.common.model.UserRole;
import com.sys.constant.SysConstant;
import com.sys.model.UserModel;
import com.sys.query.UserQuery;
import com.sys.utils.AssertUtil;
import com.sys.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class UserService {

    private User userDao = new User().dao();// 只允许调用查询方法


    public UserModel doLogin(String userName,String userPwd){
        /**
         * 校验参数合法性
         */
        checkUserLoginParams(userName,userPwd);
        /**
         * 查询用户记录是否存在
         */
        User user=userDao.findFirst("select * from t_user where userName =? ",
                userName);
        AssertUtil.isTrue(null==user,"改用户不存在或已注销!");
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(userPwd)),"密码不正确!");
        return buildUserModelInfo(user);
    }

    private void checkUserLoginParams(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"密码不能为空!");
    }

    private UserModel buildUserModelInfo(User user) {
        UserModel userModel=new UserModel();
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        userModel.setUserId(user.getId());
        return userModel;
    }


    public User queryUserById(Integer userId){
        return userDao.findById(userId);
    }



    public Map<String,Object> userList(UserQuery userQuery){
        StringBuffer sb =new StringBuffer(" from t_user where  isDel=0");
        List params =new ArrayList();
        Map<String,Object> result = new HashMap<String,Object>();
        if(StringUtils.isNotBlank(userQuery.getUserName())){
            sb.append(" and  userName like concat('%',?,'%') ");
            params.add(userQuery.getUserName());
        }
        if(StringUtils.isNotBlank(userQuery.getEmail())){
            sb.append(" and  email = ? ");
            params.add(userQuery.getEmail());
        }
        if(StringUtils.isNotBlank(userQuery.getPhone())){
            sb.append(" and  phone = ? ");
            params.add(userQuery.getPhone());
        }
        Page<User> page = userDao.paginate(userQuery.getPage(),
                userQuery.getLimit(), "select * ",sb.toString(),params.toArray());
        result.put("count",page.getTotalRow());
        result.put("data",page.getList());
        result.put("code",0);
        result.put("msg","");
        return result;
    }

    public  void saveOrUpdateUser(User user){
        checkUserParams(user.getUserName(),user.getTrueName(),
                user.getEmail(),user.getPhone());
        Integer userId = user.getId();
        user.setUpdateDate(new Date());
        User  temp=userDao.findFirst("select * from t_user where userName =? ",
                user.getUserName());
        if(null !=userId){
            /**
             * 执行更新
             */
            AssertUtil.isTrue(null!=temp&&!(userId.equals(temp.getId())),
                    "该用户已存在!");
            AssertUtil.isTrue(!user.update(), SysConstant.OPS_FAILED_MSG);
        }else{
            /**
             * 执行添加
             */
            AssertUtil.isTrue(null!=temp,"该用户已存在!");
            user.setCreateDate(new Date());
            user.setUserPwd(Md5Util.encode("123456"));//密码统一123456
            AssertUtil.isTrue(!user.save(), SysConstant.OPS_FAILED_MSG);
        }

        // 关联用户角色
        if(StringUtils.isNotBlank(user.getRoleIds())){
            // 如果为更新 删除用户原有角色
            if(null != userId){
                Db.delete("delete from t_user_role where userId=?",userId);
            }
            //重新添加新的角色
            String[] roleIds = user.getRoleIds().split(",");
            List<UserRole> userRoles =new ArrayList<UserRole>();
            for(String roleId:roleIds){
                UserRole userRole =new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(Integer.parseInt(roleId));
                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());
                userRoles.add(userRole);
            }
            Db.batchSave(userRoles,userRoles.size());
        }


    }
    private void checkUserParams(String userName, String trueName, String email, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(trueName),"真实名称不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(email),"邮箱不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(phone),"手机号不能为空!");
    }


    public void del(Integer userId){
        AssertUtil.isTrue(null == userDao.findById(userId),"待删除记录不存在!");
        Db.update("update t_user set isDel=1 where id = ?",userId);
        // 级联删除用户角色
        Db.update("delete from t_user_role where userId=?",userId);
    }
}
