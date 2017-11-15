package com.jdbctemplate.query.bo;

public class CustomerBo {
	 private int custmerNo;
	 private String firstName;
	 private String lastName;
	 private String mobileNo;
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public int getCustmerNo() {
		return custmerNo;
	}
	public void setCustmerNo(int custmerNo) {
		this.custmerNo = custmerNo;
	}
	public String getFirstName() {
		return firstName;
	}
	@Override
	public String toString() {
		return "CustomerBo [custmerNo=" + custmerNo + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobileNo=" + mobileNo + "]";
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
}
