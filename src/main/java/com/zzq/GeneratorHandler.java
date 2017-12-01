package com.zzq;

import com.zzq.domain.*;
import freemarker.template.Configuration;
import org.apache.commons.dbutils.DbUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yhsyzzq on 2017-11-29.
 */
public class GeneratorHandler {

    private Configuration configuration;

    public void beginTask() {
        preHandler();

        //生成实体类文件
        generateEntityCode();

        //生成mapper映射文件
        generateMapperCode();

        //生成dao文件
        generateIDaoCode();

        //生成service接口文件
        generateIServiceCode();

        //生成service实现类代码文件
        generateServiceImplCode();
    }

    /**
     * 初始化内存数据
     */
    public void preHandler() {
        //初始化模板配置信息
        initFreeMarkerConfig();

        //初始化数据库表信息
        initJdbcInfo();
    }

    /**
     * 初始化模板信息
     */
    public void initFreeMarkerConfig() {
        configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding("utf-8");
        configuration.setObjectWrapper(Configuration.getDefaultObjectWrapper(Configuration.VERSION_2_3_23));
        configuration.setClassForTemplateLoading(this.getClass(), "/");
    }

    /**
     * 获取数据库表信息放入内存
     */
    public void initJdbcInfo() {
        //加载数据表信息
        Connection conn = null;
        try {
            Class.forName(SystemMemory.jdbcDriver);
            conn = DriverManager.getConnection(SystemMemory.jdbcUrl, SystemMemory.jdbcUserName, SystemMemory.jdbcPassword);
            DatabaseMetaData dbMetaData = conn.getMetaData();
            String schemaPattern = dbMetaData.getUserName().toUpperCase(); //数据库schema
            ResultSet tableRS = dbMetaData.getTables(null, schemaPattern, "", new String[]{"TABLE"});
            while (tableRS.next()) {
                String tableName = tableRS.getString("TABLE_NAME"); //表名
                if (tableName.equals(SystemMemory.tableName)) {
                    String tableRemark = tableRS.getString("REMARKS");   //表注释
                    Table table = new Table();
                    table.setTableName(tableName);
                    table.setRemark(tableRemark);

                    //获取列信息
                    ResultSet columnRS = dbMetaData.getColumns(null, schemaPattern, tableName, "");
                    while (columnRS.next()) {
                        String columnName = columnRS.getString("COLUMN_NAME"); //列名称
                        String typeName = columnRS.getString("TYPE_NAME"); //列数据类型
                        int jdbcType = Integer.valueOf(columnRS.getString("DATA_TYPE")); //列jdbc类型
                        String entityFieldName = Utils.getEntityFieldName(columnName);
                        String entityFieldType = Utils.getJavaType(jdbcType);
                        String simpleEntityFieldType = Utils.getSimpleTypeName(entityFieldType);
                        String remark = columnRS.getString("REMARKS"); //列注释
                        Column column = new Column();
                        column.setColumnName(columnName);
                        column.setTypeName(typeName);
                        column.setJdbcType(jdbcType);
                        column.setRemark(remark);
                        column.setEntityFieldName(entityFieldName);
                        column.setEntityFiledType(entityFieldType);
                        column.setSimpleEntityFiledType(simpleEntityFieldType);
                        System.out.println("列名称:" + columnName + ", 列数据类型:" + typeName + ", 对应实体属性名称:" + entityFieldName + ", 对应实体属性类型:" + entityFieldType + ", 对应实体属性类型(不带包名):" + simpleEntityFieldType + ", 列jdbc类型:" + jdbcType + ", 列注释：" + remark);
                        SystemMemory.columnList.add(column);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * 生成实体类代码文件
     */
    public void generateEntityCode() {
        EntityInfo entityInfo = new EntityInfo();
        entityInfo.setEntityClassName(SystemMemory.entityName); //实体类名称
        entityInfo.setEntityRemark(SystemMemory.entityRemark); //实体类注释
        entityInfo.setAuthor(SystemMemory.author);  //作者
        entityInfo.setFieldList(SystemMemory.columnList); //实体类属性集合
        entityInfo.setPackageName(SystemMemory.packageName + ".entity"); //实体类包名
        entityInfo.setFilePath(SystemMemory.generatorFileDir + "/entity/" + SystemMemory.entityName + Constant.ENTITY_SUFFIX + ".java"); //实体类文件路径
        entityInfo.setTemplateFilePath(Constant.TEMPLATE_FILE_PATH + Constant.ENTITY_SUFFIX + ".ftl"); //实体类模板文件路径
        entityInfo.setCreateDate(Utils.getNowDateStr());//创建时间

        //构建import字符串,field属性字符串
        StringBuilder importSb = new StringBuilder();   //导入包字符串缓存
        StringBuilder fieldSb = new StringBuilder();    //字段属性字符串缓存
        StringBuilder methodSb = new StringBuilder();    //方法字符串缓存
        Map<String, String> importPackageMap = new HashMap<String, String>();
        for (Column column : SystemMemory.columnList) {
            String entityFieldName = column.getEntityFieldName();
            String entityFieldType = column.getEntityFiledType();
            String simpleEntityFieldType = column.getSimpleEntityFiledType();

            if (entityFieldType.contains(".") && importPackageMap.get(entityFieldType) == null) {
                //import字符串
                importSb.append("import ").append(entityFieldType + ";\r\n");
                importPackageMap.put(entityFieldType, entityFieldType);
            }
            fieldSb.append("    /**\r\n     * ").append(column.getRemark()).append("\r\n     */\r\n").append("    private ").append(simpleEntityFieldType)
                    .append(" " + entityFieldName + ";\r\n\r\n");
            //seter方法
            methodSb.append("    /**\r\n     * ").append(column.getRemark()).append("\r\n     * @param ").append(entityFieldName).append("\r\n     */\r\n").append("    public void set").append(Utils.toFirstUperCase(entityFieldName))
                    .append("(" + simpleEntityFieldType + " ").append(entityFieldName + ") {\r\n")
                    .append("        this." + entityFieldName).append(" = " + entityFieldName + ";\r\n    }\r\n\r\n");
            //getter方法
            methodSb.append("    /**\r\n     * ").append(column.getRemark()).append("\r\n     * @return ").append(entityFieldName).append("\r\n     */\r\n").append("    public " + simpleEntityFieldType + " get").append(Utils.toFirstUperCase(entityFieldName))
                    .append("() {\r\n").append("        return " + entityFieldName).append(";\r\n    }\r\n\r\n");

        }
        System.out.println(importSb.toString());
        System.out.println(fieldSb.toString());
        System.out.println(methodSb.toString());
        entityInfo.setImportStr(importSb.toString());
        entityInfo.setFieldStr(fieldSb.toString());
        entityInfo.setMethodStr(methodSb.toString());
        SystemMemory.infoMap.put("entityInfo", entityInfo);

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("packageName", entityInfo.getPackageName());
        paramMap.put("importStr", entityInfo.getImportStr());
        paramMap.put("description", entityInfo.getEntityRemark());
        paramMap.put("author", entityInfo.getAuthor());
        paramMap.put("createDate", entityInfo.getCreateDate());
        paramMap.put("className", entityInfo.getEntityClassName());
        paramMap.put("fieldStr", entityInfo.getFieldStr());
        paramMap.put("methodStr", entityInfo.getMethodStr());

        //保存实体类代码文件
        Utils.saveCodeFile(paramMap, entityInfo, configuration);
    }

    /**
     * 生成mapper映射xml文件
     */
    public void generateMapperCode() {
        String entityFullName = SystemMemory.packageName + ".entity." + SystemMemory.entityName;
        String namespace = SystemMemory.packageName + ".dao." + Constant.INTERFACE_PREFFIX
                + Utils.toFirstUperCase(SystemMemory.entityName) + Constant.DAO_SUFFIX; //命名空间

        MapperInfo mapperInfo = new MapperInfo();
        mapperInfo.setTableName(SystemMemory.tableName); //表名称
        mapperInfo.setEntityFullName(entityFullName); //实体类全名
        mapperInfo.setNamespace(namespace); //命名空间
        mapperInfo.setEntityResultMap(SystemMemory.entityName + Constant.MAPPER_RESULTMAP); //mapper映射结果集
        mapperInfo.setFilePath(SystemMemory.generatorFileDir + "/ibatis/" + SystemMemory.entityName + "Dao" + Constant.MAPPER_SUFFIX + ".xml"); //mapper文件路径
        mapperInfo.setTemplateFilePath(Constant.TEMPLATE_FILE_PATH + Constant.MAPPER_SUFFIX + ".ftl"); //mapper模板文件路径

        //构建import字符串,field属性字符串
        StringBuilder resultMapSb = new StringBuilder();   //mapper字段映射字符串缓存
        StringBuilder columnSb = new StringBuilder();   //mapper字段字符串缓存
        StringBuilder likeSb = new StringBuilder();   //mapper模糊匹配字段字符串缓存
        StringBuilder equalSb = new StringBuilder();   //mapper精确匹配字段字符串缓存
        StringBuilder insertColumnNameSb = new StringBuilder();   //mapper插入字段名称字符串缓存
        StringBuilder insertColumnSb = new StringBuilder();   //mapper插入字段值字符串缓存
        StringBuilder updateColumnSb = new StringBuilder();   //mapper修改字段值字符串缓存


        updateColumnSb.append("            <trim prefix=\"set\" suffixOverrides=\",\">\r\n");
        for (Column column : SystemMemory.columnList) {
            String entityFieldName = column.getEntityFieldName(); //实体类属性名称
            String columnName = column.getColumnName().toUpperCase(); //列名称
            String columnTypeName = column.getTypeName(); //字段类型
            String simpleEntityFieldType = column.getSimpleEntityFiledType();
            //构建字段resultMap集合字符串
            resultMapSb.append("        <!-- " + column.getRemark() + " -->\r\n").append("        <result property=\"")
                    .append(entityFieldName).append("\" column=\"").append(columnName).append("\" />\r\n");

            //构建字段集合字符串
            columnSb.append("        ").append(columnName).append(",\r\n");

            //构建模糊匹配字段字符串
            if (entityFieldName != null && !"id".equals(entityFieldName)) {
                likeSb.append("        <if test=\"").append(entityFieldName).append(" != null");
                if (simpleEntityFieldType.equals("String")) {
                    //字符串类型拼接(！="")
                    likeSb.append(" and ").append(entityFieldName).append(" != '' ");
                }
                likeSb.append("\">\r\n").append("            AND ").append(columnName)
                        .append(" like concat('%',#{").append(entityFieldName).append("},'%')\r\n        </if>\r\n");
            }

            //构建精确匹配字段字符串
            equalSb.append("        <if test=\"").append(entityFieldName).append(" !=null");
            if (simpleEntityFieldType.equals("String")) {
                //字符串类型拼接(！="")
                equalSb.append(" and ").append(entityFieldName).append(" != '' ");
            }
            equalSb.append("\">\r\n").append("            AND ").append(columnName).append(" = #{")
                    .append(entityFieldName).append("}\r\n        </if>\n");

            //构建插入字段名称字符串、
            insertColumnNameSb.append("            ").append(columnName).append(",\r\n");

            //构建插入字段值字符串
            insertColumnSb.append("            #{").append(entityFieldName).append(", jdbcType=").append(columnTypeName).append("},\r\n");

            //构建修改字段值字符串
            updateColumnSb.append("            <if test=\"").append(entityFieldName).append(" !=null");
            if (simpleEntityFieldType.equals("String")) {
                //字符串类型拼接(！="")
                updateColumnSb.append(" and ").append(entityFieldName).append(" != '' ");
            }
            updateColumnSb.append("\">\r\n").append("                ").append(columnName).append(" = #{")
                    .append(entityFieldName).append(", jdbcType=").append(columnTypeName).append("},\r\n            </if>\r\n");

        }
        updateColumnSb.append("            </trim>");

        //去除行尾逗号
        String columnStr = Utils.trimLastComma(columnSb.toString());
        String insertColumnStr = Utils.trimLastComma(insertColumnSb.toString());
        String insertColumnNameStr = Utils.trimLastComma(insertColumnNameSb.toString());

        System.out.println(resultMapSb.toString());
        System.out.println(columnStr);
        System.out.println(likeSb.toString());
        System.out.println(equalSb.toString());
        System.out.println(insertColumnStr);
        System.out.println(insertColumnNameStr);
        System.out.println(updateColumnSb.toString());

        mapperInfo.setResultMapStr(Utils.trimLeft(resultMapSb.toString()));
        mapperInfo.setColumnStr(Utils.trimLeft(columnStr));
        mapperInfo.setLikeStr(Utils.trimLeft(likeSb.toString()));
        mapperInfo.setEqualStr(Utils.trimLeft(equalSb.toString()));
        mapperInfo.setInsertColumnNameStr(Utils.trimLeft(insertColumnNameStr));
        mapperInfo.setInsertColumnStr(Utils.trimLeft(insertColumnStr));
        mapperInfo.setUpdateColumnStr(Utils.trimLeft(updateColumnSb.toString()));
        SystemMemory.infoMap.put("mapperInfo", mapperInfo);

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("description", SystemMemory.entityRemark);
        paramMap.put("namespace", mapperInfo.getNamespace());
        paramMap.put("entityResultMap", mapperInfo.getEntityResultMap());
        paramMap.put("entityFullName", mapperInfo.getEntityFullName());
        paramMap.put("resultMapStr", mapperInfo.getResultMapStr());
        paramMap.put("columnStr", mapperInfo.getColumnStr());
        paramMap.put("likeStr", mapperInfo.getLikeStr());
        paramMap.put("equalStr", mapperInfo.getEqualStr());
        paramMap.put("tableName", mapperInfo.getTableName());
        paramMap.put("insertColumnNameStr", mapperInfo.getInsertColumnNameStr());
        paramMap.put("insertColumnStr", mapperInfo.getInsertColumnStr());
        paramMap.put("updateColumnStr", mapperInfo.getUpdateColumnStr());

        //保存实体类代码文件
        Utils.saveCodeFile(paramMap, mapperInfo, configuration);
    }

    /**
     * 生成Dao类代码文件
     */
    public void generateIDaoCode() {
        DaoInfo daoInfo = new DaoInfo();
        daoInfo.setDaoInterfaceName("I" + Utils.toFirstUperCase(SystemMemory.entityName) + Constant.DAO_SUFFIX); //Dao接口名称
        daoInfo.setRemark(SystemMemory.entityRemark); //描述
        daoInfo.setEntityClassName(SystemMemory.entityName); // 关联实体类类名
        daoInfo.setEntityName(Utils.toFirstLowerCase(SystemMemory.entityName)); // 关联实体类实例对象名称
        daoInfo.setAuthor(SystemMemory.author); //作者
        daoInfo.setFilePath(SystemMemory.generatorFileDir + "/dao/" + daoInfo.getDaoInterfaceName() + ".java"); //dao文件存放路径
        daoInfo.setTemplateFilePath(Constant.TEMPLATE_FILE_PATH + "I" + Constant.DAO_SUFFIX + ".ftl"); //dao模板路径
        daoInfo.setPackageName(SystemMemory.packageName + ".dao"); //包名
        daoInfo.setCreateDate(Utils.getNowDateStr()); //创建时间

        //构建import字符串字符串
        EntityInfo entityInfo = (EntityInfo) SystemMemory.infoMap.get("entityInfo");
        String importStr = "import " + entityInfo.getPackageName() + "." + entityInfo.getEntityClassName() + ";";
        daoInfo.setImportStr(importStr);
        System.out.println(importStr);
        SystemMemory.infoMap.put("daoInfo", daoInfo);

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("packageName", daoInfo.getPackageName());
        paramMap.put("importStr", daoInfo.getImportStr());
        paramMap.put("description", daoInfo.getRemark());
        paramMap.put("author", daoInfo.getAuthor());
        paramMap.put("createDate", daoInfo.getCreateDate());
        paramMap.put("className", daoInfo.getDaoInterfaceName());
        paramMap.put("entityClassName", daoInfo.getEntityClassName());
        paramMap.put("entityName", daoInfo.getEntityName());

        //保存Dao代码文件
        Utils.saveCodeFile(paramMap, daoInfo, configuration);
    }

    /**
     * 生成service接口代码文件
     */
    public void generateIServiceCode() {
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setServiceInterfaceName("I" + Utils.toFirstUperCase(SystemMemory.entityName) + Constant.SERVICE_SUFFIX); //service接口名称
        serviceInfo.setRemark(SystemMemory.entityRemark); //描述
        serviceInfo.setEntityClassName(SystemMemory.entityName); //关联实体类类名
        serviceInfo.setEntityName(Utils.toFirstLowerCase(SystemMemory.entityName)); // 关联实体类实例对象名称
        serviceInfo.setAuthor(SystemMemory.author); //作者
        serviceInfo.setFilePath(SystemMemory.generatorFileDir + "/service/" + serviceInfo.getServiceInterfaceName() + ".java"); //service文件存放路径
        serviceInfo.setTemplateFilePath(Constant.TEMPLATE_FILE_PATH + "I" + Constant.SERVICE_SUFFIX + ".ftl"); //dao模板路径
        serviceInfo.setPackageName(SystemMemory.packageName + ".service"); //包名
        serviceInfo.setCreateDate(Utils.getNowDateStr()); //创建时间

        //构建import字符串字符串
        EntityInfo entityInfo = (EntityInfo) SystemMemory.infoMap.get("entityInfo");
        String importStr = "import " + entityInfo.getPackageName() + "." + entityInfo.getEntityClassName() + ";";
        serviceInfo.setImportStr(importStr);
        System.out.println(importStr);

        SystemMemory.infoMap.put("serviceInfo", serviceInfo);

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("packageName", serviceInfo.getPackageName());
        paramMap.put("importStr", serviceInfo.getImportStr());
        paramMap.put("description", serviceInfo.getRemark());
        paramMap.put("author", serviceInfo.getAuthor());
        paramMap.put("createDate", serviceInfo.getCreateDate());
        paramMap.put("className", serviceInfo.getServiceInterfaceName());
        paramMap.put("entityName", serviceInfo.getEntityName());
        paramMap.put("entityClassName", serviceInfo.getEntityClassName());

        //保存Service代码文件
        Utils.saveCodeFile(paramMap, serviceInfo, configuration);
    }

    /**
     * 生成service实现类代码文件
     */
    public void generateServiceImplCode() {
        EntityInfo entityInfo = (EntityInfo) SystemMemory.infoMap.get("entityInfo");
        DaoInfo daoInfo = (DaoInfo) SystemMemory.infoMap.get("daoInfo");
        ServiceInfo serviceInfo = (ServiceInfo) SystemMemory.infoMap.get("serviceInfo");

        ServiceImplInfo serviceImplInfo = new ServiceImplInfo();
        serviceImplInfo.setServiceImplClassName(Utils.toFirstUperCase(SystemMemory.entityName) + Constant.SERVICE_SUFFIX); //service实现类名称
        serviceImplInfo.setServiceInterfaceName("I" + Utils.toFirstUperCase(SystemMemory.entityName) + Constant.SERVICE_SUFFIX); //service接口名称
        serviceImplInfo.setRemark(SystemMemory.entityRemark); //描述
        serviceImplInfo.setEntityClassName(SystemMemory.entityName); //关联实体类类名
        serviceImplInfo.setEntityName(Utils.toFirstLowerCase(SystemMemory.entityName)); // 关联实体类实例对象名称
        serviceImplInfo.setDaoClassName(daoInfo.getDaoInterfaceName()); //Dao接口类类名
        serviceImplInfo.setDaoName(serviceImplInfo.getEntityName() + Constant.DAO_SUFFIX); //Dao接口实例名
        serviceImplInfo.setAuthor(SystemMemory.author); //作者
        serviceImplInfo.setFilePath(SystemMemory.generatorFileDir + "/service/impl/" + serviceImplInfo.getServiceImplClassName() + ".java"); //service文件存放路径
        serviceImplInfo.setTemplateFilePath(Constant.TEMPLATE_FILE_PATH + "ServiceImpl" + ".ftl"); //dao模板路径
        serviceImplInfo.setPackageName(SystemMemory.packageName + ".service.impl"); //包名
        serviceImplInfo.setCreateDate(Utils.getNowDateStr()); //创建时间

        //构建import字符串字符串
        StringBuilder importSb = new StringBuilder();
        importSb.append("import ").append(entityInfo.getPackageName() + ".").append(entityInfo.getEntityClassName() + ";\r\n")
                .append("import ").append(daoInfo.getPackageName() + ".").append(daoInfo.getDaoInterfaceName() + ";\r\n")
                .append("import ").append(serviceInfo.getPackageName() + ".").append(serviceInfo.getServiceInterfaceName() + ";\r\n");
        System.out.println(importSb.toString());
        serviceImplInfo.setImportStr(importSb.toString());

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("packageName", serviceImplInfo.getPackageName());
        paramMap.put("importStr", serviceImplInfo.getImportStr());
        paramMap.put("description", serviceImplInfo.getRemark());
        paramMap.put("author", serviceImplInfo.getAuthor());
        paramMap.put("createDate", serviceImplInfo.getCreateDate());
        paramMap.put("className", serviceImplInfo.getServiceImplClassName());
        paramMap.put("serviceInterfaceName", serviceImplInfo.getServiceInterfaceName());
        paramMap.put("daoClassName", serviceImplInfo.getDaoClassName());
        paramMap.put("daoName", serviceImplInfo.getDaoName());
        paramMap.put("entityName", serviceImplInfo.getEntityName());
        paramMap.put("entityClassName", Utils.toFirstUperCase(serviceImplInfo.getEntityName()));

        //保存Service代码文件
        Utils.saveCodeFile(paramMap, serviceImplInfo, configuration);
    }
}
