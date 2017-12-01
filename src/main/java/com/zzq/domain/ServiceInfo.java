package com.zzq.domain;

/**
 * Created by 332406 on 2017-12-01.
 */
public class ServiceInfo extends BaseInfo {
    /**
     * Service描述
     */
    private String remark;

    /**
     * Service接口名称
     */
    private String serviceInterfaceName;

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

    public String getServiceInterfaceName() {
        return serviceInterfaceName;
    }

    public void setServiceInterfaceName(String serviceInterfaceName) {
        this.serviceInterfaceName = serviceInterfaceName;
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
