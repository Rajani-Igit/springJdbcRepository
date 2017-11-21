package com.mappingsqloperations.subclass.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mappingsqloperations.subclass.bo.CustomerBo;
import com.mappingsqloperations.subclass.dao.CustomerDao;

public class MappingSqlOperationsAsSubclasses {
   public static void main(String[] args) {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("com/mappingsqloperations/subclass/common/application-context.xml");
	CustomerDao customerDao = context.getBean("customerDao",CustomerDao.class);
	
	/*CustomerBo customerBo = new CustomerBo();
	customerBo.setCustmerNo(113);
	customerBo.setFirstName("Dharnindhra");
	customerBo.setLastName("Veramachineni");
	customerBo.setMobileNo("9966441211");
	int i = customerDao.saveCoustomer(customerBo);
	System.out.println("No of records are inserted "+i);*/
	
	/*List<CustomerBo> listCustomerBo = customerDao.getCustomers();
	for(CustomerBo customerBo : listCustomerBo) {
		System.out.println(customerBo);
	}*/
	
	/*CustomerBo customerBo = customerDao.getCustomer();
	System.out.println(customerBo);*/
	
	CustomerBo customerBo = customerDao.getCustomer("talasila");
	System.out.println(customerBo);
	
}
}
