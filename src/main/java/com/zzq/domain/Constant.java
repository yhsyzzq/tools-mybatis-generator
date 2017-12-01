package com.zzq.domain;

/**
 * Created by yhsyzzq on 2017-11-30.
 */
public class Constant {

    /**
     * 接口前缀
     */
    public final static String INTERFACE_PREFFIX = "I";

    /**
     * entity实体类后缀
     */
    public final static String ENTITY_SUFFIX = "Entity";

    /**
     * Dao数据接口后缀
     */
    public final static String DAO_SUFFIX = "Dao";

    /**
     * service业务接口及实现类后缀
     */
    public final static String SERVICE_SUFFIX = "Service";

    /**
     * mapper文件后缀
     */
    public final static String MAPPER_SUFFIX = "Mapper";

    /**
     * mapper文件resultMap后缀
     */
    public final static String MAPPER_RESULTMAP = "ResultMap";

    /**
     * 模板文件相对路径
     */
    public final static String TEMPLATE_FILE_PATH = Constant.class.getClassLoader().getResource("").getPath() + "template/";


}
