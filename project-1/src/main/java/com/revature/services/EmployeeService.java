package com.revature.services;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeeDaoImplementation;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * EmployeeService class uses CRUD functionality from the DAO layer to provide login functionality
 * For example searching a employee by username or validating an employee's login credentials.
 */
public class EmployeeService {

    // EmployeeDao is used throughout the EmployeeService class to provide CRUD operations to the employee service layer
    public static EmployeeDao employeeDao = new EmployeeDaoImplementation();
    public final static Logger log = Logger.getLogger(Employee.class);

    /**
     * When invoked, the method will add an employee record to the ERS database.
     * @param employee
     * @return boolean
     */
    public static boolean insert(Employee employee) {
        return employeeDao.insert(employee);
    }

    /**
     * When invoked, the update() method will update an employee's record in the ERS database.
     * @param employee
     * @return boolean
     */
    public static boolean update(Employee employee) {
        return employeeDao.update(employee);
    }

    /**
     * When invoked, the findAll() method will retrieve a List of all employees from the ERS database.
     * @return List<Employee>
     */
    public static List<Employee> findAll() {
        return employeeDao.findAll();
    }

    /**
     * When invoked, findByUsername() will find an employee from the ERS database by using their account
     * username as a credential to validate access to the employee with the given username.
     * @param username
     * @return Employee
     */
    public static Employee findByUsername(String username) {
        // SELECT * FROM Employee SQL Query executed; Results will be stored in a list of employee objects
        List<Employee> employeeList = employeeDao.findAll();

        for (Employee employee : employeeList) {
            if(employee.getUsername().equals(username)) {
                log.info("Successful: Employee username found in ERS database. ");
                return employee;
            }
        }

        log.warn("WARN: Failed to find the employee username on ERS database.");
        return null;
    }

    /**
     * When invoked, the confirmLogin() method will validate the clients login credentials against the ERS database.
     * @param username
     * @param password
     * @return Employee
     */
    public static Employee confirmLogin(String username, String password) {
        // Employee is first retrieved from the ERS database by querying for it using employee's username
        Employee employee = findByUsername(username);

        // Checks if the findByUsername() method was invoked properly and that the employee object is not null
        if (employee == null) {
            log.warn("WARN: Failed to retrieve employee by username. Employee was set to null");
            return null;
        } else if (employee.getPassword().equals(password)) {
            return employee;
        }

        return null;
    }
}
