package com.zzq.domain;

/**
 * Created by 332406 on 2017-12-01.
 */
public class ServiceImplInfo extends BaseInfo{

    /**
     * service实现类名称
     */
    private String serviceImplClassName;

    /**
     * service接口类名称
     */
    private String serviceInterfaceName;

    /**
     * 描述
     */
    private String remark;

    /**
     * 关联Dao类名称
     */
    private String daoClassName;

    /**
     * 关联Dao实例名称
     */
    private String daoName;

    /**
     * 关联实体类名称
     */
    private String entityClassName;

    /**
     * 关联实体类实例名称
     */
    private String entityName;

    /**
     * 引入包字符串
     */
    private String importStr;

    public String getServiceImplClassName() {
        return serviceImplClassName;
    }

    public void setServiceImplClassName(String serviceImplClassName) {
        this.serviceImplClassName = serviceImplClassName;
    }

    public String getServiceInterfaceName() {
        return serviceInterfaceName;
    }

    public void setServiceInterfaceName(String serviceInterfaceName) {
        this.serviceInterfaceName = serviceInterfaceName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDaoClassName() {
        return daoClassName;
    }

    public void setDaoClassName(String daoClassName) {
        this.daoClassName = daoClassName;
    }

    public String getDaoName() {
        return daoName;
    }

    public void setDaoName(String daoName) {
        this.daoName = daoName;
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
