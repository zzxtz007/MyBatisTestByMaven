package top.haha233.smbms.dal;

import top.haha233.smbms.model.bo.UserBo;
import top.haha233.smbms.model.po.UserPo;

import java.util.List;

/**
 * 关于User的操作方法
 *
 * @author Ice_Dog
 */
public interface UserMapper {
	/**
	 * 添加用户方法
	 *
	 * @param user
	 * @return
	 */
	Integer add(UserPo user);

	/**
	 * 修改用户方法
	 *
	 * @param user
	 * @return
	 */
	Integer modify(UserPo user);

	/**
	 * 删除用户方法
	 *
	 * @param id
	 * @return
	 */
	Integer delete(Integer id);

	/**
	 * 查询用户数量
	 *
	 * @return
	 */
	Integer count();

	/**
	 * 无条件模糊查询
	 *
	 * @return
	 */
	List<UserBo> queryUser();

	/**
	 * 查询用户 根据名字
	 *
	 * @param name
	 * @return
	 */
	List<UserBo> queryUserByName(String name);

	/**
	 * 查询用户 根据各种信息
	 *
	 * @param user
	 * @return
	 */
	List<UserBo> queryUserByObject(UserPo user);

	/**
	 * 查询用户和role 根据各种信息
	 * @param name
	 * @return
	 */
	List<UserBo> queryRListByName(String name);

	/**
	 * 查询用户的地址 根据各种用户id
	 * @param id
	 * @return
	 */
	List<UserBo> queryUserAddressByUserId(Integer id);
}
