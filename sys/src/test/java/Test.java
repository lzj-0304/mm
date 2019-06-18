import com.jfinal.plugin.activerecord.Db;
import com.sys.common.model.UserRole;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    @org.junit.Test
    public void test01(){
        String[] roleIds = "2,6,10".split(",");
        List<UserRole> userRoles =new ArrayList<UserRole>();
        for(String roleId:roleIds){
            UserRole userRole =new UserRole();
            userRole.setUserId(10);
            userRole.setRoleId(Integer.parseInt(roleId));
            userRole.setCreateDate(new Date());
            userRole.setUpdateDate(new Date());
            userRoles.add(userRole);
        }
        Db.batchSave(userRoles,userRoles.size());
    }
}
