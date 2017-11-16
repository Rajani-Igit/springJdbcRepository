package com.jdbctemplate.rollup.rsextractor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.jdbctemplate.rollup.rsextractor.bo.CustomerBo;
import com.jdbctemplate.rollup.rsextractor.bo.CustomerJobSheetBo;
import com.jdbctemplate.rollup.rsextractor.bo.JobSheetBo;

public class CustomerDao {
     
	private String SQL_GET_CUSTOMERS_JOBSHEETS = "SELECT C.CUSTOMERNO ,C.FIRSTNAME , C.LASTNAME ,C.MOBIBENO ,JOB.JOBSHEETID ,JOB.JOBSHEETDATE ,JOB.JOBSHEETTYPE ,JOB.KILOMETERS ,JOB.PETROLLEVEL ,JOB.ENGINEID FROM CUSOMER C INNER JOIN JOBSHEETS JOB ON C.CUSTOMERNO = JOB.CUSTOMERNO WHERE C.CUSTOMERNO = ?";
	private String SQL_GET_LIST_OF_CUSTOMERS_JOBSHEETS = "SELECT C.CUSTOMERNO ,C.FIRSTNAME , C.LASTNAME ,C.MOBIBENO ,JOB.JOBSHEETID ,JOB.JOBSHEETDATE ,JOB.JOBSHEETTYPE ,JOB.KILOMETERS ,JOB.PETROLLEVEL ,JOB.ENGINEID FROM CUSOMER C INNER JOIN JOBSHEETS JOB ON C.CUSTOMERNO = JOB.CUSTOMERNO";
	private JdbcTemplate jdbcTemplate;

	public CustomerDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/*public CustomerJobSheetBo getCustomerJobSheets(int customerNo) {
		System.out.println("inside getCustomerJobSgeets");
		return jdbcTemplate.query(SQL_GET_CUSTOMERS_JOBSHEETS, new Object[] {customerNo}, new CustomerJobSheetBoExtractor());
	}*/
	
	public List<CustomerJobSheetBo> getCustomerJobSheetsGroup() {
		return jdbcTemplate.query(SQL_GET_LIST_OF_CUSTOMERS_JOBSHEETS, new CustomerJobSheetBoExtractor());
	}

	//private final class CustomerJobSheetBoExtractor implements ResultSetExtractor<CustomerJobSheetBo>{
	  private final class CustomerJobSheetBoExtractor implements ResultSetExtractor<List<CustomerJobSheetBo>>{
		  
		//This is used to check weather the key is already we read or not like incase of One to Many
        private Map<Integer , CustomerJobSheetBo> map = null;
        
        //For Returining single Object
        //public CustomerJobSheetBo extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		@Override
		public List<CustomerJobSheetBo> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
			map = new HashMap<Integer , CustomerJobSheetBo>();
			
			//This is to return only CustomerJobSheetBo Object 
			CustomerJobSheetBo customerJobSheetBo = null;
			
			//This Object is used to send List of CustomerJobSheetBo Objects
			List<CustomerJobSheetBo> customerJobSheetBoList = new ArrayList<CustomerJobSheetBo>();
			
			//This Object is used to Store the List of objects for JobSheetsBo
			List<JobSheetBo> jobSheetBoList = null;
			
			//This Object is used to Store the CustomerBo object and store inside the CustomerJobSheetBo object
			CustomerBo cbo = null;
			
			//This Object is used to store the Details of JobSheet columns from Table and later this will store in List and that List will be again Stored into CustomerJobsheetBo object
			JobSheetBo jbo = null;
			
			while(resultSet.next()) {
				
				int customerNo = resultSet.getInt(1);
				//If Key is not available here Then insert both the CustomerBo and JobSheetBo into their respective Objects CustomerJobSheetBo
				
				if(map.containsKey(customerNo) == false) {
					
				  //Insert the values to CustomerBo 
					customerJobSheetBo = new CustomerJobSheetBo();
					cbo = new CustomerBo();
					cbo.setCustmerNo(resultSet.getInt(1));
					cbo.setFirstName(resultSet.getString(2));
					cbo.setLastName(resultSet.getString(3));
					cbo.setMobileNo(resultSet.getString(4));
					
					customerJobSheetBo.setCustomerBo(cbo);
					
					//Insert the values to JobSheetBo
				    //jbo = new JobSheetBo();
				    jobSheetBoList = new ArrayList<JobSheetBo>();
				    
				    /*jbo.setJobSheetId(resultSet.getInt(5));
				    jbo.setJobSheetDate(resultSet.getDate(6));
				    jbo.setJobSheetType(resultSet.getString(7));
				    jbo.setKilometers(resultSet.getInt(8));
				    jbo.setPetrolLevel(resultSet.getInt(9));
				    jbo.setEngineId(resultSet.getString(10));
				   
				    jobSheetBoList.add(jbo);
				    customerJobSheetBo.setJobsheetBoList(jobSheetBoList);
				    map.put(customerNo,customerJobSheetBo);*/
				    
				    //for optimizing the code
				    customerJobSheetBo.setJobsheetBoList(jobSheetBoList);
				    map.put(customerNo,customerJobSheetBo);
					
				}
				
				//for optimizing the performence we are removeing the else block
				/*else {
					
					//if the customer key is available then add JobSheetBo to List inside CustomerJobSheetBo class
					jbo = new JobSheetBo();
				    jbo.setJobSheetId(resultSet.getInt(5));
				    jbo.setJobSheetDate(resultSet.getDate(6));
				    jbo.setJobSheetType(resultSet.getString(7));
				    jbo.setKilometers(resultSet.getInt(8));
				    jbo.setPetrolLevel(resultSet.getInt(9));
				    jbo.setEngineId(resultSet.getString(10));
				    
				    CustomerJobSheetBo cjsb =(CustomerJobSheetBo)map.get(customerNo);
				    cjsb.getJobsheetBoList().add(jbo);
				
				}*/	
				jbo = new JobSheetBo();
				jbo.setJobSheetId(resultSet.getInt(5));
			    jbo.setJobSheetDate(resultSet.getDate(6));
			    jbo.setJobSheetType(resultSet.getString(7));
			    jbo.setKilometers(resultSet.getInt(8));
			    jbo.setPetrolLevel(resultSet.getInt(9));
			    jbo.setEngineId(resultSet.getString(10));
			    
			    CustomerJobSheetBo cjsb =(CustomerJobSheetBo)map.get(customerNo);
			    cjsb.getJobsheetBoList().add(jbo);
			}
			      for(Integer key : map.keySet()) {
			    	  CustomerJobSheetBo customerJobSheetBoEx =  map.get(key);
			    	  customerJobSheetBoList.add(customerJobSheetBoEx);
			      }
			//return customerJobSheetBo;
			return customerJobSheetBoList;
		}
		
	}
}
