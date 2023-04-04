package com.nandan.shop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nandan.shop.dto.CustomerDetials;
import com.nandan.shop.dto.TransactionDetails;
import com.nandan.shop.service.MainService;

@Controller
public class MainController {
	@Autowired
	public MainService sainService;
	
	@GetMapping("/")
	public String showHome(Model model) {
		return "index";
	}

	@GetMapping("/registration_form")
	public String showRegistrationForm(Model model) {
		CustomerDetials customer = new CustomerDetials();
		model.addAttribute("customer", customer);
		return "registration_form";
	}
	
	@GetMapping("/add_transaction")
	public String addTransactionForm(@RequestParam Long customer,@RequestParam String customerName,Model model) {
		List<TransactionDetails> allTransactionOfUser=sainService.getTransactionBYCustomer(customer);
		TransactionDetails transaction = new TransactionDetails();
		transaction.setCustomerId(customer);
		transaction.setCustomerName(customerName);
		model.addAttribute("transaction", transaction);
		model.addAttribute("exsistingAmout", allTransactionOfUser.stream().filter(x->x.getTranccat().contains("A")).collect(Collectors.toList()));
		model.addAttribute("exsistingGold", allTransactionOfUser.stream().filter(x->x.getTranccat().contains("G")).collect(Collectors.toList()));
		model.addAttribute("exsistingSilver",allTransactionOfUser.stream().filter(x->x.getTranccat().contains("S")).collect(Collectors.toList()));
		model.addAttribute("overall",sainService.getOverAllBalance(allTransactionOfUser));
		return "add_transaction";
	}
	
	@PostMapping("/saveTransaction")
	public String addTransactionForm(@ModelAttribute TransactionDetails transaction, Model model) {		
		sainService.addTransaction(transaction);
		//need to change
		TransactionDetails newTransaction= new TransactionDetails();
		newTransaction.setCustomerId(transaction.getCustomerId());
		newTransaction.setCustomerName(transaction.getCustomerName());
		model.addAttribute("transaction", newTransaction);
		List<TransactionDetails> allTransactionOfUser=sainService.getTransactionBYCustomer(transaction.getCustomerId());
		model.addAttribute("exsistingAmout", allTransactionOfUser.stream().filter(x->x.getTranccat().contains("A")).collect(Collectors.toList()));
		model.addAttribute("exsistingGold", allTransactionOfUser.stream().filter(x->x.getTranccat().contains("G")).collect(Collectors.toList()));
		model.addAttribute("exsistingSilver",allTransactionOfUser.stream().filter(x->x.getTranccat().contains("S")).collect(Collectors.toList()));
		model.addAttribute("overall",sainService.getOverAllBalance(allTransactionOfUser));
		return "add_transaction";
	}
	
	@PostMapping("/editTransaction")
	public String rditTransactionForm(@ModelAttribute TransactionDetails transaction, Model model) {		
		sainService.updateTransaction(transaction);
		//need to change
		TransactionDetails newTransaction= new TransactionDetails();
		newTransaction.setCustomerId(transaction.getCustomerId());
		newTransaction.setCustomerName(transaction.getCustomerName());
		model.addAttribute("transaction", newTransaction);
		List<TransactionDetails> allTransactionOfUser=sainService.getTransactionBYCustomer(transaction.getCustomerId());
		model.addAttribute("exsistingAmout", allTransactionOfUser.stream().filter(x->x.getTranccat().contains("A")).collect(Collectors.toList()));
		model.addAttribute("exsistingGold", allTransactionOfUser.stream().filter(x->x.getTranccat().contains("G")).collect(Collectors.toList()));
		model.addAttribute("exsistingSilver",allTransactionOfUser.stream().filter(x->x.getTranccat().contains("S")).collect(Collectors.toList()));
		model.addAttribute("overall",sainService.getOverAllBalance(allTransactionOfUser));
		return "add_transaction";
	}


	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute CustomerDetials customer, Model model) {		
		sainService.addCustomer(customer);
		model.addAttribute("customer", customer);
		return "redirect:/listCustomer";
	}
	
	@GetMapping("/listCustomer")
	public ModelAndView getAllCustomer() {
		ModelAndView mav = new ModelAndView("show_customer");
		mav.addObject("customers", sainService.getAllCustomer());
		return mav;
	}
	
	@GetMapping("/transEdit")
	public String editTransactionForm(@RequestParam Long tid,Model model) throws Exception {	

		TransactionDetails newTransaction= sainService.getTransactionByID(tid);
		model.addAttribute("transaction", newTransaction);
		return "show_transaction";
	}
	
	
	@GetMapping("/download")
	public String addTransactionForm(@RequestParam Long customerid,Model model) {		
		//need to change
		CustomerDetials customer=sainService.getCuastomer(customerid);
		List<TransactionDetails> allTransactionOfUser=sainService.getTransactionBYCustomer(customerid);
		model.addAttribute("transaction", allTransactionOfUser);
		model.addAttribute("customer", customer);
		return "show_report";
	}
	

}