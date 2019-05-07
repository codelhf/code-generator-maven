package com.example.generator.config;

import com.example.generator.util.StringUtil;

import java.util.List;

/**
 * @Description: 模板文件对应配置
 * @Auther: liuhf
 * @CreateTime: 2019/5/7 9:43
 */
public class TemplateConfiguration {
    /**
     * 模板文件路径
     */
    private String name;

    /**
     * 生成代码文件名后缀
     */
    private String suffix;

    /**
     * 生成代码路径
     */
    private boolean override = false;

    /**
     * 生成代码文件路径
     */
    private String directory;

    /**
     * 生成代码文件所在包
     */
    private String packageName;

    public void validate(List<String> errors, Integer listPosition) {
        if (StringUtil.isBlank(name)) {
            errors.add("Missing template name at index {" + listPosition + "}");
        }
        if (StringUtil.isBlank(suffix)) {
            errors.add("template suffix can not be empty");
        }
        if (StringUtil.isBlank(directory)) {
            errors.add("template directory can not be empty");
        }
        if (StringUtil.isBlank(packageName)) {
            errors.add("template packageName can not be empty");
        }
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

    public boolean isOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
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
}
