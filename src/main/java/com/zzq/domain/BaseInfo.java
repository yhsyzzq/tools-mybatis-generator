package com.zzq.domain;

/**
 * Created by yhsyzzq on 2017-12-01.
 */
public class BaseInfo {

    /**
     * 包名
     *
     * @return
     */
    private String packageName;

    /**
     * 作者
     */
    private String author;

    /**
     * 创建日期，格式：yyyy-MM-dd
     */
    private String createDate;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 模板文件路径
     */
    private String templateFilePath;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTemplateFilePath() {
        return templateFilePath;
    }

    public void setTemplateFilePath(String templateFilePath) {
        this.templateFilePath = templateFilePath;
    }
}
