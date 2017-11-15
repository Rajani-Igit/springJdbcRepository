package com.jdbctemplate.query.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdbctemplate.query.bo.CustomerBo;
import com.jdbctemplate.query.dao.CustomerDao;

public class JdbcTemplateQueryApproachTest {
   public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("com/jdbctemplate/query/common/application-context.xml");
	CustomerDao customerDao = context.getBean("customerDao", CustomerDao.class);
	
	int i = customerDao.getCustomerCount();
	System.out.println("no of customers are  "+i);
	
	String firstName =(String) customerDao.getCustomerFirstName("9701136377");
	System.out.println("firstName of the customer is "+firstName);
	
	CustomerBo customerBo = customerDao.getCustomerDetails("9701136377");
	System.out.println("customer Bo object is "+customerBo);
	
	List<CustomerBo> listBo = customerDao.getCustomers();
	for(CustomerBo customer : listBo) {
		System.out.println(customer);
	}
	
}
}
