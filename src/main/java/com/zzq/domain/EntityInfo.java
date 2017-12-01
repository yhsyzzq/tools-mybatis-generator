package com.zzq.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhsyzzq on 2017-11-29.
 */
public class EntityInfo extends BaseInfo{
    /**
     * 实体类名称
     */
    private String entityClassName;

    /**
     * 实体类描述
     */
    private String entityRemark;

    /**
     * 导入包字符串
     */
    private String importStr;

    /**
     * 属性字符串
     */
    private String fieldStr;

    /**
     * 方法字符串
     */
    private String methodStr;

    /**
     * 实体类属性集合
     */
    private List<Column> fieldList = new ArrayList<Column>();

    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    public String getEntityRemark() {
        return entityRemark;
    }

    public void setEntityRemark(String entityRemark) {
        this.entityRemark = entityRemark;
    }

    public List<Column> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Column> fieldList) {
        this.fieldList = fieldList;
    }

    public String getImportStr() {
        return importStr;
    }

    public void setImportStr(String importStr) {
        this.importStr = importStr;
    }

    public String getFieldStr() {
        return fieldStr;
    }

    public void setFieldStr(String fieldStr) {
        this.fieldStr = fieldStr;
    }

    public String getMethodStr() {
        return methodStr;
    }

    public void setMethodStr(String methodStr) {
        this.methodStr = methodStr;
    }
}
