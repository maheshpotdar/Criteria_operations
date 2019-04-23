package com.mahesh.Client_SELECT;

import java.util.List;

import javax.persistence.Column;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class App_criteria_Restrictions {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		Session session = factory.openSession();

		// ***********************Eq(Equal_To)*********************************************************

		// select * from employee where id=3
		// condition manaje restrictions.
		Criteria criteria = session.createCriteria(Employee.class);

		Criterion criterion = Restrictions.eq("id", 3);
		criteria.add(criterion);
		// restriction madhe eq=> equal to,gt=> Greater than,lt=> Less
		// than,between,distinct,like.

		// id=3 manaje eakach result yenar.
		// so uniqueResult.

		Employee employee = (Employee) criteria.uniqueResult();

		System.out.println("**********Employee EQ-----UniqueResult*******");

		System.out.println("ID: " + employee.getId());
		System.out.println("Name: " + employee.getEname());
		System.out.println("Address: " + employee.getAddress());

		// ***********************gt(Greater_than)*********************************************************
		// select * from employee where salary>1000;

		Criteria ct = session.createCriteria(Employee.class);
		Criterion cr = Restrictions.gt("esal", 24999.0f);
		ct.add(cr);
		System.out.println("\n**********Employee List----gt*******");

		List<Employee> lstEmp = ct.list();
		for (Employee employee2 : lstEmp) {
			System.out.println("ID: " + employee2.getId());
			System.out.println("Name: " + employee2.getEname());
			System.out.println("Address: " + employee2.getAddress());
		}

		System.out.println("**********Employee List----lt*******");

		Criteria c3 = session.createCriteria(Employee.class);
		Criterion cr3 = Restrictions.lt("esal", 19000.0f);
		c3.add(cr3);

		List<Employee> lst = c3.list();
		for (Employee employee2 : lst) {
			System.out.println("ID: " + employee2.getId());
			System.out.println("Name: " + employee2.getEname());
			System.out.println("Address: " + employee2.getAddress());
			System.out.println("Salary: " + employee2.getEsal());
		}

		System.out.println("**********Employee List----between*******");

		Criteria c4 = session.createCriteria(Employee.class);
		Criterion cr4 = Restrictions.between("esal", 5000.0f, 25000.0f);
		c4.add(cr4);

		List<Employee> employees = c4.list();

		for (Employee e2 : employees) {
			System.out.println("ID: " + e2.getId());
			System.out.println("Name: " + e2.getEname());
			System.out.println("Address: " + e2.getAddress());
			System.out.println("Salary: " + e2.getEsal());
		}

		System.out.println("**********Employee List----Like*******");

		// VVIMP NOTES
		// @Column(name = "eaddress", length = 25)
		// private String address;
		// take column name ( address ) not eaddress.

		Criteria c55 = session.createCriteria(Employee.class);
		Criterion cr5 = Restrictions.like("address", "Japan");
		c55.add(cr5);

		List<Employee> lstNames = c55.list();
		for (Employee s : lstNames) {
			System.out.println("List of Names: " + s.getAddress());
		}
		
	}

}
