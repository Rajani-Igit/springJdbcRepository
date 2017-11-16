package com.jdbctemplate.rsextractor.pagination.bo;

import java.util.Date;

public class JobSheetBo {
	
  private int jobSheetId;
  private Date jobSheetDate;
  private String jobSheetType;
  private int kilometers;
  private int petrolLevel;
  private String engineId;
  private int customerNo;
		public int getJobSheetId() {
			return jobSheetId;
		}
		public void setJobSheetId(int jobSheetId) {
			this.jobSheetId = jobSheetId;
		}
		public Date getJobSheetDate() {
			return jobSheetDate;
		}
		public void setJobSheetDate(Date jobSheetDate) {
			this.jobSheetDate = jobSheetDate;
		}
		public String getJobSheetType() {
			return jobSheetType;
		}
		public void setJobSheetType(String jobSheetType) {
			this.jobSheetType = jobSheetType;
		}
		public int getKilometers() {
			return kilometers;
		}
		public void setKilometers(int kilometers) {
			this.kilometers = kilometers;
		}
		public int getPetrolLevel() {
			return petrolLevel;
		}
		public void setPetrolLevel(int petrolLevel) {
			this.petrolLevel = petrolLevel;
		}
		public String getEngineId() {
			return engineId;
		}
		public void setEngineId(String engineId) {
			this.engineId = engineId;
		}
		public int getCustomerNo() {
			return customerNo;
		}
		public void setCustomerNo(int customerNo) {
			this.customerNo = customerNo;
		}
		
		@Override
		public String toString() {
			return "JobSheetBo [jobSheetId=" + jobSheetId + ", jobSheetDate=" + jobSheetDate + ", jobSheetType="
					+ jobSheetType + ", kilometers=" + kilometers + ", petrolLevel=" + petrolLevel + ", engineId="
					+ engineId + ", customerNo=" + customerNo + "]";
		}
}
