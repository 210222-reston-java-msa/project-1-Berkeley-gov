package com.revature;

import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.repositories.EmployeeDaoImplementation;
import com.revature.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * EmployeeService class is tested in this class for proper functionality.
 * Also checks that the Dao layer is working appropriately.
 */
public class ServiceTest {

    private EmployeeService eserv;
    private EmployeeDaoImplementation employeeDaoImplementation;

    @Before
    public void setup() {
        eserv = new EmployeeService();

        employeeDaoImplementation = mock(EmployeeDaoImplementation.class);

        eserv.employeeDao = employeeDaoImplementation;
    }

    @Test
    public void happyPathScenario() {

        Employee sampleEmployee = new Employee(1, "a", "a", "b", "c", "d", new Role(1, "c"));

        List<Employee> list  = new ArrayList<Employee>();
        list.add(sampleEmployee);

        // Programming a simulated, fake employee dao to return this as its fake data
        when(employeeDaoImplementation.findAll()).thenReturn(list);

        Employee foundByUsername = eserv.findByUsername(sampleEmployee.getUsername());

        assertEquals(sampleEmployee, foundByUsername);
    }

    @Test
    public void employeeIsNotPresentInDB() {

        List<Employee> emptyList = new ArrayList<Employee>();

        when(employeeDaoImplementation.findAll()).thenReturn(emptyList);

        // Passing through data that does not exist on the ERS database
        Employee employeeFoundByUsername = eserv.findByUsername("tester");

        assertEquals(null, employeeFoundByUsername);
    }


}
