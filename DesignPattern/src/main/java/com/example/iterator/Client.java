package com.example.iterator;

/**
 * @author tengfei
 * 责任链模式：使多个对象都有机会处理某个请求，避免发送者和接收者之间的耦合关系，这些对象形成一个链式结构，请求在这个链式结构上依次传递直到被处理完成为止
 */
public class Client {

    public static void main(String[] args){
        GroupLeader groupLeader = new GroupLeader();
        ChiefInspectorLeader chiefInspectorLeader = new ChiefInspectorLeader();
        ManagerLeader managerLeader = new ManagerLeader();
        groupLeader.nextLeader = chiefInspectorLeader;
        chiefInspectorLeader.nextLeader = managerLeader;

        groupLeader.operation(2300);
    }
}
