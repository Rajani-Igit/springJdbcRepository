package com.simplejdbccall.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.simplejdbccall.bo.CustomerBo;

public class CustomerDao {
	/* It is highly recomended to follow the Conventions while declaring querys in Java Classes,Bcz if the Database Table got mdified or column added or removed 
	 * we have to modify those changes in lot of places inside the class so it is better to declare those querys at class level ans those querys are static and final
	 * we should not modified any where in side the class
	 * if the query is fetching only one record it is better to use GET or if the query is fetching one row of the records it is better to use FETCH or SERCH
	 * it is highly recomended to start with SQL latter depends on the operation you are performing provide that name means for INSERT INS and for DELETE DEL so on.
	 * 
	 *  This class is usefull to perform insert operation in simple way.The name itself will says that it will perform only Insert operations
	 *  In case of normal approaches we are 
	 * 
	 *  There are 3 ways we can pass these paramters 
	 *  1)Map by declaring parameters names as keys and values as objects
	 *  2)SqlParameterSource
	 *    -->It is an interface and contains two implementation classes are there
	 *    1)MapSqlParameterSource
	 *    2)BeanPropertySqlParameterSource 
	 *    
	 * Here we have to provide the Stored Procedure Names, and at the time of setting in parameters we have to take the names which are same as
	 * Procedure In Parameters here we can achieve Database independent Operarions.internally it will create callable statement and it will pass the 
	 * in Parameters.Those Parameters we can send either of the three ways.
	 * incase of BeanPropertySqlParameterSource we have to create the bean that attributes should exactly looks like Procedure In ParameterNames
	 * The return type should be comes as Map there key is the OutParameter in procedure by iterating you will find those values
	 * */
	
  private SimpleJdbcCall simpleJdbcCall;
  
  /*Parameterized constructor to inject SimpleJdbcCall class*/
  public CustomerDao(SimpleJdbcCall simpleJdbcCall) {
	this.simpleJdbcCall = simpleJdbcCall;
	simpleJdbcCall.setProcedureName("saveCustomer");
	simpleJdbcCall.compile();
	System.out.println("compiled sucessfully");
  }
  
  public long saveCoustomer(CustomerBo customerBo) {
	  
	  //This is first way of defineing the Parameters
	  Map<String ,Object> map = new HashMap<String ,Object>();
	  
	  //This is the second way of defineing the parameters
	  MapSqlParameterSource  mapSqlParameterSource = null;
	  mapSqlParameterSource = new MapSqlParameterSource();
	  mapSqlParameterSource.addValue("customerno", customerBo.getCustomerno());
	  mapSqlParameterSource.addValue("firstname", customerBo.getFirstname());
	  mapSqlParameterSource.addValue("lastname", customerBo.getLastname());
	  mapSqlParameterSource.addValue("mobibeno", customerBo.getMobibeno());
	  
	  
	  //This is the third way of defineing the Parameters
	  BeanPropertySqlParameterSource beanPropertySqlParameterSource = null;
	  beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(customerBo);

	  Map<String , Object> returningValue = simpleJdbcCall.execute(mapSqlParameterSource);
	  System.out.println(returningValue);
	  return (Long)returningValue.get("RES");
  }

}
