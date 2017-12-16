package top.haha233.smbms.service;

import top.haha233.smbms.model.po.UserPo;
import top.haha233.smbms.util.Response;

import javax.servlet.http.HttpSession;


/**
 * @author Ice_Dog
 */
public interface UserService {
	Response add(String userCode, String userName, String userPassword, String gender,
			String birthday, String phone, String address, String userRole);

	Response delete(String id);

	Response modify(String id,String userCode, String userName, String userPassword, String gender,
			String birthday, String phone, String address, String userRole);

	Response count(String userName,String userRole);

	Response query(String userName, String userRole,String pageSize,String pageNum);

	Response queryById(String id);

	Response login(String userCode, String password,HttpSession session);



}
