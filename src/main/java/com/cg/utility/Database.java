package com.cg.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cg.model.CustomerDetails;
import com.cg.model.TransactionDetails;

public class Database {
	public Session getSession() {
		Configuration configuration=new Configuration().configure().addAnnotatedClass(CustomerDetails.class);
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session= sessionFactory.openSession();
		return session;
	}
	
	public Session getSession2() {
		Configuration configuration=new Configuration().configure().addAnnotatedClass(TransactionDetails.class);
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session= sessionFactory.openSession();
		return session;
	}
}
