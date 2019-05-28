package com.example.proxy.staticproxy.simple;

/**
 * 代理对象中维护了"委托对象"的实例，并通过"委托对象"达到间接访问的目的
 */
public class SubjectProxy implements ISubject {


    private ISubject iSubject;

    public SubjectProxy(ISubject iSubject) {
        this.iSubject = iSubject;
    }

    @Override
    public String commitCode(String code) {
        return iSubject.commitCode(code);
    }
}
