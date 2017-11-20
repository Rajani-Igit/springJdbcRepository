package com.npjdbctemplate.allopr.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.npjdbctemplate.allopr.bo.CustomerBo;
import com.npjdbctemplate.allopr.dao.CustomerDao;

public class NamedJdbcTemplateApproachTest {
   public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("com/npjdbctemplate/allopr/common/application-context.xml");
	CustomerDao customerDao= context.getBean("customerDao",CustomerDao.class);
	
	/*int i = customerDao.getCustomerCount("r");
	System.out.println("no of customers are "+i);*/
	
	/*String firstName = customerDao.getCustomerFirstName("9701136377");
	System.out.println("firstName is "+firstName);*/
	
	 /*CustomerBo customerBo = customerDao.getCustomerDetails("9701136377");
	 System.out.println(customerBo);*/
	
	/*List<CustomerBo> listCustomers = customerDao.getCustomers("a");
	
	for(CustomerBo customerBo : listCustomers) {
		System.out.println(customerBo);
	}*/
	
	/*CustomerBo customerBo = new CustomerBo();
	customerBo.setCustmerNo(105);
	customerBo.setFirstName("Ramana");
	customerBo.setLastName("reddy");
	customerBo.setMobileNo("8686864291");
	int i = customerDao.saveCustomer(customerBo);
	System.out.println("no of records inserted "+i);*/
	
	int i = customerDao.updateCustomer(105, "Ramakanth");
	System.out.println("update the record "+i);
}
}
