package top.haha233.smbms.service.impl;

import org.apache.ibatis.session.SqlSession;
import top.haha233.smbms.dal.UserMapper;
import top.haha233.smbms.model.bo.UserBo;
import top.haha233.smbms.model.po.UserPo;
import top.haha233.smbms.service.UserService;
import top.haha233.smbms.utils.MyBatisUtil;

import java.util.List;

/**
 *
 * @author Ice_Dog
 */
public class UserServiceImpl implements UserService {
	@Override
	public Integer add(UserPo user) {
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
		}
		finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return count;
	}

	@Override
	public Integer delete(UserPo u) {
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
		}
		finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return count;
	}

	@Override
	public Integer count() {
		//mybatis去操作数据库
		SqlSession sqlSession ;
		sqlSession= MyBatisUtil.createSqlSession();
		Integer count = 0;
		try {
			count = sqlSession.getMapper(UserMapper.class).count();
			sqlSession.commit();
		}catch (Exception e){
			e.printStackTrace();
			sqlSession.rollback();
		}
		finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return count;
	}

	@Override
	public List<UserBo> queryAll() {
		List<UserBo> users = null;
		//mybatis去操作数据库
		SqlSession sqlSession ;
		sqlSession= MyBatisUtil.createSqlSession();
		try {
			users = sqlSession.getMapper(UserMapper.class).queryUser();
			sqlSession.commit();
		}catch (Exception e){
			e.printStackTrace();
			sqlSession.rollback();
		}
		finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return users;
	}

	@Override
	public List<UserBo> queryAllByName(String userName) {
		List<UserBo> users = null;
		//mybatis去操作数据库
		SqlSession sqlSession ;
		sqlSession= MyBatisUtil.createSqlSession();
		try {
			users = sqlSession.getMapper(UserMapper.class).queryUserByName(userName);
			sqlSession.commit();
		}catch (Exception e){
			e.printStackTrace();
			sqlSession.rollback();
		}
		finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return users;
	}

	@Override
	public List<UserBo> queryRListByName(String userName) {
		List<UserBo> users = null;
		//mybatis去操作数据库
		SqlSession sqlSession;
		sqlSession= MyBatisUtil.createSqlSession();
		try {
			users = sqlSession.getMapper(UserMapper.class).queryRListByName(userName);
			sqlSession.commit();
		}catch (Exception e){
			e.printStackTrace();
			sqlSession.rollback();
		}
		finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return users;
	}

	@Override
	public List<UserBo> queryUserAddressByUserId(String id) {
		List<UserBo> users = null;
		Integer uid= 0;
		try {
			uid = Integer.parseInt(id);
		}catch (Exception e){
			e.printStackTrace();

		}
		//mybatis去操作数据库
		SqlSession sqlSession;
		sqlSession= MyBatisUtil.createSqlSession();
		try {
			users = sqlSession.getMapper(UserMapper.class).queryUserAddressByUserId(uid);
			sqlSession.commit();
		}catch (Exception e){
			e.printStackTrace();
			sqlSession.rollback();
		}
		finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return users;
	}
}
