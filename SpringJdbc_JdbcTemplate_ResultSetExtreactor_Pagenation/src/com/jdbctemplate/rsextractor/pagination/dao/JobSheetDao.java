package com.jdbctemplate.rsextractor.pagination.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.jdbctemplate.rsextractor.pagination.bo.JobSheetBo;

public class JobSheetDao {
  
  private JdbcTemplate jdbcTemplate;
  
  private String SQL_GET_JOBSHEETS = "SELECT JOBSHEETID ,JOBSHEETDATE ,JOBSHEETTYPE ,KILOMETERS ,PETROLLEVEL, ENGINEID from JOBSHEETS";
  private String SQL_GET_JOBSHEETS_WITH_PAGINATION = "select * from (select * from (select js.*,ROWNUM row_num from JOBSHEETS js  where ROWNUM<=5) WHERE row_num >=2);";
  public JobSheetDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
  }
  
  public List<JobSheetBo> getJobSheets(int pageNo ,int pageSize){
	  return jdbcTemplate.query(SQL_GET_JOBSHEETS, new PaginationResultSetExtractor(pageNo, pageSize));
  }
  
  private final class PaginationResultSetExtractor implements ResultSetExtractor<List<JobSheetBo>>{

	  private int pageNo;
	  private int pageSize;
	  
	  private int startIndex ;
	  private int endIndex;
	  
	public PaginationResultSetExtractor(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
    private List<JobSheetBo> jobSheetBoList;
    
	@Override
	public List<JobSheetBo> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		jobSheetBoList = new ArrayList<JobSheetBo>();
		JobSheetBo jobSheetBo = null; 
		startIndex = pageSize * (pageNo-1)+1;
		endIndex = startIndex+pageSize-1;
		int count = 1;
		while(resultSet.next() && count <= endIndex) {
			if(count >= startIndex  && count <= endIndex) {
				jobSheetBo = new JobSheetBo();	
				jobSheetBo.setJobSheetId(resultSet.getInt(1));
				jobSheetBo.setJobSheetDate(resultSet.getDate(2));
				jobSheetBo.setJobSheetType(resultSet.getString(3));
				jobSheetBo.setKilometers(resultSet.getInt(4));
				jobSheetBo.setPetrolLevel(resultSet.getInt(5));
				jobSheetBo.setEngineId(resultSet.getString(6));
				jobSheetBoList.add(jobSheetBo);
			}
			count++;
		}
		return jobSheetBoList;
	}
	  
  }
}
