package com.example.stragety.simple1;

/**
 * @author tengfei
 */
public class SubWayStrategy implements CalculateStrategy {
    @Override
    public int calculatePrice(int km) {
        if (km <= 3) {
            return 3;
        }
        if (3 < km && km <= 10) {
            return 6;
        }
        return 10;
    }
}
