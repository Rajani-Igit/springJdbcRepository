package com.npjdbctemplate.allopr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.npjdbctemplate.allopr.bo.CustomerBo;

public class CustomerDao {
	/* It is highly recomended to follow the Conventions while declaring querys in Java Classes,Bcz if the Database Table got mdified or column added or removed 
	 * we have to modify those changes in lot of places inside the class so it is better to declare those querys at class level ans those querys are static and final
	 * we should not modified any where in side the class
	 * if the query is fetching only one record it is better to use GET or if the query is fetching one row of the records it is better to use FETCH or SERCH
	 * it is highly recomended to start with SQL latter depends on the operation you are performing provide that name means for INSERT INS and for DELETE DEL so on.
	 * 
	 *  This class is usefull to perform almost all operations as JdbcTemplate will Do as it is not child class of JdbcTemplate but it will perform all the 
	 *  operations as JdbcTeplate will do.more over the methods which are avilable in JdbcTemplate are avilable in NamedParamterJdbcTemplate
	 *  
	 *  There are disadvantages of working with positional paramter
	 *  1)one can not simply substitute the values of positional parameters
	 *  2)if we misplace the position of value may be it miss leads to exception or misplacing of the data.main intention is to avoid kayas(confussion) problem
	 *  
	 *  In case of NamedParameterJdbcTemplate Approach we can perform Insert ,Update ,Delete ,select but we have to provide Named Parameter which is start 
	 *  with colon(:) before it we have to specify space so that it will identify that this should be replaced with the value at runtime.
	 *  This will diffrentiate the substitutes and clauses 
	 *  
	 *  There are 3 ways we can pass these paramters 
	 *  1)Map by declaring parameters names as keys and values as objects
	 *  2)ParameterSource
	 *    -->It is an interface and contains two implementation classes are there
	 *    1)MapSqlParameterSource
	 *    2)BeanPropertySqlParameterSource
	 * */
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  
  private final String SQL_GET_NO_OF_CUST =  "select count(1) from cusomer where FIRSTNAME like :firstname " ;
  private final String SQL_FND_FIRST_NM_BASED_ON_MOBILE_NO = "select firstname from cusomer where mobibeno = :mobileno";
  private final String SQL_FETCH_CUSTOMER_ROW_BASED_ON_MOBILE_NO = "select customerno ,firstname ,lastname ,mobibeno from cusomer where mobibeno = :mobileno";
  private final String SQL_FETCH_CUSTOMERS = "select customerno ,firstname ,lastname ,mobibeno from cusomer where FIRSTNAME like :firstname";
  private final String SQL_INST_CUSTOMER ="INSERT INTO cusomer(customerno ,firstname ,lastname ,mobibeno) VALUES(:custmerNo ,:firstName ,:lastName ,:mobileNo) ";
  private final String SQL_UPD_CUSTOMER_BY_CUST_NO = "update cusomer set firstname = :firstname where customerno = :customerno";

  public CustomerDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
	this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }
  
  
  /* This method we are performing count(1) or aggregate function operations we can not simply get all the customer objects bcz we have to provide 
   * atleast one named parameter.Here we are providing paramters inside the map as keys and values as well
   * and return type we should method the type of class weather it is integer or long or String or e.t.c same as like in JdbcTemplate class
   */
  public int getCustomerCount(String firstName) {
	  Map<String ,Object> paramMap = null;
	  paramMap = new HashMap<String ,Object>();
	  paramMap.put("firstname", "%"+firstName+"%");
	  return namedParameterJdbcTemplate.queryForObject(SQL_GET_NO_OF_CUST, paramMap, Integer.class);
	  
  }
  
  /*This method is same as like above method but the difference is that here we are finding out the firstname based on mobile no*/
  public String getCustomerFirstName(String mobileNo) {
	  Map<String ,Object> paramMap = null;
	  paramMap = new HashMap<String ,Object>();
	  paramMap.put("mobileno", mobileNo);
	   return namedParameterJdbcTemplate.queryForObject(SQL_FND_FIRST_NM_BASED_ON_MOBILE_NO, paramMap,String.class);   
  }
  
  /*This method is used to fetch the single customer object based on mobile number. to do this we required rowmapper object to itrate over one by one*/
  public CustomerBo getCustomerDetails(String mobileNo) {
	  Map<String ,String> paramMap = null;
	  paramMap = new HashMap<String ,String>();
	  paramMap.put("mobileno", mobileNo);
	  return namedParameterJdbcTemplate.queryForObject(SQL_FETCH_CUSTOMER_ROW_BASED_ON_MOBILE_NO, paramMap ,new CustomerDetailsRowMapper());
  }	 
  
  /*This method is used for fetching the list of customer objects*/
  public List<CustomerBo> getCustomers(String firstName){
	  Map<String ,String> paramMap = null;
	  paramMap = new HashMap<String ,String>();
	  paramMap.put("firstname", "%"+firstName+"%");
	  return namedParameterJdbcTemplate.query(SQL_FETCH_CUSTOMERS, paramMap,new CustomerDetailsRowMapper());
  } 
  
  /*This method is used for fetching the list of customer objects*/
  public int saveCustomer(CustomerBo customerBo){

	  //Here we are using BeanPropertyParameterSource it is almost same as Map insted of we providing as key value pairs this class it self will do
	  BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(customerBo);
	  return namedParameterJdbcTemplate.update(SQL_INST_CUSTOMER, beanPropertySqlParameterSource);
  } 
  
  /*This method is used for updateing the customer firstname based on firstname*/
  public int updateCustomer(int custNo ,String firstName){
	  Map<String ,Object> paramMap = null;
	  paramMap = new HashMap<String ,Object>();
	  paramMap.put("firstname", firstName);
	  paramMap.put("customerno", custNo);
	  return namedParameterJdbcTemplate.update(SQL_UPD_CUSTOMER_BY_CUST_NO, paramMap);
  } 
  
  //BeanPropertyRowMapper is a class it will directly binds the propertys to Bean Directly
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
