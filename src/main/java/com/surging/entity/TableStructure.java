package com.surging.entity;

/**
 * Created by Administrator on 2018/10/22.
 */
public class TableStructure {
    private String columnName;
    private String dataType;

    public TableStructure() {
    }

    public TableStructure(String columnName, String dataType) {
        this.columnName = columnName;
        this.dataType = dataType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
