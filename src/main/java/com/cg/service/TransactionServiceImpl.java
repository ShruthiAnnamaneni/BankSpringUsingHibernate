package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.TransactionDao;
import com.cg.model.TransactionDetails;

@Transactional
@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionDao transactionDao;

	public int deposit(int accountNo, int amt) {
		return transactionDao.deposit(accountNo, amt);
	}

	public int withdraw(int accountNo, int amt) {
		return transactionDao.withdraw(accountNo, amt);
	}

	public int showBalance(int accountNo) {
		return transactionDao.showBalance(accountNo);
	}

	public TransactionDetails fundTransfer(int accountNo, TransactionDetails transaction) {
		return transactionDao.fundTransfer(accountNo, transaction);
	}

	public boolean insertTransaction(TransactionDetails transaction) {
		return transactionDao.insertTransaction(transaction);
	}
	
}
