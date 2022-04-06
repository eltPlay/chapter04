package com.wjw.test;
/*
  入门程序测试类
 */

import com.wjw.po.Customer;
import com.wjw.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author 吴嘉炜
 */
public class MybatisTest {
	/**
	 * 一级缓存测试
	 * 缓存在SqlSession
	 * 默认开启
	 */
	@Test
	public void catchOneTest() {
		SqlSession sqlSession = MybatisUtils.getSession();
		Customer customer = sqlSession.selectOne("com.wjw.mapper.CustomerMapper.findCustomerById", 1);
		System.out.println(customer.toString());
		//清空一级缓存
		sqlSession.commit();

		//第二次查询先查询一级缓存
		customer = sqlSession.selectOne("com.wjw.mapper.CustomerMapper.findCustomerById", 1);
		System.out.println(customer.toString());
		sqlSession.close();
	}

	/**
	 * 二级缓存测试
	 * SqlsessionFactory
	 * 需要手动开启
	 * 二级缓存查询的对象需要实现序列化接口
	 */
	@Test
	public void catchTwoTest() {
		SqlSession sqlSession = MybatisUtils.getSession();
		Customer customer = sqlSession.selectOne("com.wjw.mapper.CustomerMapper.findCustomerById", 1);
		System.out.println(customer.toString());
		//与下面的sqlSession不是同一个，所以这里可以关闭
		sqlSession.close();

		//第二个回话查询，二级缓存
		//获取新的回话
		sqlSession = MybatisUtils.getSession();
		customer = sqlSession.selectOne("com.wjw.mapper.CustomerMapper.findCustomerById", 1);
		System.out.println(customer.toString());
		sqlSession.close();
	}

	/**
	 * 根据客户编号查询客户信息
	 */
	@Test
	public void findCustomerByIdTest() throws Exception {

		//通过工具类获取回话
		SqlSession sqlSession = MybatisUtils.getSession();
		// 4、sqlSession执行映射文件中定义的SQL，并返回映射结果
		// 第一个参数是sql的id，第二个参数是传入给sql的占位符参数
		Customer customer = sqlSession.selectOne("com.wjw.mapper.CustomerMapper.findCustomerById", 1);

		// 打印输出结果
		System.out.println(customer.toString());
		// 5、关闭sqlSession
		sqlSession.close();

	}

	/**
	 * 根据用户名称来模糊查询用户信息列表
	 */
	@Test
	public void findCustomerByNameTest() throws Exception {

		//通过工具类获取回话
		SqlSession sqlSession = MybatisUtils.getSession();
		// 4、SqlSession执行映射文件中定义的SQL，并返回映射结果
		List<Customer> customers = sqlSession.selectList("com.wjw.mapper.CustomerMapper.findCustomerByName", "J");
		for (Customer customer : customers) {
			// 打印输出结果集
			System.out.println(customer);
		}
		// 5、关闭SqlSession
		sqlSession.close();
	}

	/**
	 * 添加客户
	 */
	@Test
	public void addCustomerTest() throws Exception {

		//通过工具类获取回话
		SqlSession sqlSession = MybatisUtils.getSession();
		// 4、SqlSession执行添加操作
		// 4.1创建Customer对象，并向对象中添加数据
		Customer customer = new Customer();
		customer.setId(5);
		customer.setUsername("Rose");
		customer.setJobs("TEACHER");
		customer.setPhone("1392912");
		// 4.2执行SqlSession的插入方法，返回的是SQL语句影响的行数
		int rows = sqlSession.insert("com.wjw.mapper.CustomerMapper.addCustomer", customer);
		// 4.3通过返回结果判断插入操作是否执行成功
		if (rows > 0) {
			System.out.println("您成功插入了" + rows + "条数据！");
		} else {
			System.out.println("执行插入操作失败！！！");
		}
		// 4.4提交事务
		sqlSession.commit();
		// 5、关闭SqlSession
		sqlSession.close();
	}

	/**
	 * 更新客户
	 */
	@Test
	public void updateCustomerTest() throws Exception {

		//通过工具类获取回话
		SqlSession sqlSession = MybatisUtils.getSession();
		// 4、SqlSession执行更新操作
		// 4.1创建Customer对象，对对象中的数据进行模拟更新
		Customer customer = new Customer();
		customer.setId(5);
		customer.setUsername("Rose");
		customer.setJobs("PROGRAMMER");
		customer.setPhone("1331111");
		// 4.2执行SqlSession的更新方法，返回的是SQL语句影响的行数
		int rows = sqlSession.update("com.wjw.mapper.CustomerMapper.updateCustomer", customer);
		// 4.3通过返回结果判断更新操作是否执行成功
		if (rows > 0) {
			System.out.println("您成功修改了" + rows + "条数据！");
		} else {
			System.out.println("执行修改操作失败！！！");
		}
		// 4.4提交事务
		sqlSession.commit();
		// 5、关闭SqlSession
		sqlSession.close();
	}

	/**
	 * 删除客户
	 */
	@Test
	public void deleteCustomerTest() throws Exception {

		//通过工具类获取回话
		SqlSession sqlSession = MybatisUtils.getSession();
		// 4、SqlSession执行删除操作
		// 4.1执行SqlSession的删除方法，返回的是SQL语句影响的行数
		int rows = sqlSession.delete("com.wjw.mapper.CustomerMapper.deleteCustomer", 5);
		// 4.2通过返回结果判断删除操作是否执行成功
		if (rows > 0) {
			System.out.println("您成功删除了" + rows + "条数据！");
		} else {
			System.out.println("执行删除操作失败！！！");
		}
		// 4.3提交事务
		sqlSession.commit();
		// 5、关闭SqlSession
		sqlSession.close();
	}

}
