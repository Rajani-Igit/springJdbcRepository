package com.jdbctemplate.classic.bo;

public class CustomerBo {
 private int custmerNo;
 private String firstName;
 private String lastName;
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
	return "CustomerBo [custmerNo=" + custmerNo + ", firstName=" + firstName + ", lastName=" + lastName + "]";
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
