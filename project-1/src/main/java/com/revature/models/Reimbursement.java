package com.revature.models;

import java.sql.Blob;
import java.sql.Timestamp;

/**
 * Reimbursement class is used to model reimbursement request from and to the ERS database.
 */
public class Reimbursement {
    // Reimbursement data members
    private int reimbursementId;
    private double reimbursementAmount;
    private Timestamp timeOfSubmission;
    private Timestamp timeResolved;
    private String description;
    private Blob receipt;
    private int author;
    private int resolver;
    private int statusId;
    private int typeId;

    // Reimbursement Constructors
    public Reimbursement() {
    }

    public Reimbursement(int reimbursementId, double reimbursementAmount, Timestamp timeOfSubmission, Timestamp timeResolved, String description, Blob receipt, int author, int resolver, int statusId, int typeId) {
        this.reimbursementId = reimbursementId;
        this.reimbursementAmount = reimbursementAmount;
        this.timeOfSubmission = timeOfSubmission;
        this.timeResolved = timeResolved;
        this.description = description;
        this.receipt= receipt;
        this.author = author;
        this.resolver = resolver;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public Reimbursement(int reimbursementId, double reimbursementAmount, Timestamp timeOfSubmission, Timestamp timeResolved, String description, int author, int resolver, int statusId, int typeId) {
        this.reimbursementId = reimbursementId;
        this.reimbursementAmount = reimbursementAmount;
        this.timeOfSubmission = timeOfSubmission;
        this.timeResolved = timeResolved;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public Reimbursement(double reimbursementAmount, Timestamp timeOfSubmission, Timestamp timeResolved, String description, Blob receipt, int author, int resolver, int statusId, int typeId) {
        this.reimbursementAmount = reimbursementAmount;
        this.timeOfSubmission = timeOfSubmission;
        this.timeResolved = timeResolved;
        this.description = description;
        this.receipt= receipt;
        this.author = author;
        this.resolver = resolver;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public Reimbursement(double reimbursementAmount, Timestamp timeOfSubmission, Timestamp timeResolved, String description, int author, int resolver, int statusId, int typeId) {
        this.reimbursementAmount = reimbursementAmount;
        this.timeOfSubmission = timeOfSubmission;
        this.timeResolved = timeResolved;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    // Reimbursement Getter and Setters
    public int getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    public double getReimbursementAmount() {
        return reimbursementAmount;
    }

    public void setReimbursementAmount(double reimbursementAmount) {
        this.reimbursementAmount = reimbursementAmount;
    }

    public Timestamp getTimeOfSubmission() {
        return timeOfSubmission;
    }

    public void setTimeOfSubmission(Timestamp timeOfSubmission) {
        this.timeOfSubmission = timeOfSubmission;
    }

    public Timestamp getTimeResolved() {
        return timeResolved;
    }

    public void setTimeResolved(Timestamp timeResolved) {
        this.timeResolved = timeResolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getReceipt() {
        return receipt;
    }

    public void setReceipt(Blob receipt) {
        this.receipt = receipt;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getResolver() {
        return resolver;
    }

    public void setResolver(int resolver) {
        this.resolver = resolver;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    // Overridden Object Parent Class Methods
    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursementId=" + reimbursementId +
                ", reimbursementAmount=" + reimbursementAmount +
                ", timeOfSubmission=" + timeOfSubmission +
                ", timeResolved=" + timeResolved +
                ", description='" + description + '\'' +
                ", receipt=" + receipt +
                ", author='" + author + '\'' +
                ", resolver='" + resolver + '\'' +
                ", statusId=" + statusId +
                ", typeId=" + typeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reimbursement)) return false;

        Reimbursement that = (Reimbursement) o;

        if (reimbursementId != that.reimbursementId) return false;
        if (Double.compare(that.reimbursementAmount, reimbursementAmount) != 0) return false;
        if (author != that.author) return false;
        if (resolver != that.resolver) return false;
        if (statusId != that.statusId) return false;
        if (typeId != that.typeId) return false;
        if (timeOfSubmission != null ? !timeOfSubmission.equals(that.timeOfSubmission) : that.timeOfSubmission != null)
            return false;
        if (timeResolved != null ? !timeResolved.equals(that.timeResolved) : that.timeResolved != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return receipt != null ? receipt.equals(that.receipt) : that.receipt == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = reimbursementId;
        temp = Double.doubleToLongBits(reimbursementAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (timeOfSubmission != null ? timeOfSubmission.hashCode() : 0);
        result = 31 * result + (timeResolved != null ? timeResolved.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (receipt != null ? receipt.hashCode() : 0);
        result = 31 * result + author;
        result = 31 * result + resolver;
        result = 31 * result + statusId;
        result = 31 * result + typeId;
        return result;
    }
}
