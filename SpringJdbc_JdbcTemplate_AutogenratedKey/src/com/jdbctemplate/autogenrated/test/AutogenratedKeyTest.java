package com.jdbctemplate.autogenrated.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdbctemplate.autogenrated.bo.CustomerBo;
import com.jdbctemplate.autogenrated.dao.CustomerDao;

public class AutogenratedKeyTest {
   public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("com/jdbctemplate/autogenrated/common/application-context.xml");
	CustomerDao customerDao = context.getBean("customerDao", CustomerDao.class);
	CustomerBo customerBo = new CustomerBo();
	/*customerBo.setEmailAddress("rishab@gmail.com");
	customerBo.setFirstName("rishab");
	customerBo.setLastName("jain");
	customerBo.setMobileNo("9441148216");
	int i = customerDao.insertCustomer(customerBo);
	System.out.println("returned values are  "+i);*/
	
	customerBo.setEmailAddress("teja@gmail.com");
	customerBo.setFirstName("teja");
	customerBo.setLastName("gokina");
	customerBo.setMobileNo("9441148143");
	int j = customerDao.getAutogenratedKeyValue(customerBo);
	System.out.println(j);
  }
}
