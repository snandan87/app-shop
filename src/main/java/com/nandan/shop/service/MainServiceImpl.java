package com.nandan.shop.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nandan.shop.dto.BalanceDetails;
import com.nandan.shop.dto.CustomerDetials;
import com.nandan.shop.dto.TransactionDetails;
import com.nandan.shop.entity.TransactionDtl;
import com.nandan.shop.entity.UserDetail;
import com.nandan.shop.repository.TransactionRepository;
import com.nandan.shop.repository.UserDetailsRepository;

@Service
@Transactional
public class MainServiceImpl implements MainService {
	@Autowired
	public UserDetailsRepository userData;
	@Autowired
	public TransactionRepository transrepo;

	@Override
	public CustomerDetials addCustomer(CustomerDetials customer) {
		UserDetail data = new UserDetail();
		data.setId(null);
		data.setFName(customer.getCustomerFName());
		data.setLName(customer.getCustomerLName());
		data.setLocation(customer.getAddress());
		data.setPhone(customer.getPhoneno());
		userData.save(data);
		customer.setId(data.getId());
		return customer;
	}

	@Override
	public TransactionDetails addTransaction(TransactionDetails transaction) {
		Optional<UserDetail> customer = userData.findById(transaction.getCustomerId());
		if (customer.isPresent()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				TransactionDtl data = new TransactionDtl();
				data.setUserDetail(customer.get());
				data.setAmountDip(transaction.getDipAmount());
				data.setAmountwith(transaction.getWidAmount());
				data.setGoldDip(transaction.getDipGold());
				data.setGoldWith(transaction.getWidGold());
				data.setSilverDip(transaction.getDipSilver());
				data.setSilverWith(transaction.getWidSilver());
				data.setTransDate(sdf.parse(transaction.getTransactionDate()));
				data.setTransactionId(null);
				data.setTransType(transaction.getTransactionType());
				data.setTransStat(0);
				data.setTransDescription(transaction.getDesc());
				data=transrepo.save(data);
				transaction.setTransactID(data.getTransactionId());
			} catch (Exception e) {
				return null;
			}
			return transaction;
		} else {
			return null;
		}

	}

	@Override
	public List<CustomerDetials> getAllCustomer() {
		List<CustomerDetials> data = new ArrayList<CustomerDetials>();
		userData.findAll().forEach(each -> {
			CustomerDetials obj = new CustomerDetials();
			obj.setId(each.getId());
			obj.setCustomerFName(each.getFName());
			if(each.getMName().equals("") || Objects.isNull(each.getMName()) ) {
				obj.setCustomerLName(each.getLName());
			}else {
				obj.setCustomerLName(each.getLName()+" "+ each.getLName());
			}
			obj.setCustomerLName(each.getLName());
			obj.setAddress(each.getLocation());
			obj.setPhoneno(each.getPhone());
			obj.setBengaliName(each.getBengaliName());
			data.add(obj);
		});

		return data;
	}

	@Override
	public List<TransactionDetails> getTransactionBYCustomer(Long customer) {
		Optional<UserDetail> customerDtl = userData.findById(customer);
		if (customerDtl.isPresent()) {
			List<TransactionDetails> datdto = new ArrayList<TransactionDetails>();
			List<TransactionDtl> allrelatedTransaction = transrepo.findAllTransactionOfUser(customerDtl.get());
			allrelatedTransaction.forEach(each -> {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					TransactionDetails data = new TransactionDetails();
					data.setTransactID(each.getTransactionId());
					data.setDesc(each.getTransDescription());
					data.setDipAmount(each.getAmountDip());
					data.setDipGold(each.getGoldDip());
					data.setDipSilver(each.getSilverDip());
					data.setTransactionDate(sdf.format(each.getTransDate()));
					data.setWidAmount(each.getAmountwith());
					data.setWidGold(each.getGoldWith());
					data.setWidSilver(each.getSilverWith());
					data.setTransactionType(each.getTransType());
					data.setTranccat(getTranssactionCatagory(each));// A/G/S
					datdto.add(data);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			return datdto;
		} else {
			return new ArrayList<TransactionDetails>();
		}
	}

	private String getTranssactionCatagory(TransactionDtl each) {
		StringBuilder catagory = new StringBuilder();
		if (each.getAmountDip() != null) {
			if (each.getAmountDip().compareTo(BigDecimal.ZERO) > 0) {
				catagory.append("A");
			}
		}
		if (each.getAmountwith() != null) {
			if (each.getAmountwith().compareTo(BigDecimal.ZERO) > 0) {
				catagory.append("A");
			}
		}
		if (each.getGoldDip() != null) {
			if (each.getGoldDip().compareTo(BigDecimal.ZERO) > 0) {
				catagory.append("G");
			}
		}
		if (each.getGoldWith() != null) {
			if (each.getGoldWith().compareTo(BigDecimal.ZERO) > 0) {
				catagory.append("G");
			}
		}
		if (each.getSilverDip() != null) {
			if (each.getSilverDip().compareTo(BigDecimal.ZERO) > 0) {
				catagory.append("S");
			}
		}
		if (each.getSilverWith() != null) {
			if (each.getSilverWith().compareTo(BigDecimal.ZERO) > 0) {
				catagory.append("S");
			}
		}

		return catagory.toString();
	}

	@Override
	public BalanceDetails getOverAllBalance(List<TransactionDetails> data) {
		BalanceDetails dataOverall = new BalanceDetails();
		BigDecimal amount = BigDecimal.ZERO;
		BigDecimal gold = BigDecimal.ZERO;
		BigDecimal silver = BigDecimal.ZERO;
		for (TransactionDetails dataTran : data) {
			amount = amount.add(dataTran.getDipAmount() != null ? dataTran.getDipAmount()
					: BigDecimal.ZERO
							.subtract(dataTran.getWidAmount() != null ? dataTran.getWidAmount() : BigDecimal.ZERO));
			gold = gold.add(dataTran.getDipGold() != null ? dataTran.getDipGold()
					: BigDecimal.ZERO
							.subtract(dataTran.getWidGold() != null ? dataTran.getWidGold() : BigDecimal.ZERO));
			silver = silver.add(dataTran.getDipSilver() != null ? dataTran.getDipSilver()
					: BigDecimal.ZERO
							.subtract(dataTran.getWidSilver() != null ? dataTran.getWidSilver() : BigDecimal.ZERO));
		}
		dataOverall.setAmountBal(amount);
		dataOverall.setGoldBal(gold);
		dataOverall.setSilverBal(silver);
		return dataOverall;
	}

	@Override
	public TransactionDetails getTransactionByID(Long tid) throws Exception {
		TransactionDetails data = new TransactionDetails();
		Optional<TransactionDtl> each = transrepo.findById(tid);
		if (each.isPresent()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				data.setTransactID(each.get().getTransactionId());
				data.setDesc(each.get().getTransDescription());
				data.setDipAmount(each.get().getAmountDip());
				data.setDipGold(each.get().getGoldDip());
				data.setDipSilver(each.get().getSilverDip());
				data.setTransactionDate(sdf.format(each.get().getTransDate()));
				data.setWidAmount(each.get().getAmountwith());
				data.setWidGold(each.get().getGoldWith());
				data.setWidSilver(each.get().getSilverWith());
				data.setTransactionType(each.get().getTransType());
				data.setCustomerName(each.get().getUserDetail().getFName().concat(" ")
						.concat(each.get().getUserDetail().getLName()));
				data.setCustomerId(each.get().getUserDetail().getId());
				return data;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new Exception("Transaction not found");
		}
		return null;
	}

	@Override
	public void updateTransaction(TransactionDetails transaction) {
		Optional<UserDetail> customer = userData.findById(transaction.getCustomerId());
		if (customer.isPresent()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				TransactionDtl data = new TransactionDtl();
				data.setUserDetail(customer.get());
				data.setAmountDip(transaction.getDipAmount());
				data.setAmountwith(transaction.getWidAmount());
				data.setGoldDip(transaction.getDipGold());
				data.setGoldWith(transaction.getWidGold());
				data.setSilverDip(transaction.getDipSilver());
				data.setSilverWith(transaction.getWidSilver());
				data.setTransDate(sdf.parse(transaction.getTransactionDate()));
				data.setTransactionId(transaction.getTransactID());
				data.setTransType(transaction.getTransactionType());
				data.setTransStat(0);
				data.setTransDescription(transaction.getDesc());
				transrepo.save(data);
			} catch (Exception e) {

			}

		}
	}

	@Override
	public CustomerDetials getCuastomer(Long customerid) {
		Optional<UserDetail> each = userData.findById(customerid);
		if (each.isPresent()) {
			CustomerDetials obj = new CustomerDetials();
			obj.setId(each.get().getId());
			obj.setCustomerFName(each.get().getFName());
			obj.setCustomerLName(each.get().getLName());
			obj.setAddress(each.get().getLocation());
			obj.setPhoneno(each.get().getPhone());
			obj.setBengaliName(each.get().getBengaliName());
			return obj;
		}
		return null;
	}

}
