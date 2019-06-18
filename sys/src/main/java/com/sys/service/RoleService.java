package com.sys.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.sys.common.model.Permission;
import com.sys.common.model.Role;
import com.sys.common.model.UserRole;
import com.sys.constant.SysConstant;
import com.sys.query.RoleQuery;
import com.sys.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class RoleService {

    private Role roleDao = new Role().dao();// 只允许调用查询方法


    private UserRole userRoleDao=new UserRole().dao();





    public Role queryRoleById(Integer roleId){
        return roleDao.findById(roleId);
    }



    public Map<String,Object> roleList(RoleQuery roleQuery){
        StringBuffer sb =new StringBuffer(" from t_role where  isValid=1 ");
        List params =new ArrayList();
        Map<String,Object> result = new HashMap<String,Object>();
        if(StringUtils.isNotBlank(roleQuery.getRoleName())){
            sb.append(" and  roleName like concat('%',?,'%') ");
            params.add(roleQuery.getRoleName());
        }

        Page<Role> page = roleDao.paginate(roleQuery.getPage(),
                roleQuery.getLimit(), "select * ",sb.toString(),params.toArray());
        result.put("count",page.getTotalRow());
        result.put("data",page.getList());
        result.put("code",0);
        result.put("msg","");
        return result;
    }

    public List<Role> queryAllRoles(Integer userId){
       List<Role> roles= roleDao.find("select id ,roleName  " +
               "from " +
                "t_role where isValid=1");
       if(null !=userId){
           List<UserRole> userRoles = userRoleDao.find("select roleId from " +
                   "t_user_role where userId=?",userId);
           if(null!=roles && roles.size()>0){
               if(null !=userRoles && userRoles.size()>0){
                   roles.forEach(r->{
                       userRoles.forEach(ur->{
                           if(r.getId().equals(ur.getRoleId())){
                               r.setSelected("selected");
                           }
                       });
                   });
               }
           }
       }
       return roles;
    }



    public  void saveOrUpdateRole(Role role){
        checkRoleParams(role.getRoleName());
        Integer roleId = role.getId();
        role.setUpdateDate(new Date());
        Role  temp=roleDao.findFirst("select * from t_role where roleName =? ",role.getRoleName());
        if(null !=roleId){
            /**
             * 执行更新
             */
            AssertUtil.isTrue(null!=temp&&!(roleId.equals(temp.getId())),
                    "该角色已存在!");
            AssertUtil.isTrue(!role.update(), SysConstant.OPS_FAILED_MSG);
        }else{
            /**
             * 执行添加
             */
            AssertUtil.isTrue(null!=temp,"该角色已存在!");
            role.setCreateDate(new Date());
            AssertUtil.isTrue(!role.save(), SysConstant.OPS_FAILED_MSG);
        }


    }
    private void checkRoleParams(String roleName) {
        AssertUtil.isTrue(StringUtils.isBlank(roleName),"角色名不能为空!");
    }


    public void del(Integer roleId){
        AssertUtil.isTrue(null == roleDao.findById(roleId),"待删除记录不存在!");
        Db.update(" update t_role set isValid=0 where id = ? ",roleId);
    }

    public void addGrant(Integer[] mids, Integer roleId) {
        /**
         * 角色授权
         *   1.删除角色原有权限 (如果存在权限)
         *   2.添加角色新的权限
         */
        Db.delete("delete from t_permission where roleId=?",roleId);
        /**
         * 执行批量添加
         */
        List<Permission> permissions =new ArrayList<Permission>();
        for (Integer mid:mids){
            Permission permission =new Permission();
            permission.setModuleId(mid);
            permission.setRoleId(roleId);
            permission.setCreateDate(new Date());
            permission.setUpdateDate(new Date());
            permissions.add(permission);
        }
        Db.batchSave(permissions,mids.length);
    }
}
