package com.revature.website;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    private static final Logger log = Logger.getLogger(FrontController.class);
    // Used to track sessions across servlets.
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String URI = req.getRequestURI().replace("/Employee_Reimbursement_System/", "");

        // Switch is used to control the flow of execution and redirect the client request to the appropriate resource
        // Using the FrontController design pattern (MVC)
        switch (URI) {
            case "login":
                RequestHelper.processLogin(request, response);
                break;
            case "logout":
                RequestHelper.processLogout(request, response);
                break;
            case "employees":
                RequestHelper.processEmployees(request, response);
                break;
            case "error":
                RequestHelper.processError(request, response);
                break;
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
