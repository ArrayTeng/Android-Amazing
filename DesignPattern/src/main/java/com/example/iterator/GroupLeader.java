package com.example.iterator;

/**
 * @author tengfei
 */
public class GroupLeader extends Leader {
    @Override
    public int approvalNumber() {
        return 500;
    }

    @Override
    public void handleMoney(int reimbursement) {
        System.out.println(" Group agree "+reimbursement);
    }
}
