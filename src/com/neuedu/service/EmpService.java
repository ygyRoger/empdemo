package com.neuedu.service;

import java.util.List;

import com.neuedu.entity.Emp;

public interface EmpService {

	List<Emp> selectEmps();
	
	int deleteEmpById(int id);
	
	int insertEmp(Emp emp);
	
	Emp selectEmpById(int id);
	
	int updateEmp(Emp emp);
	
}
