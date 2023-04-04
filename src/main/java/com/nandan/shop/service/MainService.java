package com.nandan.shop.service;

import java.util.List;

import com.nandan.shop.dto.BalanceDetails;
import com.nandan.shop.dto.CustomerDetials;
import com.nandan.shop.dto.TransactionDetails;
import com.nandan.shop.entity.UserDetail;

public interface MainService {
	
	public CustomerDetials addCustomer(CustomerDetials customer);

	public TransactionDetails addTransaction(TransactionDetails transaction);

	public List<CustomerDetials> getAllCustomer();

	public List<TransactionDetails> getTransactionBYCustomer(Long customer);
	public BalanceDetails getOverAllBalance(List<TransactionDetails> data);

	public TransactionDetails getTransactionByID(Long tid)throws Exception ;

	public void updateTransaction(TransactionDetails transaction);

	public CustomerDetials getCuastomer(Long customerid);

	

}
