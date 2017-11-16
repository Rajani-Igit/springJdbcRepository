package com.jdbctemplate.rsextractor.pagination.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.jdbctemplate.rsextractor.pagination.bo.JobSheetBo;

public class JobSheetDaoWithAbstractImpl  {
	
	private final String GET_JOBSHEETS_WITH_PAGINATION_TEMPLATE_DP = "SELECT JOBSHEETID ,JOBSHEETDATE ,JOBSHEETTYPE ,KILOMETERS ,PETROLLEVEL, ENGINEID from JOBSHEETS";
    
	private JdbcTemplate jdbcTemplate;
	private JobSheetBo jobSheetBo;
    
	public JobSheetDaoWithAbstractImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Object> getPaginatedJobSheets(){
		return jdbcTemplate.query(GET_JOBSHEETS_WITH_PAGINATION_TEMPLATE_DP, new AbstractPaginationResultSetExtractor(3, 4) );
	}

	private final class AbstractPaginationResultSetExtractor  extends AbstractPaginationDao{

		protected AbstractPaginationResultSetExtractor(int pageSize, int pageNo) {
			super(pageSize, pageNo);
		}

		@Override
		public Object rowMap(ResultSet resultSet) throws SQLException {
			jobSheetBo = new JobSheetBo();	
			jobSheetBo.setJobSheetId(resultSet.getInt(1));
			jobSheetBo.setJobSheetDate(resultSet.getDate(2));
			jobSheetBo.setJobSheetType(resultSet.getString(3));
			jobSheetBo.setKilometers(resultSet.getInt(4));
			jobSheetBo.setPetrolLevel(resultSet.getInt(5));
			jobSheetBo.setEngineId(resultSet.getString(6));
			return jobSheetBo;
		}
		
	}
}
