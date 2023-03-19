package com.gl.HibernateAssignment;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import config.HibernateConfig;
import entity.Course;
import entity.Employee;
import entity.Project;
import entity.Student;


/**
 * Hello world!
 *
 */
public class App 
{private static SessionFactory factory = HibernateConfig.getSessionFactory();
    public static void main( String[] args )
    {
       
		System.out.println("config loaded");
		Session session= factory.openSession();
		
		//Create Employee Object
		Employee e1= new Employee();
		e1.setName("Raghu Kumar");
		e1.setAge(22);
		
		Employee e2= new Employee();
		e2.setName("Shyam Mittal");
		e2.setAge(23);
		
		Employee e4= new Employee();
		e4.setName("Anurag Dubey");
		e4.setAge(23);
		
		//Create Project Object
		Project p1= new Project();
		p1.setName("Banking Project");
		
		Project p2= new Project();
		p2.setName("Insurance Project");
		
		Project p3= new Project();
		p3.setName("Salesforce Project");
		
		
		//Create List of Employees and Projects
		List<Employee> emps1= new ArrayList<Employee>();
		List<Employee> emps2= new ArrayList<Employee>();
		List<Project> ps1= new ArrayList<Project>();
		List<Project> ps2= new ArrayList<Project>();
		
		emps1.add(e1);
		emps1.add(e2);
		emps2.add(e2);
		
		ps1.add(p1);
		ps2.add(p1);
		ps2.add(p2);
		
		//Setting the Employees of project and projects of Employee
		e1.setProjects(ps1);
		e2.setProjects(ps2);
		
		p1.setEmployees(emps1);
		p2.setEmployees(emps2);
		
		
		
		//Insert data into the Employee along with the details of the project assigned to that employee 
		Transaction tx= session.beginTransaction();
//		session.persist(e1);
//		session.persist(e2);
//		session.persist(p1);
//		session.persist(p2);
		
//		session.persist(e4);
//		session.persist(p3);
		

		
		
		//Fetching the Employee names for a given Project Id 
		Project p4= session.get(Project.class, 1);
		System.out.println(p4.toString());
		System.out.println("Employees involved in the above project");
		List<Employee> es1= p4.getEmployees();
		for(Employee employee: es1){
			System.out.println(employee.getName());
		}
		//Fetching the Project names for a given Employee id
		System.out.println("\n*******************************************************\n");
		Employee e3= session.get(Employee.class, 2);
		System.out.println(e3.toString());
		System.out.println("Employee is involved in the below projects");
		List<Project> ps3= e3.getProjects();
		for(Project project:ps3){
			System.out.println(project.getName());
		}

		
		
		//Removing an Employee record from the database without affecting Project.
		Employee e5=session.get(Employee.class, 3);
		session.remove(e5);
		
		
		tx.commit();
		session.close();
		
		
		
    }
   
}
