package com.wjw.test;
/*
  ���ų��������
 */

import com.wjw.po.Customer;
import com.wjw.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author ����
 */
public class MybatisTest {
	/**
	 * һ���������
	 * ������SqlSession
	 * Ĭ�Ͽ���
	 */
	@Test
	public void catchOneTest() {
		SqlSession sqlSession = MybatisUtils.getSession();
		Customer customer = sqlSession.selectOne("com.wjw.mapper.CustomerMapper.findCustomerById", 1);
		System.out.println(customer.toString());
		//���һ������
		sqlSession.commit();

		//�ڶ��β�ѯ�Ȳ�ѯһ������
		customer = sqlSession.selectOne("com.wjw.mapper.CustomerMapper.findCustomerById", 1);
		System.out.println(customer.toString());
		sqlSession.close();
	}

	/**
	 * �����������
	 * SqlsessionFactory
	 * ��Ҫ�ֶ�����
	 * ���������ѯ�Ķ�����Ҫʵ�����л��ӿ�
	 */
	@Test
	public void catchTwoTest() {
		SqlSession sqlSession = MybatisUtils.getSession();
		Customer customer = sqlSession.selectOne("com.wjw.mapper.CustomerMapper.findCustomerById", 1);
		System.out.println(customer.toString());
		//�������sqlSession����ͬһ��������������Թر�
		sqlSession.close();

		//�ڶ����ػ���ѯ����������
		//��ȡ�µĻػ�
		sqlSession = MybatisUtils.getSession();
		customer = sqlSession.selectOne("com.wjw.mapper.CustomerMapper.findCustomerById", 1);
		System.out.println(customer.toString());
		sqlSession.close();
	}

	/**
	 * ���ݿͻ���Ų�ѯ�ͻ���Ϣ
	 */
	@Test
	public void findCustomerByIdTest() throws Exception {

		//ͨ���������ȡ�ػ�
		SqlSession sqlSession = MybatisUtils.getSession();
		// 4��sqlSessionִ��ӳ���ļ��ж����SQL��������ӳ����
		// ��һ��������sql��id���ڶ��������Ǵ����sql��ռλ������
		Customer customer = sqlSession.selectOne("com.wjw.mapper.CustomerMapper.findCustomerById", 1);

		// ��ӡ������
		System.out.println(customer.toString());
		// 5���ر�sqlSession
		sqlSession.close();

	}

	/**
	 * �����û�������ģ����ѯ�û���Ϣ�б�
	 */
	@Test
	public void findCustomerByNameTest() throws Exception {

		//ͨ���������ȡ�ػ�
		SqlSession sqlSession = MybatisUtils.getSession();
		// 4��SqlSessionִ��ӳ���ļ��ж����SQL��������ӳ����
		List<Customer> customers = sqlSession.selectList("com.wjw.mapper.CustomerMapper.findCustomerByName", "J");
		for (Customer customer : customers) {
			// ��ӡ��������
			System.out.println(customer);
		}
		// 5���ر�SqlSession
		sqlSession.close();
	}

	/**
	 * ��ӿͻ�
	 */
	@Test
	public void addCustomerTest() throws Exception {

		//ͨ���������ȡ�ػ�
		SqlSession sqlSession = MybatisUtils.getSession();
		// 4��SqlSessionִ����Ӳ���
		// 4.1����Customer���󣬲���������������
		Customer customer = new Customer();
		customer.setId(5);
		customer.setUsername("Rose");
		customer.setJobs("TEACHER");
		customer.setPhone("1392912");
		// 4.2ִ��SqlSession�Ĳ��뷽�������ص���SQL���Ӱ�������
		int rows = sqlSession.insert("com.wjw.mapper.CustomerMapper.addCustomer", customer);
		// 4.3ͨ�����ؽ���жϲ�������Ƿ�ִ�гɹ�
		if (rows > 0) {
			System.out.println("���ɹ�������" + rows + "�����ݣ�");
		} else {
			System.out.println("ִ�в������ʧ�ܣ�����");
		}
		// 4.4�ύ����
		sqlSession.commit();
		// 5���ر�SqlSession
		sqlSession.close();
	}

	/**
	 * ���¿ͻ�
	 */
	@Test
	public void updateCustomerTest() throws Exception {

		//ͨ���������ȡ�ػ�
		SqlSession sqlSession = MybatisUtils.getSession();
		// 4��SqlSessionִ�и��²���
		// 4.1����Customer���󣬶Զ����е����ݽ���ģ�����
		Customer customer = new Customer();
		customer.setId(5);
		customer.setUsername("Rose");
		customer.setJobs("PROGRAMMER");
		customer.setPhone("1331111");
		// 4.2ִ��SqlSession�ĸ��·��������ص���SQL���Ӱ�������
		int rows = sqlSession.update("com.wjw.mapper.CustomerMapper.updateCustomer", customer);
		// 4.3ͨ�����ؽ���жϸ��²����Ƿ�ִ�гɹ�
		if (rows > 0) {
			System.out.println("���ɹ��޸���" + rows + "�����ݣ�");
		} else {
			System.out.println("ִ���޸Ĳ���ʧ�ܣ�����");
		}
		// 4.4�ύ����
		sqlSession.commit();
		// 5���ر�SqlSession
		sqlSession.close();
	}

	/**
	 * ɾ���ͻ�
	 */
	@Test
	public void deleteCustomerTest() throws Exception {

		//ͨ���������ȡ�ػ�
		SqlSession sqlSession = MybatisUtils.getSession();
		// 4��SqlSessionִ��ɾ������
		// 4.1ִ��SqlSession��ɾ�����������ص���SQL���Ӱ�������
		int rows = sqlSession.delete("com.wjw.mapper.CustomerMapper.deleteCustomer", 5);
		// 4.2ͨ�����ؽ���ж�ɾ�������Ƿ�ִ�гɹ�
		if (rows > 0) {
			System.out.println("���ɹ�ɾ����" + rows + "�����ݣ�");
		} else {
			System.out.println("ִ��ɾ������ʧ�ܣ�����");
		}
		// 4.3�ύ����
		sqlSession.commit();
		// 5���ر�SqlSession
		sqlSession.close();
	}

}
