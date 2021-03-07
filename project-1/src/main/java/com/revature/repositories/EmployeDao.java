package com.revature.repositories;

import com.revature.models.Employee;

import java.util.List;

/**
 * All Methods in the interface will return true or false based on
 * its attempt to query the database.
 */
public interface EmployeDao {
    public boolean insert(Employee employee);
    public boolean update(Employee employee);
    public boolean delete(Employee employee);

    public List<Employee> findAll();
}
