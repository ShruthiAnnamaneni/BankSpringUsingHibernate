package com.cg.dao;

import java.util.ArrayList;

import com.cg.model.CustomerDetails;
import com.cg.model.TransactionDetails;

public interface TransactionDao {
	int deposit(int accountNo, int amt);
	int withdraw(int accountNo, int amt);
	int showBalance(int accountNo);
	TransactionDetails fundTransfer(int accountNo, TransactionDetails transaction);
	boolean insertTransaction(TransactionDetails transaction);
}
