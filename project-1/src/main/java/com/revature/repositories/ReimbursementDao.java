package com.revature.repositories;

import com.revature.models.Reimbursement;

import java.util.List;

public interface ReimbursementDao {
    public boolean insert(Reimbursement reimbursement);
    public boolean update(Reimbursement reimbursement);
    public boolean delete(Reimbursement reimbursement);
    public List<Reimbursement> findAll();
}
