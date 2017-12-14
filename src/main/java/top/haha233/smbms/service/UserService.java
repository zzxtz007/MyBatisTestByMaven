package top.haha233.smbms.service;

import top.haha233.smbms.model.po.UserPo;
import top.haha233.smbms.util.Response;


/**
 * @author Ice_Dog
 */
public interface UserService {
	Response add(UserPo user);

	Response delete(UserPo u);

	Response count();

}
