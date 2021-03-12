package com.revature.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.LoginTemplate;
import com.revature.services.EmployeeService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RequestHelper {

    private static Logger log = Logger.getLogger(RequestHelper.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();

        String line = reader.readLine();

        while (line != null) {
            stringBuilder.append(line);
            line = reader.readLine();
        }

        String requestBodyContent = stringBuilder.toString();
        log.info(requestBodyContent);

        LoginTemplate loginAttempt = objectMapper.readValue(requestBodyContent, LoginTemplate.class);

        String username = loginAttempt.getUsername();
        String password = loginAttempt.getUsername();
        log.info("Employee login attempt with username: " + loginAttempt.getUsername());

        Employee employeeLoggedIn = EmployeeService.confirmLogin(username, password);

        if (employeeLoggedIn != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", employeeLoggedIn.getUsername());

            PrintWriter printWriter = response.getWriter();
            response.setContentType("application/json");

            printWriter.println(objectMapper.writeValueAsString(employeeLoggedIn));
            log.info("Employee: " + employeeLoggedIn.getUsername() + "has successfully logged in.");
        } else {
            log.warn("WARN: Employee failed to be found on the ERS database based on credentials provided.");
            response.setStatus(204);
        }
    }

    public static void processLogout (HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession currentSession = request.getSession(false);

        log.info("Employee has logged out of the ERS.");

        if(currentSession != null) {
            log.info("Employee: " + (String) currentSession.getAttribute("username") + " has logged out of the ERS.");
            currentSession.invalidate();
        }

        response.setStatus(200);
    }

    // This method's purpose is to return all Employees from the DB in JSON form
    public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. Set the content type to app/json because we will be sending json data back to the client, stuck alongside the response
        log.info(EmployeeService.findAll());
        response.setContentType("application/json");

        // 2. Get a list of all Employees in the DB
        List<Employee> allEmployees = EmployeeService.findAll();

        // 3. Turn the list of Java Objs into a JSON string
        String json = objectMapper.writeValueAsString(allEmployees);

        // 4. Use getWriter() from the response object to return the json string
        PrintWriter printWriter = response.getWriter();
        printWriter.println(json);


    }

    public static void processError(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            request.getRequestDispatcher("error.html").forward(request, response);
            //  we do NOT create a new request and we also maintain the url.
        } catch (ServletException | IOException e) {
            log.warn("WARN: Error HTML page failed to be redirected. ");
            e.printStackTrace();
        }

    }
}
