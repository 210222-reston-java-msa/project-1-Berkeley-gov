package com.revature.repositories;

import com.revature.models.Employee;
import com.revature.utility.JDBConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * EmployeeDaoImplementation class is the data access layer for Employee objects
 * that need to be persisted to the database. Uses the JDBC to establish a connection to the DB,
 * and SQL postgres framework to query the postgres database engine.
 */
public class EmployeeDaoImplementation implements EmployeDao {

    Logger log = Logger.getLogger(EmployeeDaoImplementation.class);

    @Override
    public boolean insert(Employee employee) {
        PreparedStatement sqlStatement;

        try {

            Connection connected = JDBConnection.getConnection();

            // columns holds the column names of the Employee table as string values; to be used for SQL query
            String sqlQuery = "INSERT INTO \"Employee\" (username, pass_word, first_name, last_name, email, user_role_id) VALUES (?, ?, ?, ?, ?, ?)";

            sqlStatement = connected.prepareStatement(sqlQuery);

            sqlStatement.setString(1, employee.getUsername());
            sqlStatement.setString(2, employee.getPassword());
            sqlStatement.setString(3, employee.getFirstName());
            sqlStatement.setString(4, employee.getLastName());
            sqlStatement.setString(5, employee.getEmail());
            sqlStatement.setInt(6, employee.getRole().getRoleId());

            sqlStatement.execute();

        } catch (SQLException e) {
            log.error("WARN: Failed to execute insert employee query to the ERS database.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Employee employee) {
        return false;
    }

    @Override
    public boolean delete(Employee employee) {
        return false;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }
    //TODO finsh building the Employee Dao layer
}
