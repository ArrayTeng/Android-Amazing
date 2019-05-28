package com.example.proxy.staticproxy.simple;

/**
 * 代码库：我们的客户端需要具体访问的对象
 */
public class SubjectImpl implements ISubject {
    @Override
    public String commitCode(String code) {
        return "代码提交成功,本次提交的代码为 ： "+code;
    }
}
