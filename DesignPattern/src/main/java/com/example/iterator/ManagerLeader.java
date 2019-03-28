package com.example.iterator;

/**
 * @author tengfei
 */
public class ManagerLeader extends Leader{
    @Override
    public int approvalNumber() {
        return 5000;
    }

    @Override
    public void handleMoney(int reimbursement) {
        System.out.println(" Manager agree "+reimbursement);
    }
}
