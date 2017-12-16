package top.haha233.smbms.dal;


import org.apache.ibatis.annotations.Param;
import top.haha233.smbms.model.bo.RoleBo;

import java.util.List;

public interface RoleMapper {
	List<RoleBo> queryAllRole();
	RoleBo query(@Param("userName") String userName,@Param("passWord") String passWord);
}
