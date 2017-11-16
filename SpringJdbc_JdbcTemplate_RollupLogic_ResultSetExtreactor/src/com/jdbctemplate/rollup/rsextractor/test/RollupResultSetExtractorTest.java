package com.jdbctemplate.rollup.rsextractor.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdbctemplate.rollup.rsextractor.bo.CustomerBo;
import com.jdbctemplate.rollup.rsextractor.bo.CustomerJobSheetBo;
import com.jdbctemplate.rollup.rsextractor.bo.JobSheetBo;
import com.jdbctemplate.rollup.rsextractor.dao.CustomerDao;

public class RollupResultSetExtractorTest {

	public static void main(String[] args) {
     ApplicationContext context = new ClassPathXmlApplicationContext("com/jdbctemplate/rollup/rsextractor/common/application-context.xml");
     CustomerDao customerDao = context.getBean("customerDao",CustomerDao.class);
   //CustomerJobSheetBo customerJobSheetBo =  customerDao.getCustomerJobSheets(101);
     
     List<CustomerJobSheetBo> customerJobSheetBoList = customerDao.getCustomerJobSheetsGroup();
     for(CustomerJobSheetBo customerJobSheetBo : customerJobSheetBoList) {
    	 //System.out.println(customerJobSheetBo);
    	 CustomerBo customerBo = customerJobSheetBo.getCustomerBo();
    	 List<JobSheetBo> jobSheetBoList = customerJobSheetBo.getJobsheetBoList();
    	 System.out.println("CustomerName   :"+customerBo.getFirstName() +"  and customerId  "+customerBo.getCustmerNo());
    	 for(JobSheetBo jobSheetBo :jobSheetBoList) {
    		 System.out.println("jobSheets are  "+jobSheetBo.getJobSheetId());
    	 }
    	 
     }
	}
}
