package com.atguigu.mybatis.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.atguigu.mybatis.bean.Department;
import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.DepartmentMapper;
import com.atguigu.mybatis.dao.EmployeeMapper;
import com.atguigu.mybatis.dao.EmployeeMapperAnnotation;
import com.atguigu.mybatis.dao.EmployeeMapperPlus;

public class MyBatisTest {
	
	public SqlSessionFactory getsessionFactory() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void test() throws IOException {
		SqlSessionFactory sqlSessionFactory=getsessionFactory();
		SqlSession sqlsession=sqlSessionFactory.openSession();
		
		try{
			
			Employee e=sqlsession.selectOne("com.atguigu.mybatis.dao.EmployeeMapper.getEmpByid", 1);
			System.out.println("没有使用接口"+e.getLastName());
		}finally{
			sqlsession.close();
		}
		
	}
	
	@Test
	public void test01() throws IOException{
		SqlSessionFactory sqlSessionFactory=getsessionFactory();
		SqlSession sqlsession=sqlSessionFactory.openSession();
		
		try{
			//获取接口的实现类对象：mybatis自动为接口创建代理对象
			EmployeeMapper Mapper=sqlsession.getMapper(EmployeeMapper.class);
			//System.out.println(Mapper.getClass());
			Employee e= Mapper.getEmpByid(1);
			System.out.println("使用接口的方式："+e.getLastName());
		}finally{
			sqlsession.close();
		}
		
	}
	
	@Test
	public void test02() throws IOException{
		SqlSessionFactory sessionFactory=getsessionFactory();
		SqlSession sqlSession=sessionFactory.openSession();
		
		try{
			EmployeeMapperAnnotation mapper=sqlSession.getMapper(EmployeeMapperAnnotation.class);
			Employee e=mapper.getEmpByid(2);
			System.out.println(e.getLastName());
		}finally{
			sqlSession.close();
		}
	}
	
	/**
	 * Mybatis允许增删改查自定义一下类型返回值：Integer、long、Boolean、void。
	 * 包括他们的包装类型和基本类型
	 * 
	 */
	@Test
	public void test03() throws IOException{
		SqlSessionFactory sessionFactory=getsessionFactory();
		SqlSession sqlSession=sessionFactory.openSession();//这是不带参数的，需要手动提交
		
		try{
			EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
			Employee e=new Employee(null, "jer44ry", "1", "jerry@qq.com");
			mapper.addEmp(e);
			System.out.println(e.getId());
			
			//Employee e=new Employee(1, "单俊宁", "1", "jerry@qq.com");
			//mapper.updateEmp(e);
			//mapper.deleteEmp(4);
			
			sqlSession.commit();//不要忘了手动提交
			System.out.println();
		}finally{
			sqlSession.close();
		}
	}
	
	@Test
	public void test04() throws IOException{
		SqlSessionFactory sessionFactory=getsessionFactory();
		SqlSession sqlSession=sessionFactory.openSession();//这是不带参数的，需要手动提交
		
		try{
			EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
			//Employee e=mapper.getEmpByidAndLastName(1, "单俊宁");
			//使用map封装多个参数
			/*Map<String,Object> map=new HashMap<>();
			map.put("id", 1);
			map.put("lastName", "单俊宁");
			map.put("tableName", "tbl_employee");
			Employee e=mapper.getEmpByMap(map);
			System.out.println(e);*/
			
			/*List<Employee> list=mapper.getEmpsByLastNameLike("%e%");
			for (Employee employee : list) {
				System.out.println(employee);	
			}*/
			
			/*Map<String, Object> map=mapper.getEmpByidReturnMap(1);
			System.out.println("打印map："+map);*/
			
			Map<Integer, Employee> map=mapper.getEmpByNameLikeReturnMap("%r%");
			System.out.println("打印map："+map);
			
			
			
			
		}finally{
			sqlSession.close();
		}
	}
	
	@Test
	public void test05() throws IOException{
		SqlSessionFactory sessionFactory=getsessionFactory();
		SqlSession sqlSession=sessionFactory.openSession();//这是不带参数的，需要手动提交
		
		try{
			EmployeeMapperPlus mapper=sqlSession.getMapper(EmployeeMapperPlus.class);
			/*Employee e=mapper.getEmpById(1);
			System.out.println("打印e："+e);*/
			
			/*Employee e=mapper.getEmpAndDept(1);
			System.out.println("打印e："+e);
			System.out.println("打印e："+e.getDept());*/
			
			Employee e=mapper.getEmpByIdStep(1);
			System.out.println("打印e："+e);
			System.out.println("打印e："+e.getDept());
			
			
			
		}finally{
			sqlSession.close();
		}
	}
	
	@Test
	public void test06() throws IOException{
		SqlSessionFactory sessionFactory=getsessionFactory();
		SqlSession sqlSession=sessionFactory.openSession();//这是不带参数的，需要手动提交
		
		try{
			DepartmentMapper mapper=sqlSession.getMapper(DepartmentMapper.class);
			/*Department d= mapper.getDepByIdPlus(1);
			System.out.println("打印e："+d);
			System.out.println("打印e："+d.getEmps());*/
			//测试分步查询
			Department d= mapper.getDepByIdStep(1);
			System.out.println("打印e："+d);
			System.out.println("打印e："+d.getEmps());
			
			
		}finally{
			sqlSession.close();
		}
	}

}
