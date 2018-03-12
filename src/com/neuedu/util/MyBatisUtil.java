package com.neuedu.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

	private static final SqlSessionFactory factory;
	
	static {
		factory = new SqlSessionFactoryBuilder().
					build(MyBatisUtil.class.getClassLoader().getResourceAsStream("myBatis.xml"));
	}
	
	public static SqlSession getSession(boolean isAutoCommit) {
		return factory.openSession(isAutoCommit);
	}
	
}
