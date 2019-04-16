package com.example.observer;

/**
 * @author tengfei
 */
public class Client {

    public static void main(String[] args){
        DevTechFrontier devTechFrontier = new DevTechFrontier();

        Coder coder01 = new Coder("01 name");
        Coder coder02 = new Coder("02 name");
        Coder coder03 = new Coder("03 name");
        Coder coder04 = new Coder("04 name");

        devTechFrontier.addObserver(coder01);
        devTechFrontier.addObserver(coder02);
        devTechFrontier.addObserver(coder03);
        devTechFrontier.addObserver(coder04);

        devTechFrontier.postNewInFo("有新消息啦");
    }
}
