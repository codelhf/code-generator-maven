package com.example.generator.config;

import com.example.generator.util.StringUtil;

import java.util.List;

/**
 * @Description: 模板文件对应配置
 * @Auther: liuhf
 * @CreateTime: 2019/5/7 9:43
 */
public class TemplateConfig {

    /**
     * 模板模块名
     */
    private String name;

    /**
     * 模板文件路径
     */
    private String template;

    /**
     * 生成代码文件名后缀
     */
    private String suffix;

    /**
     * 生成代码文件类型
     */
    private String fileType;

    /**
     * 生成代码文件路径
     */
    private String directory;

    /**
     * 生成代码文件所在包
     */
    private String packageName;

    /**
     * 模板模块名
     */
    private boolean common = false;
    /**
     * 是否生成
     */
    private boolean isGenerate = true;

    /**
     * 是否重写
     */
    private boolean override = false;

    public void validate(List<String> errors, Integer listPosition) {
        if (StringUtil.isBlank(name)) {
            errors.add("Missing template name at index {" + listPosition + "}");
        }
        if (StringUtil.isBlank(template)) {
            errors.add("Missing template filePath at index {" + listPosition + "}");
        }
//        if (StringUtil.isBlank(suffix)) {
//            errors.add("template suffix can not be empty");
//        }
//        if ("null".equals(suffix)) {
//            this.suffix = null;
//        }
        if (StringUtil.isBlank(fileType)) {
            errors.add("template fileType can not be empty");
        }
        if (StringUtil.isBlank(directory)) {
            errors.add("template directory can not be empty");
        }
        if (StringUtil.isBlank(packageName)) {
            errors.add("template packageName can not be empty");
        }
    }

    public boolean isCommon() {
        return common;
    }

    public void setCommon(boolean common) {
        this.common = common;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isGenerate() {
        return isGenerate;
    }

    public void setGenerate(boolean generate) {
        isGenerate = generate;
    }

    public boolean isOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }
}
