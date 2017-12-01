package com.zzq.domain;

/**
 * Created by yhsyzzq on 2017-11-30.
 */
public class DaoInfo extends BaseInfo {

    /**
     * Dao描述
     */
    private String remark;

    /**
     * Dao接口名称
     */
    private String daoInterfaceName;

    /**
     * Dao关联实体类类名
     */
    private String entityClassName;

    /**
     * Dao关联实体类实例对象名称
     */
    private String entityName;

    /**
     * 导入包字符串
     */
    private String importStr;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDaoInterfaceName() {
        return daoInterfaceName;
    }

    public void setDaoInterfaceName(String daoInterfaceName) {
        this.daoInterfaceName = daoInterfaceName;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getImportStr() {
        return importStr;
    }

    public void setImportStr(String importStr) {
        this.importStr = importStr;
    }
}
