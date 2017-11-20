package com.simplejdbccall.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.simplejdbccall.bo.CustomerBo;
import com.simplejdbccall.dao.CustomerDao;

public class SimpleJdbcCallTest {
  public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("com/simplejdbccall/common/application-context.xml");
	CustomerDao customerDao =context.getBean("customerDao", CustomerDao.class);
	CustomerBo customerBo = new CustomerBo();
	customerBo.setCustomerno(111);
	customerBo.setFirstname("venkat");
	customerBo.setLastname("basani");
	customerBo.setMobibeno("7788996605");
	long i =customerDao.saveCoustomer(customerBo);
	System.out.println("no of records updated   "+i);
}
}
