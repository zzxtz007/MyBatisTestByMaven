package top.haha233.smbms.service;

import top.haha233.smbms.model.bo.UserBo;
import top.haha233.smbms.model.po.UserPo;

import java.util.List;

public interface UserService {
	Integer add(UserPo user);

	Integer delete(UserPo u);

	Integer count();

	List<UserBo> queryAll();

	List<UserBo> queryAllByName(String userName);

	List<UserBo> queryRListByName(String userName);

	List<UserBo> queryUserAddressByUserId(String id);
}
