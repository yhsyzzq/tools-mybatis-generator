package com.zzq.domain;

/**
 * Created by yhsyzzq on 2017-11-29.
 */
public class Column {
    /**
     * 列名称
     */
    private String columnName;

    /**
     * 对应实体属性名称
     */
    private String entityFieldName;

    /**
     * 对应实体属性数据类型（带包名）
     */
    private String entityFiledType;

    /**
     * 对应实体属性数据类型（不带包名）
     */
    private String simpleEntityFiledType;

    /**
     * 列类型
     */
    private String typeName;

    /**
     * jdbc类型
     */
    private int jdbcType;

    /**
     * 列注释
     */
    private String remark;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getEntityFieldName() {
        return entityFieldName;
    }

    public void setEntityFieldName(String entityFieldName) {
        this.entityFieldName = entityFieldName;
    }

    public String getEntityFiledType() {
        return entityFiledType;
    }

    public void setEntityFiledType(String entityFiledType) {
        this.entityFiledType = entityFiledType;
    }

    public String getSimpleEntityFiledType() {
        return simpleEntityFiledType;
    }

    public void setSimpleEntityFiledType(String simpleEntityFiledType) {
        this.simpleEntityFiledType = simpleEntityFiledType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(int jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
