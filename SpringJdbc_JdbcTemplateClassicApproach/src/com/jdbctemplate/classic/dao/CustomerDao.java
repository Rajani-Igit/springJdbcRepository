package com.jdbctemplate.classic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.StatementCallback;

import com.jdbctemplate.classic.bo.CustomerBo;

import oracle.jdbc.internal.OracleTypes;
import oracle.jdbc.oracore.OracleType;

public class CustomerDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public CustomerDao(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	   }
	
	/*There are lot of over rideing execute methods are available in jdbcTemplate class
	 * 
	 * execute(PreparedStatementCreator , PreapreStatementCallback);
	 *==>If the  query is the dynamic query and we have some positional paramaters as substitues at runtime definately we should go for PreparedStatement
	 *and we have to create those positional parameters as Attribues inside the creator class and we have to set those attributes as part creationg the object 
	 *to the class
	 *
	 *execute("sql query" , PreapreStatementCallback);
	 *==>This method we can use if the query is fixed means static query.we can use this execute overrided method from jdbcTemplate class
	 *so here we are free from creating Creator class.JdbcTemplate will create Statement Object by passing That Static Query as argument 
	 *
	 *execute(StatementCallback)
	 *==>This method we can used to execute the static querys or constant querys.so here we no need to create the Creator class,bcz they will not accept the
	 *positional parameters as part of normalStatement means dynamic querys are not possible
	 *
	 *execute(String sqlQuery)
	 *==>This method is used to perform the operations like Creating the table and altering the table,drop the table such kind of operations
	 *Here we no need of writing the Creator and caller classes
	 *
	 *execute(CallableStatementCreator ,CallableStatementCallback);
	 *==>This method is used to perform the operations related to stored procedure or functions written by PL/SQL language if the procedure is dynamic means 
	 *it is talking some parameters at runtime we can use this method and those parameters we have to declare those as attributes inside the class
	 *
	 *execute(String Sql Query ,CallableStatementCallback);
	 *==>This method we can used for executing the stored procedres and functions but if the query is fixed or query does not have any positional parameters 
	 *so insted of writing the one more creator class directly we can write here.So we can avoid of writing this class
	 *
	 * */
	
	    public List<CustomerBo> getCustomers(){
	    	
	    	  /*These are usefull for Prepared Statement Creators And Callbacks*/
	    	
	    	/*GetCustomersPreparedStatementCreator gcpcreator = new GetCustomersPreparedStatementCreator("sairam");
	    	GetCustomersPreparedStatementCallback gcpcallback = new GetCustomersPreparedStatementCallback();
	    	return jdbcTemplate.execute("select * from cusomer", gcpcallback);
	    	return jdbcTemplate.execute(gcpcreator, gcpcallback);*/
	    	
	    	  /*These are usefull for Statement Callbacks*/
	    	
	    	  //GetCustomersStatementCallback gcscallback = new GetCustomersStatementCallback();
	    	  //return jdbcTemplate.execute(gcscallback);
	    	return null;
	    	  
	    }
    
       /*These execute method will be used to perporm DDl Operations here*/
         public void createTable() {
    	   jdbcTemplate.execute("create table TestingTable(test_Id number(10),test_des Varchar2(20),test_data varchar2(20))");
       }

         public int getCustomerCallable() {
        	 
        	 /*These are usefull for CallableStatementCallback and CallableStatementCreator*/
        	 
           /* GetCustomersCallableStatementCreator gcscreator = new GetCustomersCallableStatementCreator(104,"Teja","Gokina");
         	GetCustomersCallableStatementCallback gcscallback = new GetCustomersCallableStatementCallback();
         	return jdbcTemplate.execute(gcscreator, gcscallback);*/
        	 return 0;
         }
         
          public List<CustomerBo> getCustomersStaticCallable(){
        	GetCustomersCallableStatementCreator gcscreator = new GetCustomersCallableStatementCreator();
           	GetCustomersCallableStatementCallback gcscallback = new GetCustomersCallableStatementCallback();
           	return jdbcTemplate.execute(gcscreator, gcscallback);
          }
         
         /*----------------------------------------------PreparedStatment---------------------------------------------------------*/
          
             /*This Classes are meant for performing operations through PreparedStatement Class Object*/
    
			/*private final class GetCustomersPreparedStatementCreator implements PreparedStatementCreator{
		        
				private String name;
				
				Constructor with named parameter it will be comming from Dao class as part of creating the Object to this class
				public GetCustomersPreparedStatementCreator(String name) {
					this.name = name;
				}
				

				@Override
		        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		        	PreparedStatement psmt = con.prepareStatement("select * from cusomer where firstname = ?");
		        	psmt.setString(1, name);
			    return psmt;
		        }
	         }
      
                private final class GetCustomersPreparedStatementCallback implements PreparedStatementCallback<List<CustomerBo>>{
		         @Override
		         public List<CustomerBo> doInPreparedStatement(PreparedStatement pstm) throws SQLException, DataAccessException {
		        	 ResultSet rs = null;
		        	 List<CustomerBo> list = new ArrayList<>();
		        	 CustomerBo customerBo = null;
		        	 rs = pstm.executeQuery();
		        	 while(rs.next()) {
		        		 customerBo = new CustomerBo();
		        		 customerBo.setCustmerNo(rs.getInt("customerNo"));
		        		 customerBo.setFirstName(rs.getString("firstName"));
		        		 customerBo.setLastName(rs.getString("lastName")); 
		        		 list.add(customerBo);
		        	 }
			     return list;
		         }	
  	          }*/
         
                   /*----------------------------------------------Statment---------------------------------------------------------*/
         
		    /*This Class is meant for performing operations through Statement Class Object and this we can not have creators 
		     * Bcz they will not allow us to write parameters in Normal Statements.if the query is static or constant we have to use Statement Callback
		
		        private final class GetCustomersStatementCallback implements StatementCallback<List<CustomerBo>>
		          {
				@Override
				public List<CustomerBo> doInStatement(Statement stmt) throws SQLException, DataAccessException {
					ResultSet rs = null;
		       	    List<CustomerBo> list = new ArrayList<>();
		       	    CustomerBo customerBo = null;
		       	    rs = stmt.executeQuery("select * from cusomer");
		       	     while(rs.next()) {
		       		   customerBo = new CustomerBo();
		       		   customerBo.setCustmerNo(rs.getInt("customerNo"));
		       		   customerBo.setFirstName(rs.getString("firstName"));
		       		   customerBo.setLastName(rs.getString("lastName")); 
		       		   list.add(customerBo);
		       	   }
			     return list;
				  }	
		        } */  
    
                 /*------------------------------------------CallableStatement with Inserting Query------------------------------------------------------*/
         
               
                /*These classes are ment for performing the Operations through CallableStatemnt Object
                 * These operations are helpfull in executing the stored procedures and functions through JavaCode
                 * Here we can use creator and callback or else only callback with static callable statement
                 * In this example we have discussed only insert query
                 * 
                 *  Procedures are 
                 * 
                 * CREATE or REPLACE
					PROCEDURE JdbcTemplateProcedure(id IN NUMBER,firstName IN VARCHAR2 ,lastName IN VARCHAR2, returnValue OUT NUMBER)
					is
					BEGIN
					returnValue := 0;
					if(returnValue = 0)
					THEN
					INSERT INTO CUSOMER VALUES(id,firstName,lastName);
					COMMIT;
					returnValue := 1;
					ENd IF;
					end ;
					/
					
					==>Executing the procedure in Sql Devloper
					
					declare
					returnValue number ;
					begin 
					JdbcTemplateProcedure(22,'sairam','talasila',returnValue);
					DBMS_OUTPUT.PUT_LINE(returnValue);
					end;
                 * 
                 * */
               /* private final class GetCustomersCallableStatementCreator implements CallableStatementCreator{
    		        
                	 private int custmerNo;
                	 private String firstName;
                	 private String lastName;
    				
    				//Constructor with named parameter it will be comming from Dao class as part of creating the Object to this class
    				public GetCustomersCallableStatementCreator(int custmerNo,String firstName ,String lastName) {
    					this.lastName = lastName;
    					this.custmerNo = custmerNo;
    					this.firstName = firstName;
    				}

					@Override
					public CallableStatement createCallableStatement(Connection con) throws SQLException {
						CallableStatement callableStatement = null;
						callableStatement = con.prepareCall("{call JdbcTemplateProcedure(?,?,?,?)}");
						callableStatement.setInt(1,custmerNo);
						callableStatement.setString(2,firstName);
						callableStatement.setString(3, lastName);
						callableStatement.registerOutParameter(4, Types.INTEGER);
						return callableStatement;
					}
    	         }
          
                    private final class GetCustomersCallableStatementCallback implements CallableStatementCallback<Integer>{
                       
					@Override
					public  Integer  doInCallableStatement(CallableStatement cstmt)
							throws SQLException, DataAccessException {
						System.out.println("callable Statement "+cstmt);
						cstmt.execute();
						System.out.println("after execute");
						int i = cstmt.getInt(4);
						return i;
					}	
      	          }*/
                    
                  /*------------------------------------------CallableStatement with static or DQL Query------------------------------------------------------*/
         
         
         /* 
          *  Procedures are 
          * 
		  *     CREATE or REPLACE
				PROCEDURE JdbcTemplateProcedureRetrive(retriveData_c OUT SYS_REFCURSOR)
				is
				BEGIN
				OPEN retriveData_c for SELECT * FROM CUSOMER;
				DBMS_OUTPUT.put_line('hello');
				end ;
				/
				
				==>Executing the procedure in Sql Devloper
				
				declare
				retriveData_c SYS_REFCURSOR;
				begin 
				JdbcTemplateProcedureRetrive(retriveData_c);
				end;
          * */
         
         private final class GetCustomersCallableStatementCreator implements CallableStatementCreator{
		        
				public GetCustomersCallableStatementCreator() {
					System.out.println("default constructor");
				}
				
				@Override
				public CallableStatement createCallableStatement(Connection con) throws SQLException {
					CallableStatement callableStatement = null;
					callableStatement = con.prepareCall("{call JdbcTemplateProcedureRetrive(?)}");
					callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
					return callableStatement;
				}
	         }
   
             private final class GetCustomersCallableStatementCallback implements CallableStatementCallback<List<CustomerBo>>{
                
				@Override
				public  List<CustomerBo>  doInCallableStatement(CallableStatement cstmt)
						throws SQLException, DataAccessException {
					CustomerBo customerBo = null;
					ResultSet rs = null;
		       	    List<CustomerBo> list = new ArrayList<>();
					System.out.println("callable Statement "+cstmt);
					cstmt.execute();
					System.out.println("after execute");
					rs  =(ResultSet) cstmt.getObject(1);
					while(rs.next()) {
			       		   customerBo = new CustomerBo();
			       		   customerBo.setCustmerNo(rs.getInt("customerNo"));
			       		   customerBo.setFirstName(rs.getString("firstName"));
			       		   customerBo.setLastName(rs.getString("lastName")); 
			       		   list.add(customerBo);
			       	   }
				     return list;
					  }	
					
				}
}                
