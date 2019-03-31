package com.example.iterator.user;

/**
 * @author tengfei
 */
public abstract class AbsUserSystemHandler implements IUserSystemHandler<AbsUserSystemHandler>, IUserSystem {

    private AbsUserSystemHandler nextHandler;

    public AbsUserSystemHandler getNextHandler() {
        return nextHandler;
    }

    @Override
    public void nextHandler(AbsUserSystemHandler nextHandler) {
        this.nextHandler = nextHandler;
    }


}
