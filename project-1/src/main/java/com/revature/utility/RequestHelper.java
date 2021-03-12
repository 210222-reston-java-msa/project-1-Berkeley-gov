package com.revature.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.LoginTemplate;
import com.revature.services.EmployeeService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RequestHelper {

    private static Logger log = Logger.getLogger(RequestHelper.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();

        String line = reader.readLine();

        while(line != null) {
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

        if(employeeLoggedIn != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            PrintWriter printWriter = response.getWriter();
            response.setContentType("application/json");

            printWriter.println(objectMapper.writeValueAsString(employeeLoggedIn));
            log.info("Employee: " + employeeLoggedIn.getUsername() + "has successfully logged in.");
        } else {
            log.warn("WARN: Employee failed to be found on the ERS database based on credentials provided.");
            response.setStatus(204);
        }

        //TODO Finish processLogout and other functions

    }
}
