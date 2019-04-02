package com.example.stragety.simple1;

/**
 * @author tengfei
 */
public class TaxiStrategy implements CalculateStrategy {
    @Override
    public int calculatePrice(int km) {
        if (km <= 3) {
            return 5;
        }
        if (km > 3) {
            return 5 + (km - 3) * 2;
        }
        return 0;
    }
}
