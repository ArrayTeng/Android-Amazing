package com.example.okhttpdemo;

import java.security.cert.CertPathValidatorException;
import java.util.List;

/**
 * @author tengfei
 * date 2019-11-24 19:42
 * email arrayadapter.cn@gmail.com
 * description
 */
public class RealInterceptorChain implements Interceptor.Chain {

    private final List<Interceptor> interceptorList;
    private final int index;
    private final Request request;

    public RealInterceptorChain(List<Interceptor> interceptorList,int index,Request request){
        this.interceptorList = interceptorList;
        this.index = index;
        this.request = request;
    }


    @Override
    public Request request() {
        return request;
    }

    @Override
    public Response proceed(Request request) {
        if (index >= interceptorList.size()) throw new AssertionError();

        RealInterceptorChain realInterceptorChainNext = new RealInterceptorChain(interceptorList,index+1,request);
        Interceptor interceptor = interceptorList.get(index);
        Response response = interceptor.intercept(realInterceptorChainNext);

        return response;
    }
}
