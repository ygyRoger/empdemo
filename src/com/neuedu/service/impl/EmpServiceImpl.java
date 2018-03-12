package com.neuedu.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.neuedu.dao.EmpMapper;
import com.neuedu.entity.Emp;
import com.neuedu.service.EmpService;
import com.neuedu.util.MyBatisUtil;

public class EmpServiceImpl implements EmpService{
	
	@Override
	public List<Emp> selectEmps() {
		SqlSession session = MyBatisUtil.getSession(true);
		EmpMapper empMapper = session.getMapper(EmpMapper.class);
		List<Emp> empList = empMapper.selectEmps();
		session.close();
		return empList;
	}

	@Override
	public int deleteEmpById(int id) {
		SqlSession session = MyBatisUtil.getSession(true);
		EmpMapper empMapper = session.getMapper(EmpMapper.class);
		int i = empMapper.deleteEmpById(id);
		session.close();
		return i;
	}

	@Override
	public int insertEmp(Emp emp) {
		SqlSession session = MyBatisUtil.getSession(true);
		EmpMapper empMapper = session.getMapper(EmpMapper.class);
		int i = empMapper.insertEmp(emp);
		session.close();
		return i;
	}

	@Override
	public Emp selectEmpById(int id) {
		SqlSession session = MyBatisUtil.getSession(true);
		EmpMapper empMapper = session.getMapper(EmpMapper.class);
		Emp emp = empMapper.selectEmpById(id);
		session.close();
		return emp;
	}

	@Override
	public int updateEmp(Emp emp) {
		SqlSession session = MyBatisUtil.getSession(true);
		EmpMapper empMapper = session.getMapper(EmpMapper.class);
		int i = empMapper.updateEmp(emp);
		session.close();
		return i;
	}
	
}
