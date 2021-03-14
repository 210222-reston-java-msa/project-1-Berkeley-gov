package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.utility.JDBConnection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImplementation implements ReimbursementDao {

    Logger log = Logger.getLogger(ReimbursementDaoImplementation.class);

    @Override
    public boolean insert(Reimbursement reimbursement) {

        PreparedStatement sqlStatement;

        try {

            Connection connected = JDBConnection.getConnection();
            String sqlQuery = "INSERT INTO \"Reimbursement\" (reimbursement_id, reimbursement_amount, reimbursement_submitted, reimbursement_resolved, reimbursement_description, reimbursement_author, reimbursement_resolver, reimbursement_status_id, reimbursement_type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            sqlStatement = connected.prepareStatement(sqlQuery);

            sqlStatement.setInt(1, reimbursement.getReimbursementId());
            sqlStatement.setDouble(2, reimbursement.getReimbursementAmount());
            sqlStatement.setTimestamp(3, reimbursement.getTimeOfSubmission());
            sqlStatement.setTimestamp(4, reimbursement.getTimeResolved());
            sqlStatement.setString(5, reimbursement.getDescription());
            sqlStatement.setInt(6, reimbursement.getAuthor());
            sqlStatement.setInt(7, reimbursement.getResolver());
            sqlStatement.setInt(8, reimbursement.getStatusId());
            sqlStatement.setInt(9, reimbursement.getTypeId());

            sqlStatement.execute();

            log.info("Successful: The Employee's reimbursement request was successfully added to the ERS database.");
            connected.close();
            sqlStatement.close();

            return true;

        } catch(SQLException e) {
            log.warn("WARN: The Employee's reimbursement request failed to persist to the ERS database.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reimbursement reimbursement) {

        PreparedStatement sqlStatement;
         try {

             Connection connected = JDBConnection.getConnection();
             String sqlQuery = "UPDATE \"Reimbursement\" SET reimbursement_id = ?, reimbursement_amount = ?, reimbursement_submitted = ?, reimbursement_resolved = ?, reimbursement_description = ?, reimbursement_author = ?, reimbursement_resolver = ?, reimbursement_status_id = ?, reimbursement_type_id = ?";

             sqlStatement = connected.prepareStatement(sqlQuery);

             sqlStatement.setInt(1, reimbursement.getReimbursementId());
             sqlStatement.setDouble(2, reimbursement.getReimbursementAmount());
             sqlStatement.setTimestamp(3, reimbursement.getTimeOfSubmission());
             sqlStatement.setTimestamp(4, reimbursement.getTimeResolved());
             sqlStatement.setString(5, reimbursement.getDescription());
             sqlStatement.setInt(6, reimbursement.getAuthor());
             sqlStatement.setInt(7, reimbursement.getResolver());
             sqlStatement.setInt(8, reimbursement.getStatusId());
             sqlStatement.setInt(9, reimbursement.getTypeId());

             log.info("Successful: Reimbursement information was successfully updated to the ERS database.");
             sqlStatement.executeUpdate();

             return true;

         } catch(SQLException e) {
             log.warn("WARN: Reimbursement information was failed to persist to the ERS database.");
             e.printStackTrace();
             return false;
         }
    }

    @Override
    public boolean delete(Reimbursement reimbursement) {

        PreparedStatement sqlStatement;

        try {

            Connection connection = JDBConnection.getConnection();

            String sqlQuery = "DELETE FROM \"Reimbursement\" WHERE  reimburesement_id = ?";

            sqlStatement = connection.prepareStatement(sqlQuery);
            sqlStatement.setInt(1, reimbursement.getReimbursementId());
            sqlStatement.executeQuery();

            log.info("Successful: Reimbursement was successfully deleted from the ERS database");
            connection.close();
            sqlStatement.close();

            return true;

        } catch (SQLException e) {
            log.warn("WARN: Failed to delete the reimbursement request from the ERS database.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Reimbursement> findByAuthorId(int authorId) {

        List<Reimbursement> employeeReimbursementList = new ArrayList<Reimbursement>();

        Statement sqlStatement;
        ResultSet sqlResults;

        try {

            Connection connected = JDBConnection.getConnection();
            String sqlQuery = "SELECT * FROM \"Reimbursements\" WHERE reimbursement_author = " + authorId + "";

            sqlStatement =  connected.createStatement();
            sqlResults = sqlStatement.executeQuery(sqlQuery);

            while (sqlResults.next()) {
                int reimbursementId = sqlResults.getInt("reimbursement_id");
                double amount = sqlResults.getDouble("reimbursement_amount");
                Timestamp submissionDate = sqlResults.getTimestamp("reimbursement_submitted");
                Timestamp resolvedDate = sqlResults.getTimestamp("reimbursement_resolved");
                String description = sqlResults.getString("reimbursement_description");
                Blob receiptPicture = sqlResults.getBlob("reimbursement_receipt");
                int author_id = sqlResults.getInt("reimbursement_author");
                int resolverId = sqlResults.getInt("reimbursement_resolver");
                int statusId = sqlResults.getInt("reimbursement_status_id");
                int typeId = sqlResults.getInt("reimbursement_type_id");

                Reimbursement reimbursement = new Reimbursement(reimbursementId, amount, submissionDate, resolvedDate, description, receiptPicture, author_id, resolverId, statusId, typeId);
                employeeReimbursementList.add(reimbursement);
            }

            log.info("Successful: All Reimbursement records for employee records were successfully retrieved from the ERS database");
            connected.close();
            sqlStatement.close();
            sqlResults.close();
            return employeeReimbursementList;
        }
        catch (SQLException e) {
            log.warn("WARN: Failed to retrieve all the reimbursement request for employee from the ERS database.");
            e.printStackTrace();
            return null;

        }

    }

    @Override
    public List<Reimbursement> findAll() {

        List<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();

        try {

            String sqlQuery = "SELECT * FROM \"Reimbursement\" INNER JOIN \"Role\" ON \"Employee\".employee_id = \"Reimbursement\".reimbursement_id";

            Connection connection = JDBConnection.getConnection();
            Statement sqlStatement = connection.createStatement();
            ResultSet sqlResults = sqlStatement.executeQuery(sqlQuery);

            while (sqlResults.next()) {
                int reimbursementId = sqlResults.getInt("reimbursement_id");
                double amount = sqlResults.getDouble("reimbursement_amount");
                Timestamp submissionDate = sqlResults.getTimestamp("reimbursement_submitted");
                Timestamp resolvedDate = sqlResults.getTimestamp("reimbursement_resolved");
                String description = sqlResults.getString("reimbursement_description");
                Blob receiptPicture = sqlResults.getBlob("reimbursement_receipt");
                int authorId = sqlResults.getInt("reimbursement_author");
                int resolverId = sqlResults.getInt("reimbursement_resolver");
                int statusId = sqlResults.getInt("reimbursement_status_id");
                int typeId = sqlResults.getInt("reimbursement_type_id");

                Reimbursement reimbursement = new Reimbursement(reimbursementId, amount, submissionDate, resolvedDate, description, receiptPicture, authorId, resolverId, statusId, typeId);
                reimbursementList.add(reimbursement);
            }

            log.info("Successful: All Reimbursement records were successfully retrieved from the ERS database");
            connection.close();
            sqlStatement.close();
            sqlResults.close();
            return reimbursementList;
        }
        catch (SQLException e) {
            log.warn("WARN: Failed to retrieve all the reimbursement request from the ERS database.");
            e.printStackTrace();
            return null;

        }
    }
}
