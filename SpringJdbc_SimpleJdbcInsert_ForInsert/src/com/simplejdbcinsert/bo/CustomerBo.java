package com.simplejdbcinsert.bo;

public class CustomerBo {
	 private int customerno;
	 private String firstname;
	 private String lastname;
	 private String mobibeno;
	public int getCustomerno() {
		return customerno;
	}
	public void setCustomerno(int customerno) {
		this.customerno = customerno;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMobibeno() {
		return mobibeno;
	}
	public void setMobibeno(String mobibeno) {
		this.mobibeno = mobibeno;
	}
	@Override
	public String toString() {
		return "CustomerBo [customerno=" + customerno + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", mobibeno=" + mobibeno + "]";
	}
	
}
