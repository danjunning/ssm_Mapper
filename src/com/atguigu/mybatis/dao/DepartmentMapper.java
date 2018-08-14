package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Department;

public interface DepartmentMapper {
	public Department getDepById(Integer id);
	
	public Department getDepByIdPlus(Integer id);
	
	public Department getDepByIdStep(Integer id);//分步查询的方式

}
