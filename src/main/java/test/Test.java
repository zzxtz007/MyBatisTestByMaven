package test;

import org.apache.ibatis.session.SqlSession;
import top.haha233.smbms.dal.UserMapper;
import top.haha233.smbms.model.bo.UserBo;
import top.haha233.smbms.model.po.UserPo;
import top.haha233.smbms.utils.MyBatisUtil;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		UserPo u = new UserPo();
//		u.setId(25);
		u.setUserCode("admin");
//		u.setUserName("谢");
		u.setUserPassword("1234567");
//		u.setUserRole(3);
//		u.setGender(2);
		query(u);
	}
	private static void query(UserPo u){
		SqlSession s = MyBatisUtil.createSqlSession();
		List<UserBo> users = s.getMapper(UserMapper.class).list(u,0,5);
		for (UserBo user : users) {
			System.out.println(user.getUserName());
		}
		System.out.println(users.get(0));
	}
	private static void count(UserPo u){
		SqlSession s = MyBatisUtil.createSqlSession();
		int i = s.getMapper(UserMapper.class).count(u);
		System.out.println(i);
	}
}