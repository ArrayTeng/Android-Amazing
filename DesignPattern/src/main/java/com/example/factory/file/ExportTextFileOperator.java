package com.example.factory.file;

/**
 * @author tengfei
 */
public class ExportTextFileOperator extends AbstractOperation {
    @Override
    public IExportFileOperation newOperator() {
        return new ExportTextFile();
    }
}
