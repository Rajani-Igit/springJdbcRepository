package com.jdbctemplate.rsextractor.pagination.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdbctemplate.rsextractor.pagination.bo.JobSheetBo;
import com.jdbctemplate.rsextractor.pagination.dao.JobSheetDaoWithAbstractImpl;

public class PaginationResultSetExtractorTest {
	public static void main(String[] args) {
   ApplicationContext context = new ClassPathXmlApplicationContext("com/jdbctemplate/rsextractor/pagination/common/application-context.xml");
   
   //This logic is for pagination with out TemplateDesin pattern
   /*JobSheetDao jobSheetDao = context.getBean("jobSheetDao", JobSheetDao.class);
   List<JobSheetBo> jobSheetBoList = jobSheetDao.getJobSheets(3,3);
    for(JobSheetBo jobSheetBo : jobSheetBoList) {
    	System.out.println("inside main for loop");
	   System.out.println(jobSheetBo);
     }*/
   
   //This logic is for pagination with  TemplateDesin pattern
   
   JobSheetDaoWithAbstractImpl jobSheetDaoWithAbstractImpl = context.getBean("jobSheetDaoWithAbstractImpl", JobSheetDaoWithAbstractImpl.class);
   List<Object> list=jobSheetDaoWithAbstractImpl.getPaginatedJobSheets();
   for(Object object : list) {
	   JobSheetBo jobSeetBo = (JobSheetBo)object;
	   System.out.println(jobSeetBo);
   }
  }
}
