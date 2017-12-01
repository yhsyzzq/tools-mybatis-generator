package com.zzq;

import com.zzq.domain.BaseInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by yhsyzzq on 2017-11-29.
 */
public class Utils {

    /**
     * 保存代码文件
     *
     * @param paramMap
     * @param baseInfo
     * @param configuration
     */
    public static void saveCodeFile(Map<String, String> paramMap, BaseInfo baseInfo, Configuration configuration) {
        try {
            //读取实体类模板文件
            String templateStr = FileUtils.readFileToString(new File(baseInfo.getTemplateFilePath()), "utf-8");
            Template template = new Template("", new StringReader("<#escape x as (x)>" + templateStr + "</#escape>"), configuration);
            StringWriter writer = new StringWriter();
            template.process(paramMap, writer);
            FileUtils.writeStringToFile(new File(baseInfo.getFilePath()), writer.toString(), "utf-8");
            System.out.println("保存代码文件成功：" + baseInfo.getFilePath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据jdbc类型获取java类型
     *
     * @param jdbcType
     * @return
     */
    public static String getJavaType(int jdbcType) {
        switch (jdbcType) {
            case Types.CHAR:
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
                return String.class.getName();
            case Types.NUMERIC:
            case Types.DECIMAL:
                return BigDecimal.class.getName();
            case Types.BIT:
                return boolean.class.getName();
            case Types.TINYINT:
                return byte.class.getName();
            case Types.SMALLINT:
                return short.class.getName();
            case Types.INTEGER:
                return int.class.getName();
            case Types.BIGINT:
                return long.class.getName();
            case Types.REAL:
                return float.class.getName();
            case Types.FLOAT:
            case Types.DOUBLE:
                return double.class.getName();
            case Types.DATE:
                return Date.class.getName();
            case Types.TIME:
                return Time.class.getName();
            case Types.TIMESTAMP:
                return Timestamp.class.getName();
            default:
                return null;
        }
    }

    /**
     * 字段名转为属性名
     *
     * @param columnName
     * @return
     */
    public static String getEntityFieldName(String columnName) {
        if (columnName != null && !"".equals(columnName)) {
            String[] strs = columnName.toLowerCase().split("_");
            String entityFieldName = "";
            int count = 0; //计数器
            for (String str : strs) {
                char[] cs = str.toCharArray();
                if (count > 0 && cs[0] >= 97 && cs[0] <= 122) {
                    cs[0] -= 32;
                }
                entityFieldName += String.valueOf(cs);
                count++;
            }
            return entityFieldName;
        }
        return columnName;
    }

    /**
     * 获取去除包名的字段类型
     *
     * @return
     */
    public static String getSimpleTypeName(String entityFieldType) {
        if (entityFieldType.contains(".")) {
            String[] typeStrs = entityFieldType.split("\\.");
            return typeStrs[typeStrs.length - 1];
        }
        return entityFieldType;
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String toFirstUperCase(String str) {
        if (str != null && !"".equals(str)) {
            char[] cs = str.toCharArray();
            if (cs[0] >= 97 && cs[0] <= 122) {
                cs[0] -= 32;
                return String.valueOf(cs);
            }
        }
        return str;
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String toFirstLowerCase(String str) {
        if (str != null && !"".equals(str)) {
            char[] cs = str.toCharArray();
            if (cs[0] >= 65 && cs[0] <= 90) {
                cs[0] += 32;
                return String.valueOf(cs);
            }
        }
        return str;
    }

    /**
     * 去除行首空白
     *
     * @param str
     * @return
     */
    public static String trimLeft(String str) {
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) != ' ' && i != 0) {
                    return str.substring(i);
                }
            }
        }
        return str;
    }

    /**
     * 去除行尾逗号
     *
     * @param str
     * @return
     */
    public static String trimLastComma(String str) {
        if (str.indexOf(",") > 0) {
            return str.substring(0, str.lastIndexOf(","));
        }
        return str;
    }

    /**
     * 获取当前日期字符串：格式：yyyy-MM-dd
     *
     * @return
     */
    public static String getNowDateStr() {
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(currentDate);
    }
}
