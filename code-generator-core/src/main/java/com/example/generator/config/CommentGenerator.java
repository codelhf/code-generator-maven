package com.example.generator.config;

import com.example.generator.util.StringUtil;

import java.util.List;

/**
 * @Description: 旨在创建class时，对注释进行控制
 * @Auther: liuhf
 * @CreateTime: 2019/2/27 16:57
 */
public class CommentGenerator {
    /**
     * 公司
     */
    private String company;
    /**
     * 作者
     */
    private String author;
    /**
     * 生成代码的日期格式
     */
    private String dateFormat;
    /**
     * 生成文件的编码格式
     */
    private String fileEncode;
    /**
     * http接口前缀
     */
    private String httpPrefix;
    /**
     * 响应类名
     */
    private String responseClass;
    /**
     * 生成注解
     */
    private boolean generateRemark = true;
    /**
     * 生成所有内容
     */
    private boolean generateSwagger = false;
    /**
     * 是否添加备注内容
     */
    private boolean commonMapper = false;

    public void validate(List<String> errors) {
        if (StringUtil.isBlank(company)) {
            errors.add("CommentGenerator company can not be blank");
        }
        if(StringUtil.isBlank(author)) {
            errors.add("CommentGenerator author can not be blank");
        }
        if (StringUtil.isBlank(httpPrefix)) {
            errors.add("CommentGenerator httpPrefix can not be blank");
        }
        if (StringUtil.isBlank(responseClass)) {
            errors.add("CommentGenerator responseClass can not be blank");
        }
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getFileEncode() {
        return fileEncode;
    }

    public void setFileEncode(String fileEncode) {
        this.fileEncode = fileEncode;
    }

    public String getHttpPrefix() {
        return httpPrefix;
    }

    public void setHttpPrefix(String httpPrefix) {
        this.httpPrefix = httpPrefix;
    }

    public String getResponseClass() {
        return responseClass;
    }

    public void setResponseClass(String responseClass) {
        this.responseClass = responseClass;
    }

    public boolean isGenerateRemark() {
        return generateRemark;
    }

    public void setGenerateRemark(boolean generateRemark) {
        this.generateRemark = generateRemark;
    }

    public boolean isGenerateSwagger() {
        return generateSwagger;
    }

    public void setGenerateSwagger(boolean generateSwagger) {
        this.generateSwagger = generateSwagger;
    }

    public boolean isCommonMapper() {
        return commonMapper;
    }

    public void setCommonMapper(boolean commonMapper) {
        this.commonMapper = commonMapper;
    }
}
