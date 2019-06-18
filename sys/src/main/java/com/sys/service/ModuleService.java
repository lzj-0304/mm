package com.sys.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.sys.common.model.Module;
import com.sys.common.model.Permission;

import java.util.ArrayList;
import java.util.List;

public class ModuleService {
    private Module moduleDao = new Module().dao();

    private Permission permissionDao = new Permission().dao();


    /*public List<Module> queryAllModules() {
        String sql = "select id, title  from t_module where " +
                " parentId = ? ";
        // 第一级菜单
          List<Module>  modules = moduleDao.find(sql, 0);
        if (null != modules && modules.size() > 0) {
            for (Module module1 : modules) {
                // 第二级
                List<Module> temp1 = moduleDao.find(sql, module1.getId());
                if(null !=temp1 && temp1.size()>0){
                    module1.setChildren(temp1);
                    // 第三极
                    for(Module module2 :temp1){
                        List<Module> temp2 = moduleDao.find(sql,
                                module2.getId());
                        if(null !=temp2 && temp2.size()>0){
                            module2.setChildren(temp2);
                        }
                    }
                }
            }
        }
        return modules;
    }*/


    /**
     * 无角色时 资源tree 展示
     * @return
     */
   /* public List<Module> queryAllModules02() {
        String sql = "select id,parentId as pId, title as name  from t_module" +
                " where " +
                "isValid=1 ";
        return moduleDao.find(sql);
    }*/

   /*
    显示指定角色资源tree
    */
    public List<Module> queryAllModules03(Integer roleId) {
        List<Module> modules=null;
        String sql = "select id,parentId as pId, title as name  from t_module" +
                " where " +
                "isValid=1 ";
        modules=moduleDao.find(sql);
        if(null !=roleId){
            /**
             * 查询角色对应权限
             */
            List<Permission> permissions= permissionDao.find("select moduleId" +
                    " from t_permission " +
                    " where roleId=?",roleId);
            if(null!=permissions && permissions.size()>0){
                for(Permission p:permissions){
                    for(Module m:modules){
                        if(p.getModuleId().equals(m.getId())){
                            m.setChecked(true);
                        }
                    }
                }
            }
        }
        return  modules;
    }



    public List<String> queryUserHasModulesByUserId(Integer userId){
        List<String> results=null;
        List<Record> records =Db.find("select m.optValue from t_user_role ur " +
                ", t_permission p ,t_module m where ur.roleId = p.roleId " +
                " and p.moduleId=m.id and ur.userId=?",userId);
        if(null !=records && records.size()>0){
            results=new ArrayList<String>();
            List<String> finalResults = results;
            records.forEach(r->{
                finalResults.add(r.getStr("optValue"));
            });
        }
        return results;
    }




}
