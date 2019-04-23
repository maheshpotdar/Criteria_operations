package com.mahesh.Client_SELECT;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.AggregateProjection;
import org.hibernate.criterion.CountProjection;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.PropertyProjection;
import org.hibernate.criterion.Restrictions;

public class App_criteria_Projections {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		Session session = factory.openSession();

		// select ename from employee where id=3;
		// fakt ename manaje 1 column pahije tar projection use kara.
		// Restriction add kara criteria madhe
		// Projection set kara criteria madhe.

		Criteria criteria = session.createCriteria(Employee.class);
		Criterion criterion = Restrictions.eq("id", 3);
		criteria.add(criterion);

		PropertyProjection pt = Projections.property("ename");
		criteria.setProjection(pt);

		String name1 = (String) criteria.uniqueResult();
		System.out.println("Projection Data: ");
		System.out.println("Ename: " + name1);

		// select ename,address from Employee where id=4.
		// -------------------------ProjectionList-------------------------------------------------------------------
		Criteria ccc = session.createCriteria(Employee.class);
		Criterion cr = Restrictions.eq("id", 3);
		ccc.add(cr);
		ProjectionList ppp = Projections.projectionList();

		PropertyProjection p1 = Projections.property("ename");
		PropertyProjection p2 = Projections.property("address");
		PropertyProjection p3 = Projections.property("esal");

		// select ename,address,esal from employee where id=3;
		// select manaje criteria.
		// ename,address,esal manaje (Particular column) Projection.
		// where id=3 (condition) Restriction.
		ppp.add(p1);
		ppp.add(p2);
		ppp.add(p3);

		ccc.setProjection(ppp);

		Object[] o1 = (Object[]) ccc.uniqueResult();

		System.out.println("Object Name: " + o1[0]);
		System.out.println("Object Address: " + o1[1]);
		System.out.println("Object Salary: " + o1[2]);

		// criteria manaje select * from employee.
		// -------------------------Like-------------------------------------------------------------------
		Criteria ccp = session.createCriteria(Employee.class);
		// select avg(esal) from employee;
		AggregateProjection pp11 = Projections.avg("esal");
		ccp.setProjection(pp11);

		Criterion ctt = Restrictions.like("address", "Japan");
		// select esal from employee where eaddress like '%Japan%';
		ccp.add(ctt);

		// select avg(esal) from employee where name like japan.

		// 1 cha result yenar ahe. (uniqueResult).

		Double sal = (Double) ccp.uniqueResult();
		System.out.println("Salary==<>: " + sal);
		// -------------------------Count-------------------------------------------------------------------
		Criteria ccq = session.createCriteria(Employee.class);
//		Criterion ccr = Restrictions.eq("id", 2);
//		ccq.add(ccr);
		// select count(id) from employee
		CountProjection pp1 = Projections.count("id");
		ccq.setProjection(pp1);

		Long ct = (Long) ccq.uniqueResult();

		System.out.println("Count: " + ct);
		// -------------------------SUM-------------------------------------------------------------------
		Criteria ccq1 = session.createCriteria(Employee.class);
//		Criterion ccr = Restrictions.eq("id", 2);
//		ccq.add(ccr);
		// select count(id) from employee
		AggregateProjection pp111 = Projections.sum("id");
		ccq1.setProjection(pp111);

		Long ct1 = (Long) ccq1.uniqueResult();

		System.out.println("Sum: " + ct1);
		// -------------------------Project_List-------------------------------------------------------------------
		// select ename,address from employee
		Criteria ccq111 = session.createCriteria(Employee.class);
		// select count(id) from employee

		ProjectionList pp122 = Projections.projectionList();
		// konate column pahije te projection madhe ghala.
		PropertyProjection p189 = Projections.property("ename");
		PropertyProjection p190 = Projections.property("address");

		pp122.add(p189);
		pp122.add(p190);

		// select ename,address from employee
		ccq111.setProjection(pp122);

		List<Object[]> lst = ccq111.list();
		System.out.println("Employee Projections Data\n");
		for (Object[] r : lst) {

			System.out.println("Last Projetion Name: " + r[0]);
			System.out.println("Last Projetion Address: " + r[1]);
		}

	}

}
