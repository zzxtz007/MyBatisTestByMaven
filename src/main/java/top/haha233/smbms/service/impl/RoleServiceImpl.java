package top.haha233.smbms.service.impl;

import org.apache.ibatis.session.SqlSession;
import top.haha233.smbms.dal.RoleMapper;
import top.haha233.smbms.model.bo.RoleBo;
import top.haha233.smbms.service.RoleService;
import top.haha233.smbms.util.Response;
import top.haha233.smbms.utils.MyBatisUtil;

import java.util.List;

/**
 * @author Ice_Dog
 */
public class RoleServiceImpl implements RoleService {

	@Override
	public Response queryAllRole() {
		// 含空值时返回
//		boolean hasNull = id == null;
//		if (hasNull) {
//			return new Response(2);
//		}
		List<RoleBo> roles = null;
		//mybatis去操作数据库
		SqlSession sqlSession;
		sqlSession = MyBatisUtil.createSqlSession();
		try {
			roles = sqlSession.getMapper(RoleMapper.class).queryAllRole();
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		// 不存在时返回
		if (roles == null) {
			return new Response(3);
		}

		// 返回
		return new Response(0).add("roles", roles);
	}
}
