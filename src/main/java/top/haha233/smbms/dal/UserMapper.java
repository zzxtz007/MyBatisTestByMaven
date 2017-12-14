package top.haha233.smbms.dal;

import org.apache.ibatis.annotations.Param;
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
	int add(UserPo user);

	/**
	 * 修改用户方法
	 *
	 * @param user
	 * @return
	 */
	int modify(UserPo user);

	/**
	 * 删除用户方法
	 *
	 * @param id
	 * @return
	 */
	int delete(Integer id);

	/**
	 * 查询用户数量
	 * @param condition
	 * @return
	 */
	int count(@Param("condition") UserPo condition);

	/**根据用户的情况查询用户
	 * @param condition
	 * @param startIndex
	 * @param count
	 * @return
	 */
	List<UserBo> list(@Param("condition") UserPo condition, @Param("startIndex") Integer startIndex,
			@Param("count") Integer count);
}
