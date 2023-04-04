package com.nandan.shop.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TransactionDetails {
	public Long customerId;
	public String customerName;
	public String transactionDate;
	public BigDecimal dipAmount;
	public BigDecimal widAmount;
	public BigDecimal dipGold;
	public BigDecimal widGold;
	public BigDecimal dipSilver;
	public BigDecimal widSilver;
	public String transactionType;
	public String desc;
	public String tranccat;
	public Long transactID;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public BigDecimal getDipAmount() {
		return dipAmount;
	}
	public void setDipAmount(BigDecimal dipAmount) {
		this.dipAmount = dipAmount;
	}
	public BigDecimal getWidAmount() {
		return widAmount;
	}
	public void setWidAmount(BigDecimal widAmount) {
		this.widAmount = widAmount;
	}
	public BigDecimal getDipGold() {
		return dipGold;
	}
	public void setDipGold(BigDecimal dipGold) {
		this.dipGold = dipGold;
	}
	public BigDecimal getWidGold() {
		return widGold;
	}
	public void setWidGold(BigDecimal widGold) {
		this.widGold = widGold;
	}
	public BigDecimal getDipSilver() {
		return dipSilver;
	}
	public void setDipSilver(BigDecimal dipSilver) {
		this.dipSilver = dipSilver;
	}
	public BigDecimal getWidSilver() {
		return widSilver;
	}
	public void setWidSilver(BigDecimal widSilver) {
		this.widSilver = widSilver;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getTranccat() {
		return tranccat;
	}
	public void setTranccat(String tranccat) {
		this.tranccat = tranccat;
	}
	public Long getTransactID() {
		return transactID;
	}
	public void setTransactID(Long transactID) {
		this.transactID = transactID;
	}
	
	

}
