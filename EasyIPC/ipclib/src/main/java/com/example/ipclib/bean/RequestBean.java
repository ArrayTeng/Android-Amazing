package com.example.ipclib.bean;

public class RequestBean {

    private String className;

    private String methodName;

    private RequestParameter[] requestParameters;

    private int type;

    public RequestBean() {
    }

    public RequestBean(String className, String methodName, RequestParameter[] requestParameters, int type) {
        this.className = className;
        this.methodName = methodName;
        this.requestParameters = requestParameters;
        this.type = type;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public RequestParameter[] getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(RequestParameter[] requestParameters) {
        this.requestParameters = requestParameters;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
