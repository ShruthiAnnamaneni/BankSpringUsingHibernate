package com.cg.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cg.model.CustomerDetails;
import com.cg.model.TransactionDetails;
import com.cg.utility.Database;

@Repository
public class TransactionDaoImpl implements TransactionDao {
	Database database = new Database();
	
	public int deposit(int accountNo, int amt) {
		int amount = 0;
		Session session = database.getSession();
		Transaction transaction = session.beginTransaction();
		CustomerDetails cd = session.get(CustomerDetails.class, accountNo);
		amount = cd.getBalance()+amt;
		cd.setBalance(amount);
		session.update(cd);
		transaction.commit();
		return amount;
	}
	
	public int withdraw(int accountNo, int amt) {
		int amount = 0;
		Session session = database.getSession();
		Transaction transaction = session.beginTransaction();
		CustomerDetails cd = session.get(CustomerDetails.class, accountNo);
		if(cd.getBalance()>amt) {
			amount = cd.getBalance()-amt;
			cd.setBalance(amount);
			session.update(cd);
			transaction.commit();
		}
		return amount;
	}
	

	public int showBalance(int accountNo) {
		int amount = 0;
		Session session = database.getSession();
		CustomerDetails cd = session.get(CustomerDetails.class, accountNo);
		amount = cd.getBalance();
		return amount;
	}

	public TransactionDetails fundTransfer(int accountNo, TransactionDetails transaction) {
		int amount = 0;
		TransactionDetails td = null;
		Session session = database.getSession();
		Transaction trans = session.beginTransaction();
		CustomerDetails from = session.get(CustomerDetails.class, accountNo);
		CustomerDetails to = session.get(CustomerDetails.class, transaction.getToAcc());
		if(from.getBalance()>transaction.getAmt()) {
			amount = from.getBalance()-transaction.getAmt();
			from.setBalance(amount);
			to.setBalance(to.getBalance()+transaction.getAmt());
			session.update(from);
			session.update(to);
			trans.commit();
			session.close();
			td = new TransactionDetails();
			td.setFromAcc(accountNo);
			td.setToAcc(transaction.getToAcc());
			td.setAmt(transaction.getAmt());
			
		}
		return td;
	}

	public boolean insertTransaction(TransactionDetails transaction) {
		boolean isInserted = false;
		Session session = database.getSession2();
		Transaction trans = session.beginTransaction();
		if(transaction.getAmt() != 0) {
			session.persist(transaction);
			trans.commit();
			isInserted = true;
		}
		
		return isInserted;
	}

	
}
