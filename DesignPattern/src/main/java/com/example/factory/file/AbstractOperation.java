package com.example.factory.file;

/**
 * @author tengfei
 */
public abstract class AbstractOperation {

    public abstract IExportFileOperation newOperator();

    void export(String url){
        IExportFileOperation exportFileOperation = newOperator();
        exportFileOperation.exportData(url);
    }
}
