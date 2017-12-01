package com.zzq.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhsyzzq on 2017-11-29.
 */
public class Table {
    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表注释
     */
    private String remark;

    /**
     * 表所含列集合
     */
    private List<Column> columnList = new ArrayList<Column>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }
}
