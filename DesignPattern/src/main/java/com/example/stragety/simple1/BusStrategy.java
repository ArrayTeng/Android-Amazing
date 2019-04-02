package com.example.stragety.simple1;

/**
 * @author tengfei
 */
public class BusStrategy implements CalculateStrategy {
    @Override
    public int calculatePrice(int km) {
        return 2;
    }
}
