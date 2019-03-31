package com.example.iterator.leader;

/**
 * @author tengfei
 */
public class ChiefInspectorLeader extends Leader {
    @Override
    public int approvalNumber() {
        return 1000;
    }

    @Override
    public void handleMoney(int reimbursement) {
        System.out.println(" ChiefInspector agree "+reimbursement);
    }
}
