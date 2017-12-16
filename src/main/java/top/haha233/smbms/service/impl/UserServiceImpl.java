package top.haha233.smbms.service.impl;

import org.apache.ibatis.session.SqlSession;
import top.haha233.smbms.dal.UserMapper;
import top.haha233.smbms.model.bo.UserBo;
import top.haha233.smbms.model.po.UserPo;
import top.haha233.smbms.service.UserService;
import top.haha233.smbms.util.Response;
import top.haha233.smbms.utils.MyBatisUtil;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author Ice_Dog
 */
public class UserServiceImpl implements UserService {
	@Override
	public Response add(String userCode, String userName, String userPassword, String gender,
			String birthday, String phone, String address, String userRole) {
		// 含空值时返回 2
		if (userCode == null || userName == null || userPassword == null || gender == null || birthday == null || phone == null || address == null || userRole == null) {
			return new Response(2);
		}
		//创建UserPo
		UserPo userPo = new UserPo();
		userPo.setUserCode(userCode);
		userPo.setUserName(userName);
		userPo.setUserPassword(userPassword);
		userPo.setAddress(address);
		userPo.setPhone(phone);
		//类型转换异常 3
		try {
			userPo.setGender(Integer.parseInt(gender));
			userPo.setBirthday(new Date(Long.getLong(birthday)));
			userPo.setUserRole(Integer.parseInt(userRole));
		} catch (Exception e) {
			return new Response(3);
		}

		//mybatis去操作数据库
		SqlSession sqlSession;
		sqlSession = MyBatisUtil.createSqlSession();
		Integer count = 0;
		try {
			count = sqlSession.getMapper(UserMapper.class).add(userPo);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			MyBatisUtil.closeSqlSession(sqlSession);
			return new Response(1);
		} finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return new Response(0).add("ret", count);
	}

	@Override
	public Response delete(String id) {
		// 含空值时返回
		if (id == null) {
			return new Response(2);
		}
		//mybatis去操作数据库
		SqlSession sqlSession;
		sqlSession = MyBatisUtil.createSqlSession();
		Integer count = 0;
		try {
			count = sqlSession.getMapper(UserMapper.class).delete(Integer.parseInt(id));
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			MyBatisUtil.closeSqlSession(sqlSession);
			return new Response(1);
		} finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return new Response(0).add("ret", count);
	}

	@Override
	public Response modify(String id, String userCode, String userName, String userPassword,
			String gender, String birthday, String phone, String address, String userRole) {
		// 含空值时返回 2
		if (id == null) {
			return new Response(2);
		}
		//创建UserPo
		UserPo userPo = new UserPo();
		userPo.setUserCode(userCode);
		userPo.setUserName(userName);
		userPo.setUserPassword(userPassword);
		userPo.setAddress(address);
		userPo.setPhone(phone);
		//类型转换异常 3
		try {
			userPo.setId(Integer.parseInt(id));
			if (gender != null) {
				userPo.setGender(Integer.parseInt(gender));
			}
			if (birthday != null) {
				userPo.setBirthday(new Date(Long.getLong(birthday)));
			}
			if (userRole != null) {
				userPo.setUserRole(Integer.parseInt(userRole));
			}
		} catch (Exception e) {
			return new Response(3);
		}
		//mybatis去操作数据库
		SqlSession sqlSession;
		sqlSession = MyBatisUtil.createSqlSession();
		Integer count = 0;
		try {
			count = sqlSession.getMapper(UserMapper.class).modify(userPo);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			MyBatisUtil.closeSqlSession(sqlSession);
			return new Response(1);
		} finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return new Response(0).add("ret", count);
	}

	@Override
	public Response count(String userName, String userRole) {
		UserPo userPo = new UserPo();
		userPo.setUserName(userName);
		if (userRole!=null){
			//类型转换异常
			try{
				userPo.setUserRole(Integer.parseInt(userRole));
			} catch (Exception e) {
				return new Response(3);
			}
		}
		//mybatis去操作数据库
		SqlSession sqlSession;
		sqlSession = MyBatisUtil.createSqlSession();
		Integer count = 0;
		try {
			count = sqlSession.getMapper(UserMapper.class).count(userPo);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			MyBatisUtil.closeSqlSession(sqlSession);
			return new Response(1);
		} finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return new Response(0).add("count", count);
	}

	@Override
	public Response query(String userName, String userRole,String pageSize,String pageNum) {
		UserPo userPo = new UserPo();
		userPo.setUserName(userName);
		if(userRole!=null){
			//类型转换异常
			try{
				userPo.setUserRole(Integer.parseInt(userRole));
			} catch (Exception e) {
				return new Response(3);
			}
		}
		Integer startIndex = (Integer.parseInt(pageNum)-1)*Integer.parseInt(pageSize);
		Integer count = Integer.parseInt(pageSize);

		//mybatis去操作数据库
		SqlSession sqlSession;
		sqlSession = MyBatisUtil.createSqlSession();
		List<UserBo> users;
		try {
			users = sqlSession.getMapper(UserMapper.class).list(userPo,startIndex,count);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			MyBatisUtil.closeSqlSession(sqlSession);
			return new Response(1);
		} finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return new Response(0).add("users", users);
	}

	@Override
	public Response queryById(String id) {
		UserPo userPo = new UserPo();

		//类型转换异常
		try{
			userPo.setId(Integer.parseInt(id));
		} catch (Exception e) {
			return new Response(3);
		}
		//mybatis去操作数据库
		SqlSession sqlSession;
		sqlSession = MyBatisUtil.createSqlSession();
		UserBo userBo ;
		try {
			userBo = sqlSession.getMapper(UserMapper.class).list(userPo,0,1).get(0);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			MyBatisUtil.closeSqlSession(sqlSession);
			return new Response(1);
		} finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return new Response(0).add("users", userBo);
	}

	@Override
	public Response login(String userCode, String password,HttpSession session) {
		// 含空值时返回 2
		if (userCode==null||password==null){
			return new Response(2);
		}
		UserPo userPo = new UserPo();
		userPo.setUserCode(userCode);
		userPo.setUserPassword(password);
		//mybatis去操作数据库
		SqlSession sqlSession;
		sqlSession = MyBatisUtil.createSqlSession();
		UserBo userBo = null ;
		try {
			userBo = sqlSession.getMapper(UserMapper.class).list(userPo,0,1).get(0);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			//释放资源
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		//登录 失败 1
		if (userBo==null){
			return new Response(1);
		}
		session.setAttribute("uid",userBo.getId());
		return new Response(0).add("user", userBo);

	}

}
