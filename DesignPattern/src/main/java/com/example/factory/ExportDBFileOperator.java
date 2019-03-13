package com.example.factory;

/**
 * @author tengfei
 */
public class ExportDBFileOperator extends AbstractOperation {
    @Override
    public IExportFileOperation newOperator() {
        return new ExportDBFile();
    }
}
