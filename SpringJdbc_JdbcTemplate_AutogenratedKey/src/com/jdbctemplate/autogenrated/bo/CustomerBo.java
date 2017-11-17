package com.jdbctemplate.autogenrated.bo;

public class CustomerBo {
	 private int custNo;
	 private String firstName;
	 private String lastName;
	 private String mobileNo;
	 private String emailAddress;
		public int getCustNo() {
			return custNo;
		}
		public void setCustNo(int custNo) {
			this.custNo = custNo;
		}
		public String getFirstName() {
			return firstName;
		}
		@Override
		public String toString() {
			return "CustomerBo [custNo=" + custNo + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", mobileNo=" + mobileNo + ", emailAddress=" + emailAddress + "]";
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getMobileNo() {
			return mobileNo;
		}
		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}
		public String getEmailAddress() {
			return emailAddress;
		}
		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}
 
}
