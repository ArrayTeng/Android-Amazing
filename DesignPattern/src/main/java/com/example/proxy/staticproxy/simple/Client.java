package com.example.proxy.staticproxy.simple;

/**
 * 码农：
 */
public class Client {

    public static void main(String[] args){
        SubjectImpl subjectImpl = new SubjectImpl();
        //假设我们没法直接访问 SubjectImpl ，这时候就需要 SubjectProxy 来协助间接访问
        SubjectProxy subjectProxy = new SubjectProxy(subjectImpl);
        String request = subjectProxy.commitCode("Hello World");
        System.out.println(request);
    }
}
