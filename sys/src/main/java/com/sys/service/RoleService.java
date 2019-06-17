package com.sys.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.sys.common.model.Role;
import com.sys.constant.SysConstant;
import com.sys.query.RoleQuery;
import com.sys.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class RoleService {

    private Role roleDao = new Role().dao();// 只允许调用查询方法






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
}
