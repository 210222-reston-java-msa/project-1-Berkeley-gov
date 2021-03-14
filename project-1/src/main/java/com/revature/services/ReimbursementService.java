package com.revature.services;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeeDaoImplementation;
import com.revature.repositories.ReimbursementDao;
import com.revature.repositories.ReimbursementDaoImplementation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {

    public static ReimbursementDao reimbursementDao = new ReimbursementDaoImplementation();
    public static EmployeeDao employeeDao = new EmployeeDaoImplementation();

    public final static Logger log = Logger.getLogger(ReimbursementDao.class);

    public static List<Reimbursement> reimbursementList = reimbursementDao.findAll();
    public static List<Employee> employeeList = employeeDao.findAll();


    public static void insert(Reimbursement reimbursement) {
        reimbursementDao.insert(reimbursement);
    }

    /**
     *
     * @param username
     * @return
     */
    public static List<Reimbursement> findAllByUsername(String username) {

        for(Employee employee: employeeList) {

            if(employee.getUsername().equals(username)) {
                log.info("Successful: List of all reimbursement request made by employee " + username);
                return reimbursementDao.findByAuthorId(employee.getId());
            }
        }

        log.warn("WARN: List of all reimbursement request made by employee " + username + " failed to be retrieved.");
        return null;
    }

    /**
     *
     * @return
     */
    public static List<Reimbursement> returnPendingReimbursements() {

        List<Reimbursement> pendingReimbursementList = new ArrayList<Reimbursement>();

        for(int i = 0; i < reimbursementList.size(); i++) {

            if(reimbursementList.get(i).getReimbursementId() == employeeList.get(i).getId()) {

                if(reimbursementList.get(i).getStatusId() == 1){
                    pendingReimbursementList.add(reimbursementList.get(i));
                }

            }
        }

        return pendingReimbursementList;
    }

}
