package com.zzq;

import com.zzq.domain.Column;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by yhsyzzq on 2017-11-29.
 */
public class SystemMemory {

    /**
     * 数据库链接地址
     */
    public static String jdbcUrl;

    /**
     * 数据库驱动名称
     */
    public static String jdbcDriver;

    /**
     * 数据库用户名
     */
    public static String jdbcUserName;

    /**
     * 数据库密码
     */
    public static String jdbcPassword;

    /**
     * 表名称
     */
    public static String tableName;

    /**
     * 包名
     */
    public static String packageName;

    /**
     * 实体类名
     */
    public static String entityName;

    /**
     * 实体描述
     */
    public static String entityRemark;

    /**
     * 作者
     */
    public static String author;

    /**
     * 存放路径
     */
    public static String generatorFileDir;

    public static List<Column> columnList = new ArrayList<Column>();

    public static Map<String,Object> infoMap = new HashMap<String, Object>();

    static {
        InputStream is = null;
        Reader reader = null;
        Properties properties = new Properties();

        //首先在classpath中找，如果找不到，则在工作目录下找
        is = SystemMemory.class.getClassLoader().getResourceAsStream("config.properties");

        try {
            reader = new InputStreamReader(is, "UTF-8");
            properties.load(reader);

            Class clazz = SystemMemory.class;
            Field[] fieldArray = clazz.getDeclaredFields();
            for(Field field : fieldArray){
                if(field.getType() == String.class){
                    String fieldName = field.getName();
                    String fieldValue = properties.getProperty(fieldName);
                    field.set(fieldName, fieldValue);
                    System.out.println("加载系统常量：SystemMemory."+fieldName+" = "+fieldValue);
                }
            }
            //特殊处理，表名称转大写
            SystemMemory.tableName = SystemMemory.tableName.toUpperCase();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(reader);
        }
    }

}
