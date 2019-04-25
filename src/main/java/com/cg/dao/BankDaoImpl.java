package com.cg.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cg.model.CustomerDetails;
import com.cg.utility.Database;

@Repository
public class BankDaoImpl implements BankDao {
	Database database = new Database();
	public CustomerDetails register(CustomerDetails cd) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("hibernate.cfg.xml");
		Session session = database.getSession();
    	Transaction transaction = session.beginTransaction();
    	session.persist(cd);
    	transaction.commit();
    	session.close();
		return cd;
	}
	public int login(CustomerDetails c) {
		// TODO Auto-generated method stub
		int accountNo = 0;
		//ApplicationContext context=new ClassPathXmlApplicationContext("hibernate.cfg.xml");		
		Session session=database.getSession();
		CustomerDetails cd = session.get(CustomerDetails.class, c.getAccountNo());
		if(cd.getPassword().equals(c.getPassword())) {
			accountNo = c.getAccountNo(); 
		}
		return accountNo;
	}

	

}
