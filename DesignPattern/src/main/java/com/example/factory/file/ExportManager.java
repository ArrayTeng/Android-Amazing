package com.example.factory.file;

/**
 * @author tengfei
 */
public class ExportManager {

    public static void exportDBFile(String url){
        ExportDBFileOperator dbFileOperator = new ExportDBFileOperator();
        dbFileOperator.export(url);
    }

    public static void exportTextFile(String url){
        ExportTextFileOperator textFileOperator = new ExportTextFileOperator();
        textFileOperator.export(url);
    }
}
