package com.revature.repositories;

import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.utility.JDBConnection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * EmployeeDaoImplementation class is the data access layer for Employee objects
 * that need to be persisted to the database. Uses the JDBC to establish a connection to the DB,
 * and SQL postgres framework to query the postgres database engine.
 */
public class EmployeeDaoImplementation implements EmployeeDao {

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

            log.info("Successful: The Employee was successfully added to the ERS database.");
            connected.close();
            sqlStatement.close();

        } catch (SQLException e) {
            log.error("WARN: Failed to execute insert employee query to the ERS database.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * update(): When invoked, update will persist all updated employee information to the
     * database. Updated information allowed to an employee record are username, password, first name,
     * last name, and email.
     * @param employee
     * @return boolean
     */
    @Override
    public boolean update(Employee employee) {
        PreparedStatement sqlStatement;

        try {

            Connection connection = JDBConnection.getConnection();

            String sqlQuery = "UPDATE \"Employee\" SET username = ?, pass_word = ?, first_name = ?, last_name = ?, email = ? WHERE employee_id = " + employee.getId() + "";

            sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, employee.getUsername());
            sqlStatement.setString(2, employee.getPassword());
            sqlStatement.setString(3, employee.getFirstName());
            sqlStatement.setString(4, employee.getLastName());
            sqlStatement.setString(5, employee.getEmail());

            log.info("Successful: Employee information was successfully update to the ERS database.");
            sqlStatement.executeUpdate();

        } catch (SQLException e) {
            log.warn("WARN: Failed to query the employee updated information to the the ERS database");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Employee employee) {
        PreparedStatement sqlStatement;

        try {

            Connection connection = JDBConnection.getConnection();

            String sqlQuery = "DELETE FROM \"Employee\" WHERE employee_id = " + employee.getId() + "";
            sqlStatement = connection.prepareStatement(sqlQuery);
            sqlStatement.executeQuery();

            log.info("Successful: Employee: " + employee.getFirstName() + "was successfully deleted from the ERS database");
            connection.close();
            sqlStatement.close();

        } catch (SQLException e) {
            log.warn("WARN: Failed to delete the employee from the ERS database.");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> allEmployees = new ArrayList<Employee>();

        try {

            String sqlQuery = "SELECT * FROM \"Employee\" INNER JOIN Role ON Employee.employee_id = Role.role_id";

            Connection connection = JDBConnection.getConnection();
            Statement sqlStatement = connection.createStatement();
            ResultSet sqlResults = sqlStatement.executeQuery(sqlQuery);

            while (sqlResults.next()) {
                int employeeId = sqlResults.getInt("employee_id");
                String username = sqlResults.getString("username");
                String password = sqlResults.getString("pass_word");
                String firstName = sqlResults.getString("first_name");
                String lastName = sqlResults.getString("last_name");
                String email = sqlResults.getString("email");
                int roleId = sqlResults.getInt("role_id");
                String employeeRole = sqlResults.getString("user_role");

                Role role = new Role(roleId, employeeRole);
                Employee employee = new Employee(employeeId, username, password, firstName, lastName, email, role);
                allEmployees.add(employee);
            }

            log.info("Successful: All Employee records were successfully retrieved from the ERS database");
            connection.close();
            sqlStatement.close();
            sqlResults.close();
        }
        catch (SQLException e) {
            log.warn("WARN: Failed to retrieve all the employees from the ERS database.");
            e.printStackTrace();
            return null;

        }
        return allEmployees;
    }
}
