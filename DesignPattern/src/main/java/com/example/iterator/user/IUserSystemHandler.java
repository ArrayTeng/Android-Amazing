package com.example.iterator.user;

/**
 * @author tengfei
 */
public interface IUserSystemHandler<T extends IUserSystemHandler> {

    void nextHandler(T systemHandler);
}
