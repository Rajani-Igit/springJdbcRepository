package com.jdbctemplate.query.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.jdbctemplate.query.bo.CustomerBo;

public class CustomerDao {
	/*It is highly recomended to follow the Conventions while declaring querys in Java Classes,Bcz if the Database Table got mdified or column added or removed 
	 * we have to modify those changes in lot of places inside the class so it is better to declare those querys at class level ans those querys are static and final
	 * we should not modified any where in side the class
	 * if the query is fetching only one record it is better to use GET or if the query is fetching one row of the records it is better to use FETCH or SERCH
	 * it is highly recomended to start with SQL latter depends on the operation you are performing provide that name means for INSERT INS and for DELETE DEL so on.
	 *  
	 * */
  private JdbcTemplate jdbcTemplate;
  
  private final String SQL_GET_NO_OF_CUST =  "select count(1) from cusomer" ;
  private final String SQL_FND_FIRST_NM_BASED_ON_MOBILE_NO = "select firstname from cusomer where mobibeno =?";
  private final String SQL_FETCH_CUSTOMER_ROW_BASED_ON_MOBILE_NO = "select customerno ,firstname ,lastname ,mobibeno from cusomer where mobibeno =?";
  private final String SQL_FETCH_CUSTOMERS = "select customerno ,firstname ,lastname ,mobibeno from cusomer";

  public CustomerDao(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
	for(Class subClass : jdbcTemplate.getClass().getClasses()) {
		System.out.println(subClass);
	}
  }
  
  public int getCustomerCount() {
	  return jdbcTemplate.queryForObject(SQL_GET_NO_OF_CUST, Integer.class);
  }
  
  public Object getCustomerFirstName(String mobileNo) {
	   return jdbcTemplate.queryForObject(SQL_FND_FIRST_NM_BASED_ON_MOBILE_NO, String.class, new Object[] {mobileNo});
	   
  }
  
  public CustomerBo getCustomerDetails(String mobileNo) {
	  return jdbcTemplate.queryForObject(SQL_FETCH_CUSTOMER_ROW_BASED_ON_MOBILE_NO, new Object[] {mobileNo},new CustomerDetailsRowMapper());
	 // jdbcTemplate.queryForOb
  }
  
  public List<CustomerBo> getCustomers(){
	  return jdbcTemplate.query(SQL_FETCH_CUSTOMERS,new CustomerDetailsRowMapper());
  }
  
  private final class CustomerDetailsRowMapper implements RowMapper<CustomerBo>{
     CustomerBo customerBo;
	@Override
	public CustomerBo mapRow(ResultSet resultSet, int count) throws SQLException {  
   		 customerBo = new CustomerBo();
   		 customerBo.setCustmerNo(resultSet.getInt("customerNo"));
   		 customerBo.setFirstName(resultSet.getString("firstName"));
   		 customerBo.setLastName(resultSet.getString("lastName")); 
   		 customerBo.setMobileNo(resultSet.getString("mobibeNo"));
		return customerBo;
	}
	  
  }
  
}
