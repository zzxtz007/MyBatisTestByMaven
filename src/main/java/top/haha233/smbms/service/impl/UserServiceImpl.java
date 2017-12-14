package top.haha233.smbms.service.impl;

import org.apache.ibatis.session.SqlSession;
import top.haha233.smbms.dal.UserMapper;
import top.haha233.smbms.model.bo.UserBo;
import top.haha233.smbms.model.po.UserPo;
import top.haha233.smbms.service.UserService;
import top.haha233.smbms.util.Response;
import top.haha233.smbms.utils.MyBatisUtil;

import java.util.List;

/**
 *
 * @author Ice_Dog
 */
public class UserServiceImpl implements UserService {
	@Override
	public Response add(UserPo user) {
		// 含空值时返回
		if (user==null) {
			return new Response(2);
		}
		//mybatis去操作数据库
		SqlSession sqlSession ;
		sqlSession= MyBatisUtil.createSqlSession();
		Integer count = 0;
		try {
			count = sqlSession.getMapper(UserMapper.class).add(user);
			sqlSession.commit();
		}catch (Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			MyBatisUtil.closeSqlSession(sqlSession);
			return new Response(1);
		}
		finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return new Response(0).add("ret",count);
	}

	@Override
	public Response delete(UserPo u) {
		// 含空值时返回
		if (u==null) {
			return new Response(2);
		}
		//mybatis去操作数据库
		SqlSession sqlSession ;
		sqlSession= MyBatisUtil.createSqlSession();
		Integer count = 0;
		try {
			count = sqlSession.getMapper(UserMapper.class).delete(u.getId());
			sqlSession.commit();
		}catch (Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			MyBatisUtil.closeSqlSession(sqlSession);
			return new Response(1);
		}
		finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return new Response(0).add("ret",count);
	}

	@Override
	public Response count() {
		return null;
	}

	public Response count(UserPo u) {
		// 含空值时返回
		if (u==null) {
			return new Response(2);
		}
		//mybatis去操作数据库
		SqlSession sqlSession ;
		sqlSession= MyBatisUtil.createSqlSession();
		Integer count = 0;
		try {
			count = sqlSession.getMapper(UserMapper.class).count(u);
			sqlSession.commit();
		}catch (Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			MyBatisUtil.closeSqlSession(sqlSession);
			return new Response(1);
		}
		finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return new Response(0).add("ret",count);
	}

}
