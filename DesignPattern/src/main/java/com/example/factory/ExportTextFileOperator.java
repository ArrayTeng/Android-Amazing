package com.example.factory;

/**
 * @author tengfei
 */
public class ExportTextFileOperator extends AbstractOperation {
    @Override
    public IExportFileOperation newOperator() {
        return new ExportTextFile();
    }
}
