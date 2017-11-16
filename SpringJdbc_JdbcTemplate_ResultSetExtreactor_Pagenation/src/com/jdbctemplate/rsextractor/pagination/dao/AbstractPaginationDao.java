package com.jdbctemplate.rsextractor.pagination.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.jdbctemplate.rsextractor.pagination.bo.JobSheetBo;

  public abstract class AbstractPaginationDao implements ResultSetExtractor<List<Object>>{
	  
	  protected int pageSize;
	  protected int pageNo;
	 
	 //this constructor will be called when we extend this class,where ever you required pagination
	 protected AbstractPaginationDao(int pageSize ,int pageNo) {
		 this.pageNo = pageNo;
		 this.pageSize = pageSize;
	}
    
	 @Override
    public List<Object> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		 List<Object> listObjects = null;
		 int startIndex = 0;
		 int endIndex =0;
		 
		 startIndex = (pageSize *(pageNo-1))+1;
		 endIndex = (startIndex+pageSize)-1;
		 listObjects = new ArrayList<Object>();
		 int count = 1;
		 while(resultSet.next() && count <= endIndex) {
			 if(count >= startIndex && count <= endIndex) {
				 Object object = rowMap(resultSet);
				 listObjects.add(object);	 
			 }
			 count++;
		 }
    	return listObjects;
    }
    
    //this is abstract method we have to over ride this method when we extend this class
	 protected abstract Object rowMap(ResultSet resultSet)throws SQLException;
}
