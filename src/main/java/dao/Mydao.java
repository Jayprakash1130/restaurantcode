package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.Customer;

public class Mydao {
 EntityManagerFactory e=Persistence.createEntityManagerFactory("dev");
 EntityManager m=e.createEntityManager();
 EntityTransaction t=m.getTransaction();
 
 
public void save(Customer cus) {
	t.begin();
	m.persist(cus);
	t.commit();
	
}
public Customer fetchbyEmail(String mail) {
  Query query = m.createQuery("select x from Customer x where mail=?1").setParameter(1, mail);
	List<Customer> list=query.getResultList();
	if(list.isEmpty())
	return null;
	else
		return list.get(0);
	
	
}

public Customer fetchbyMobile(long mobile) {
	Query query = m.createQuery("select x from Customer x where mobile=?1").setParameter(1, mobile);
	List<Customer> list=query.getResultList();
	if(list.isEmpty())
	return null;
	else
		return list.get(0);
	
}
}
