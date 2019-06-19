package com.sys.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sys.common.model.Module;
import com.sys.common.model.Permission;
import com.sys.constant.SysConstant;
import com.sys.query.ModuleQuery;
import com.sys.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ModuleService {
    private Module moduleDao = new Module().dao();

    private Permission permissionDao = new Permission().dao();





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


    public Map<String,Object> moduleList(ModuleQuery moduleQuery){
        StringBuffer sb =new StringBuffer(" from t_module where  isValid=1 ");
        List params =new ArrayList();
        Map<String,Object> result = new HashMap<String,Object>();
        if(StringUtils.isNotBlank(moduleQuery.getModuleName())){
            sb.append(" and  title like concat('%',?,'%') ");
            params.add(moduleQuery.getModuleName());
        }
        if(null != moduleQuery.getPid()){
            sb.append(" and  parentId = ? ");
            params.add(moduleQuery.getPid());
        }
        if(StringUtils.isNotBlank(moduleQuery.getOptValue())){
            sb.append(" and  optValue like concat(?,'%') ");
            params.add(moduleQuery.getOptValue());
        }
        if(null !=moduleQuery.getGrade()){
            sb.append(" and  grade = ? ");
            params.add(moduleQuery.getGrade());
        }
        Page<Module> page = moduleDao.paginate(moduleQuery.getPage(),
                moduleQuery.getLimit(), "select * ",sb.toString(),params.toArray());
        result.put("count",page.getTotalRow());
        result.put("data",page.getList());
        result.put("code",0);
        result.put("msg","");
        return result;
    }


    public Module queryModuleById(Integer mid) {
        return moduleDao.findById(mid);
    }

    public void saveOrUpdateRole(Module module) {
        /**
         *  1.资源名 操作码非空
         *  2.同一层级 资源名唯一
         */
        Integer mid = module.getId();
        module.setUpdateDate(new Date());
        if(module.getGrade()==0){
            // 一级菜单 设置pid=0
            module.setParentId(0);
        }
        if(null == mid){
            module.setCreateDate(new Date());
            module.setIsValid(1);
            module.save();
        }else{
            module.update();
        }
    }

    public void del(Integer mid) {
        /**
         * 1.当前记录是否存在
         * 2.如果为1 2 级层级 是否存在子菜单
         * 3.执行删除
         */
        Module module= moduleDao.findById(mid);
        AssertUtil.isTrue(null==module,"待删除记录不存在!");
       List<Module> modules= moduleDao.find("select * from t_module where " +
                       "parentId=?",mid);
       AssertUtil.isTrue(null!=modules&&modules.size()>0,"存在子级菜单，不可删除!");
       module.setIsValid(0);
       AssertUtil.isTrue(!module.update(), SysConstant.OPS_FAILED_MSG);
    }
}
