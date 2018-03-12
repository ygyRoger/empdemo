package com.neuedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neuedu.entity.Emp;

public interface EmpMapper {

	List<Emp> selectEmps();
	
	int deleteEmpById(int id);
	
	int insertEmp(@Param("emp") Emp emp);
	
	Emp selectEmpById(int id);
	
	int updateEmp(@Param("emp") Emp emp);
	
}
