package com.nandan.shop.dto;

public class CustomerDetials {
	private Long id;
	private String customerFName;
	private String customerLName;
	private String address;
	private String phoneno;
	private String bengaliName;
	
	
	public String getBengaliName() {
		return bengaliName;
	}
	public void setBengaliName(String bengaliName) {
		this.bengaliName = bengaliName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomerFName() {
		return customerFName;
	}
	public void setCustomerFName(String customerFName) {
		this.customerFName = customerFName;
	}
	public String getCustomerLName() {
		return customerLName;
	}
	public void setCustomerLName(String customerLName) {
		this.customerLName = customerLName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	

}
