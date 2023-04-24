package com.nandan.shop.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name="customer_details")
@NamedQuery(name="UserDetail.findAll", query="SELECT u FROM UserDetail u")
public class UserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Column(name="customer_id")
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="account_details")
	private String accountDetails;

	@Column(name="f_name")
	private String fName;

	@Column(name="l_name")
	private String lName;

	private String location;

	@Column(name="m_name")
	private String mName;
	
	@Column(name="phone")
	private String phone;
	
	
	@Column(name="bengali_name")
	private String bengaliName;

	
	@javax.persistence.Transient
	private boolean isTransactionAvailable; 
	
	

	public boolean isTransactionAvailable() {
		if(this.getTransactionDtl().size()>0){
			return true;}
		else{
		    return false;
		}
	}

	

	//bi-directional many-to-one association to DailyTransaction
	@JsonIgnore
	@OneToMany(mappedBy="userDetail",fetch=FetchType.EAGER)
	private List<TransactionDtl> transactionDtl;
	
	

	public List<TransactionDtl> getTransactionDtl() {
		return transactionDtl;
	}

	public void setTransactionDtl(List<TransactionDtl> transactionDtl) {
		this.transactionDtl = transactionDtl;
	}

	public UserDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountDetails() {
		return this.accountDetails;
	}

	public void setAccountDetails(String accountDetails) {
		this.accountDetails = accountDetails;
	}

	public String getFName() {
		return this.fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getLName() {
		return this.lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMName() {
		return this.mName;
	}

	public void setMName(String mName) {
		this.mName = mName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBengaliName() {
		return bengaliName;
	}

	public void setBengaliName(String bengaliName) {
		this.bengaliName = bengaliName;
	}

	
	

}