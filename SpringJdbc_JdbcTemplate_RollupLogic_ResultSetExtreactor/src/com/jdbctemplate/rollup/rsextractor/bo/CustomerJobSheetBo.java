package com.jdbctemplate.rollup.rsextractor.bo;

import java.util.List;
import java.util.Map;

public class CustomerJobSheetBo {
	 @Override
	public String toString() {
		return "CustomerJobSheetBo [customerBo=" + customerBo + ", jobsheetBoList=" + jobsheetBoList + "]";
	}
	private CustomerBo customerBo;
	 private List<JobSheetBo> jobsheetBoList;

		public CustomerBo getCustomerBo() {
			return customerBo;
		}
		public void setCustomerBo(CustomerBo customerBo) {
			this.customerBo = customerBo;
		}
		public List<JobSheetBo> getJobsheetBoList() {
			return jobsheetBoList;
		}
		public void setJobsheetBoList(List<JobSheetBo> jobsheetBoList) {
			this.jobsheetBoList = jobsheetBoList;
		}
		
	 
}
