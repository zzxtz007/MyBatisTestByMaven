package test;

import top.haha233.smbms.model.bo.UserBo;
import top.haha233.smbms.model.po.UserPo;
import top.haha233.smbms.service.impl.UserServiceImpl;

import java.util.List;

public class Test {
	public static void main(String[] args) {
		UserPo u = new UserPo();
		u.setId(25);
		u.setUserCode("xiezhibo");
		u.setUserName("谢治");
		u.setUserPassword("123456");
		u.setGender(2);
		new Test().queryUserAddressByUserId(u);
	}

	private void add(UserPo u) {
		Integer count = new UserServiceImpl().add(u);
		if (count>0){
			System.out.println("成功");
		}else{
			System.out.println("失败");
		}
	}
	private void delete(UserPo u){
		Integer count = new UserServiceImpl().delete(u);
		if (count>0){
			System.out.println("成功");
		}else{
			System.out.println("失败");
		}
	}

	private void count(UserPo u){
		Integer count = new UserServiceImpl().count();
		System.out.println(count);
	}
	private void queryAll(UserPo u){
		List<UserBo> users = new UserServiceImpl().queryAll();
		if (users==null){
			System.out.println("失败");
		}else {
			for (UserBo user : users) {
				System.out.println(user.getId()+user.getUserName());
			}
		}

	}
	private void queryAllByName(UserPo u){
		List<UserBo> users = new UserServiceImpl().queryAllByName(u.getUserName());
		if (users==null){
			System.out.println("失败");
		}else {
			for (UserBo user : users) {
				System.out.println(user.getId()+user.getUserName());
			}
		}
	}

	private void queryRListByName(UserPo u){
		List<UserBo> users = new UserServiceImpl().queryRListByName("赵");
		if (users==null){
			System.out.println("失败");
		}else {
			for (UserBo user : users) {
				System.out.println(user.getId()+"::::"+user.getUserName()+"::::"+user.getUserRole().getRoleName());
			}
		}
	}
	private void queryUserAddressByUserId(UserPo u){
		List<UserBo> users = new UserServiceImpl().queryUserAddressByUserId("1");
		if (users==null){
			System.out.println("失败");
		}else {
			for (UserBo user : users) {
				System.out.println(user);
			}
		}
	}
}
