package com.atguigu.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.atguigu.mybatis.bean.Employee;

public interface EmployeeMapper {
	
	/*多条记录封装陈一个map
	 * 键（Key)是一条记录的主键，值（Value)是记录封装后的JavaBean
	 * 
	 * */
	@MapKey("id")//告诉mybatis封装这个map的时候，使用id这个属性作为主键
	public Map<Integer, Employee> getEmpByNameLikeReturnMap(String name);//返回值是一个Map
	
	public Map<String, Object> getEmpByidReturnMap(Integer id);//返回值是一个Map
	
	public List<Employee> getEmpsByLastNameLike(String lastName);//返回值是一个List
	
	public Employee getEmpByMap(Map<String, Object> map);//传递多个参数，使用Map
	
	//传递多个参数，使用命名参数
	public Employee getEmpByidAndLastName(@Param("id")Integer id,@Param("lastName")String lastName);
	public Employee getEmpByid(Integer id);
	public void addEmp(Employee employee);
	public void updateEmp(Employee employee);
	public void deleteEmp(Integer id);

}
