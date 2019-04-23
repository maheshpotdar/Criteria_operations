package com.mahesh.Client_SELECT;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class App_Criteria_All {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		Session session = factory.openSession();

		Criteria criteria = session.createCriteria(Employee.class);

		List<Employee> lstEmployees = criteria.list();

		for (Employee employee : lstEmployees) {
			System.out.println("ID: " + employee.getId());
			System.out.println("Name: " + employee.getEname());
			System.out.println("Address: " + employee.getAddress());
		}

	}

}
