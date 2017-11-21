package com.mappingsqloperations.subclass.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.mappingsqloperations.subclass.bo.CustomerBo;

public class CustomerDao {
	/* It is highly recomended to follow the Conventions while declaring querys in Java Classes,Bcz if the Database Table got mdified or column added or removed 
	 * we have to modify those changes in lot of places inside the class so it is better to declare those querys at class level ans those querys are static and final
	 * we should not modified any where in side the class
	 * if the query is fetching only one record it is better to use GET or if the query is fetching one row of the records it is better to use FETCH or SERCH
	 * it is highly recomended to start with SQL latter depends on the operation you are performing provide that name means for INSERT INS and for DELETE DEL so on.
	 * 
	 * In Java Most Common Persistance Api is JDBC.Due to of some Drawbacks it will not support rapid application devlopment bcz APIs does not provide
	 * any Boiler Place code. So People slowly Started using ORM Related Technologies .But Sun Microsystems purely Against to it bcz of some drawbacks.
	 * they have certain limitations choosing ORM over JDBC
	 * 
	 * But Sun Suggested to use EJB(EntityJavaBeans) De to of heavy weight and requireds application server people are not that much intrested to work with
	 * this. Then sun people introduced JDO(Java Data Objects) These are exactly opposite to ORM.There One Entity will represent one table here 
	 * per sql query we will write one class.So that is the name MappingSqlOperations as sub classes
	 * 
	 * It contains two classes
	 * 
	 *   1)SqlUpdate
	 *   
	 *   2)SqlQuery
	 *   
	 *      |-> MappingSqlQuery
	 * 
	 * Second class contain child class These classes are usefull to perform insert ,update ,delete ,querying operations.Here we have to write a class
	 * that extends from any of the classes.incase of MappingSqlQuery we have to override one method mapRow incase of SqlUpdate you just can call
	 * update() on supre class object so that method is not available inside the super class ,then child class method will be called
	 * */
	
	
	private final String SQL_INST_CUSTOMER = "INSERT INTO CUSOMER(customerno ,firstname ,lastname ,mobibeno) VALUES (? ,? ,? ,?)";
	private final String SQL_GET_ALL_CUSTOMERS ="SELECT customerno ,firstname ,lastname ,mobibeno from CUSOMER";	
	private final String SQL_GET_CUSTOMER ="SELECT customerno ,firstname ,lastname ,mobibeno from CUSOMER where lastname = ? ";	
	SaveCustomer saveCustomer;
	GetAllCustomers getAllCustomers;
	GetCustomer getCustomer;
	private DataSource dataSource;
	
	/*Parameterized constructor to inject SimpleJdbcCall class*/
  public CustomerDao(DataSource dataSource) {
	  System.out.println("inside Dao class constructor");
	this.dataSource = dataSource;
	saveCustomer = new SaveCustomer(dataSource);
	getAllCustomers = new GetAllCustomers(dataSource);
	getCustomer = new GetCustomer(dataSource);
  }
  
  public int saveCoustomer(CustomerBo customerBo) {
	  return  saveCustomer.insertCustomer(new Object[] {customerBo.getCustmerNo() ,customerBo.getFirstName() ,customerBo.getLastName() ,customerBo.getMobileNo()});   
  }
  
  //For a Particular Customer
  public CustomerBo getCustomer(String lastName) {
	   return getCustomer.findObject(lastName);
  }
 
  //For List of Customers
  public List<CustomerBo> getCustomers() {
	   return getAllCustomers.execute();
  }
  
  //This class is used for inserting customer details into Table
  private final class SaveCustomer extends SqlUpdate{
	  
	   public SaveCustomer(DataSource dataSource) {
		  super(dataSource ,SQL_INST_CUSTOMER);	  
		  declareParameter(new SqlParameter("customerno" ,Types.INTEGER));
		  declareParameter(new SqlParameter("firstname" ,Types.VARCHAR));
		  declareParameter(new SqlParameter("lastname" ,Types.VARCHAR));
		  declareParameter(new SqlParameter("mobibeno" ,Types.VARCHAR));
		  compile();
	    }

	   public int insertCustomer(Object[] params) {
		   return update(params);
	   }
  }
  
  //This class is mean for retriveing All the Customer Details from DataBase either all or one
    private final class GetAllCustomers extends MappingSqlQuery<CustomerBo>{
        
    	 public GetAllCustomers(DataSource dataSource) {
    		super(dataSource ,SQL_GET_ALL_CUSTOMERS);
			compile();
		}
		@Override
		protected CustomerBo mapRow(ResultSet resultSet, int rowNo) throws SQLException {
			System.out.println("inside mapRow method for all customers");
			CustomerBo customerBo = null;
			customerBo = new CustomerBo();
			customerBo.setCustmerNo(resultSet.getInt(1));
			customerBo.setFirstName(resultSet.getString(2));
			customerBo.setLastName(resultSet.getString(3));
			customerBo.setMobileNo(resultSet.getString(4));
			return customerBo;
		}	
    }
    
    
    //This class is mean for retriveing Only one Customer Details from DataBase either all or one
    private final class GetCustomer extends MappingSqlQuery<CustomerBo>{
        
    	 public GetCustomer(DataSource dataSource) {
    		
    		super(dataSource ,SQL_GET_CUSTOMER);
    		 System.out.println("inside constructor");
    		declareParameter(new SqlParameter("customerno" ,Types.VARCHAR));
			compile();
		}
		@Override
		protected CustomerBo mapRow(ResultSet resultSet, int rowNo) throws SQLException {
			System.out.println("inside mapRow method for only one customer");
			CustomerBo customerBo = null;
			customerBo = new CustomerBo();
			customerBo.setCustmerNo(resultSet.getInt(1));
			customerBo.setFirstName(resultSet.getString(2));
			customerBo.setLastName(resultSet.getString(3));
			customerBo.setMobileNo(resultSet.getString(4));
			return customerBo;
		}	
    }

}
