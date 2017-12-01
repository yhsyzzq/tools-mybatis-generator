package com.zzq.domain;

/**
 * Created by yhsyzzq on 2017-11-30.
 */
public class MapperInfo extends BaseInfo{

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 实体类全名（包含包名）
     */
    private String entityFullName;

    /**
     * 实体类映射结果集名称
     */
    private String entityResultMap;

    /**
     * mapper文件命名空间
     */
    private String namespace;

    /**
     * mapper文件结果映射字段字符串
     */
    private String resultMapStr;

    /**
     * mapper文件表字段集合字符串
     */
    private String columnStr;

    /**
     * mapper文件模糊匹配字段集合字符串
     */
    private String likeStr;

    /**
     * mapper文件精确匹配字段集合字符串
     */
    private String equalStr;

    /**
     * mapper文件插入字段字符串
     */
    private String insertColumnStr;

    /**
     * mapper文件插入字段名称字符串
     */
    private String insertColumnNameStr;

    /**
     * mapper文件更新字段字符串
     */
    private String updateColumnStr;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEntityFullName() {
        return entityFullName;
    }

    public void setEntityFullName(String entityFullName) {
        this.entityFullName = entityFullName;
    }

    public String getEntityResultMap() {
        return entityResultMap;
    }

    public void setEntityResultMap(String entityResultMap) {
        this.entityResultMap = entityResultMap;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getResultMapStr() {
        return resultMapStr;
    }

    public void setResultMapStr(String resultMapStr) {
        this.resultMapStr = resultMapStr;
    }

    public String getColumnStr() {
        return columnStr;
    }

    public void setColumnStr(String columnStr) {
        this.columnStr = columnStr;
    }

    public String getInsertColumnNameStr() {
        return insertColumnNameStr;
    }

    public void setInsertColumnNameStr(String insertColumnNameStr) {
        this.insertColumnNameStr = insertColumnNameStr;
    }

    public String getLikeStr() {
        return likeStr;
    }

    public void setLikeStr(String likeStr) {
        this.likeStr = likeStr;
    }

    public String getEqualStr() {
        return equalStr;
    }

    public void setEqualStr(String equalStr) {
        this.equalStr = equalStr;
    }

    public String getInsertColumnStr() {
        return insertColumnStr;
    }

    public void setInsertColumnStr(String insertColumnStr) {
        this.insertColumnStr = insertColumnStr;
    }

    public String getUpdateColumnStr() {
        return updateColumnStr;
    }

    public void setUpdateColumnStr(String updateColumnStr) {
        this.updateColumnStr = updateColumnStr;
    }

}
