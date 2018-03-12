package com.neuedu.util;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidFactory implements DataSourceFactory{

	private Properties properties;
	
	@Override
	public DataSource getDataSource() {
		DruidDataSource data = null;
		try {
			data = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
