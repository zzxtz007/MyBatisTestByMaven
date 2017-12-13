package top.haha233.smbms.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ice_Dog
 * 读取mybatis-config配置文件，创建出mybatis操作数据库对象
 */
public class MyBatisUtil {
	private static SqlSessionFactory factory;

	static {
		//读取配置文件
		try {
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			factory=new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建SqlSession对象的方法:用于mybatis操作数据库
	 * @return 创建SqlSession对象的方法:用于mybatis操作数据库
	 */
	public static SqlSession createSqlSession(){
		return factory.openSession();
	}

	/**
	 * 关闭SqlSession对象的方法
	 * @param sqlSession SqlSession对象
	 */
	public static void closeSqlSession(SqlSession sqlSession){
		if (sqlSession!=null){
			sqlSession.close();
		}
	}
}
