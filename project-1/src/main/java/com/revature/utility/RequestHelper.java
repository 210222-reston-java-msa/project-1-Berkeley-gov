package com.revature.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.LoginTemplate;
import com.revature.models.Reimbursement;
import com.revature.services.EmployeeService;
import com.revature.services.ReimbursementService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class RequestHelper {

    private static Logger log = Logger.getLogger(RequestHelper.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // We want to turn whatever we recieve as the request into a string to process
        StringBuilder s;
        try (BufferedReader reader = request.getReader()) {
            s = new StringBuilder();

            // logic to transfer everything from our reader to our string builder
            String line = reader.readLine();
            while (line != null) {
                s.append(line);
                line = reader.readLine();
            }
        }

        String body = s.toString();
        log.info(body);


        // I'm going to build a model called LoginTemplate which holds a username and passwrod
        LoginTemplate loginAttempt = objectMapper.readValue(body, LoginTemplate.class); // from JSON --> Java Object


        String username = loginAttempt.getUsername();
        String password = loginAttempt.getPassword();

        log.info("User attempted to login with username: " + username);
        Employee e = EmployeeService.confirmLogin(username, password);

        if (e != null) {
            // get the current session OR create one if it doesn't exist
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Attaching the print writer to our response
            PrintWriter pw = response.getWriter();
            response.setContentType("application/json");

            // this is converting our Java Object (with property firstName!)
            // to JSON format....that means we can grab the firstName property
            // after we parse it. (We parse it in JavaScript)
            pw.println(objectMapper.writeValueAsString(e));


            log.info(username + " has successfully logged in");
        } else {
            response.setStatus(204); // this means that we still have a connection, but no user is found
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
        List<Employee> employeeList = EmployeeService.findAll();

        log.info(employeeList);

        response.setContentType("application/json");

        // 3. Turn the list of Java Objs into a JSON string
        String json = objectMapper.writeValueAsString(employeeList.toString());

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

    public static void processReimbursementRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();

        String line = reader.readLine();

        while (line != null) {
            stringBuilder.append(line);
            line = reader.readLine();
        }

        String requestBodyContent = stringBuilder.toString();
        log.info(requestBodyContent);

        log.info("Reimbursement received from client.");

        Reimbursement reimbursementRequest = objectMapper.readValue(requestBodyContent, Reimbursement.class);

        ReimbursementService.insert(reimbursementRequest);

        response.setStatus(201);

        log.info("Reimbursement request made: " + reimbursementRequest.toString() + "");
    }

}
