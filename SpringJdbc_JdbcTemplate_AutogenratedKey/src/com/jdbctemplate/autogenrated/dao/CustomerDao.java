package com.jdbctemplate.autogenrated.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jdbctemplate.autogenrated.bo.CustomerBo;

public class CustomerDao {
	private JdbcTemplate jdbcTemplate;
	
	private String INS_CUSTMER_WITH_AUTO_GEN_KEY = "INSERT INTO CUSTOMER_AUTO_GEEN_KEY(CUST_FIRST_NAME ,CUST_LAST_NAME ,CUST_MOBILE ,CUST_EMAIL) VALUES(?,?,?,?)";
	
	public CustomerDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int insertCustomer(CustomerBo customerBo) {
		System.out.println("inside the insertCustomer metod");
		int i = jdbcTemplate.update(INS_CUSTMER_WITH_AUTO_GEN_KEY, new Object[] {customerBo.getFirstName() ,customerBo.getLastName() ,customerBo.getMobileNo() ,customerBo.getEmailAddress()});
		return i;
	}
	
	public int getAutogenratedKeyValue(CustomerBo customerBo) {
		System.out.println("inside autogenrated key value class");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int i =jdbcTemplate.update(new CustomerDaoPreparedStatementCreator(customerBo), keyHolder);
		Map<String,Object> map = keyHolder.getKeys();
		System.out.println("map object "+map);
		return i;
	}
	
	private final class CustomerDaoPreparedStatementCreator implements PreparedStatementCreator{

		private CustomerBo customerBo;
		
		CustomerDaoPreparedStatementCreator(CustomerBo customerBo){
			System.out.println("inside constructor  "+customerBo);
			this.customerBo = customerBo;
		}
		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			System.out.println("inside prep method");
			PreparedStatement pst = null;
			
			pst = con.prepareStatement(INS_CUSTMER_WITH_AUTO_GEN_KEY,new String[] {"CUSTNO"});
			pst.setString(1, customerBo.getFirstName());
			pst.setString(2, customerBo.getLastName());
			pst.setString(3, customerBo.getMobileNo());
			pst.setString(4, customerBo.getEmailAddress());
			System.out.println("inside prep stmt "+pst);
			return pst;
		}
		
	}
}
