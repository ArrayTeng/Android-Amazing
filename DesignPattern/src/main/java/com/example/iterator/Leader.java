package com.example.iterator;

/**
 * @author tengfei
 */
public abstract class Leader {

    public Leader nextLeader;

    public abstract int approvalNumber();

    public void operation(int reimbursement) {
        if (reimbursement > approvalNumber()) {
            if (nextLeader != null) {
                nextLeader.operation(reimbursement);
            }
        }else {
            handleMoney(reimbursement);
        }
    }

    public abstract void handleMoney(int reimbursement);
}
