package com.example.stragety.simple1;

/**
 * @author tengfei
 */
public class Client {

    private CalculateStrategy mCalculateStrategy;

    public void setCalculateStrategy(CalculateStrategy mCalculateStrategy) {
        this.mCalculateStrategy = mCalculateStrategy;
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.setCalculateStrategy(new TaxiStrategy());
        int km = client.mCalculateStrategy.calculatePrice(100);
        System.out.println(" km "+km);
    }
}
