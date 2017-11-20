package com.simplejdbcinsert.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.simplejdbcinsert.bo.CustomerBo;
import com.simplejdbcinsert.dao.CustomerDao;

public class SimpleJdbcInsertTest {
   public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("com/simplejdbcinsert/common/application-context.xml");
	CustomerDao customerDao = context.getBean("customerDao" ,CustomerDao.class);
	CustomerBo customerBo = new CustomerBo();
	customerBo.setCustomerno(107);
	customerBo.setFirstname("Bharath");
	customerBo.setLastname("Tunga");
	customerBo.setMobibeno("9988774455");
	int returningValue = customerDao.saveCoustomer(customerBo);
	System.out.println("no of records updated is  "+returningValue);
}
}
