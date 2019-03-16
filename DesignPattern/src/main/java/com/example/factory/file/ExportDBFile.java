package com.example.factory.file;

/**
 * @author tengfei
 */
public class ExportDBFile implements IExportFileOperation {
    @Override
    public void exportData(String url) {
        System.out.println("导出的数据为 : " + url);
    }
}
