package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.model.CustomerDetails;
import com.cg.model.TransactionDetails;
import com.cg.service.BankService;
import com.cg.service.TransactionService;
import com.cg.utility.Database;
@RestController
public class HomeController {
	Database d=new Database();
	int accountNo;
	@Autowired
	BankService bank;
	@Autowired
	TransactionService transactionService;
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String start() {
		
		return "Hello";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity register(@RequestBody CustomerDetails customer) {
		bank.register(customer);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public void login(@RequestBody CustomerDetails c) {
		accountNo = bank.login(c);
		
	}
	
	@RequestMapping(value="/deposit",method=RequestMethod.PUT)
	public int deposit(@RequestBody int amt) {
		int amount = 0;
		if(accountNo != 0) {
			amount = transactionService.deposit(accountNo,amt);
		}
		return amount;
	}
	
	@RequestMapping(value="/withdraw",method=RequestMethod.PUT)
	public int withdraw(@RequestBody int amt) {
		int amount = 0;
		if(accountNo != 0) {
			amount = transactionService.withdraw(accountNo,amt);
		}
		
		return amount;
		
	}
	
	@RequestMapping(value="/showBalance",method=RequestMethod.GET)
	public int showBalance() {
		int amount = 0;
		if(accountNo != 0) {
			amount = transactionService.showBalance(accountNo);
		}
		return amount;
		
	}
	
	@RequestMapping(value="/fundTransfer",method=RequestMethod.PUT)
	public int fundTransfer(@RequestBody TransactionDetails transaction) {
		int amount = 0;
		if(accountNo != 0) {
			transaction = transactionService.fundTransfer(accountNo,transaction);
			boolean isInserted = transactionService.insertTransaction(transaction);
		}
		
		return amount;
	}
	
	
}
