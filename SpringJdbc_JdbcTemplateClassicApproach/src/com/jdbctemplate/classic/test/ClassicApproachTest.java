package com.jdbctemplate.classic.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdbctemplate.classic.bo.CustomerBo;
import com.jdbctemplate.classic.dao.CustomerDao;

public class ClassicApproachTest {
    public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/jdbctemplate/classic/common/application-context.xml");
		CustomerDao customerDao = context.getBean("customerDao",CustomerDao.class);
		
		/*customerDao.createTable();
		System.out.println("Table Created");*/
		
		/*List<CustomerBo> list = customerDao.getCustomers();
		System.out.println("main method list objects are "+list);
		for(CustomerBo listObjects : list) {
			System.out.println(listObjects);
		} */
		
		/*int i = customerDao.getCustomerCallable();
		System.out.println("return value is "+i);*/
		
		List<CustomerBo> list = customerDao.getCustomersStaticCallable();
		for(CustomerBo customer : list) {
			System.out.println("inside the for each method");
			System.out.println(customer);
		}
	}
}
