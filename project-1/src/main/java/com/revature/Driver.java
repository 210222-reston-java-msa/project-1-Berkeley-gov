package com.revature;

import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.repositories.EmployeeDaoImplementation;
import com.revature.utility.JDBConnection;

import java.sql.Connection;

public class Driver {

	public static void main(String[] args) {

		Connection conn = JDBConnection.getConnection();
		Employee juan = new Employee("ramjam", "tiger101", "Juan", "Ramirez", "juan.r@revature.net", new Role(1, "Administrator"));

		EmployeeDaoImplementation employeeDao = new EmployeeDaoImplementation();
		employeeDao.insert(juan);

		System.out.println("End of the program");

	}
}
