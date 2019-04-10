package com.example.generator.config;

import com.example.generator.util.StringUtil;
import com.example.generator.util.StringUtil;

import java.util.List;

/**
 * @Description: dto生成器
 * @Auther: liuhf
 * @CreateTime: 2019/2/28 9:24
 */
public class DtoGenerator {

    private String targetProject;

    private String targetPackage;
    /**
     * 是否对dto添加全参构造函数, 添加全参构造函数时需添加无参构造
     */
    private boolean allConstructor = true;
    /**
     * 是否对dto中char类型的列进行trim操作
     */
    private boolean trimStrings = true;
    /**
     * 建立的dto对象是否不可改变, 即生成的dto对象不会有setter方法,只有构造方法
     */
    private boolean immutable = false;
    /**
     * 是否对dto使用lombok注解, 使用lombok注解将不会生成getter,setter和constructor方法
     */
    private boolean useLombok = false;

    public void validate(List<String> errors) {
        if (StringUtil.isBlank(targetProject)) {
            errors.add("dtoGenerator.targetProject can not be blank");
        }
        if (StringUtil.isBlank(targetPackage)) {
            errors.add("dtoGenerator.targetPackage can not be blank");
        }
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public boolean isAllConstructor() {
        return allConstructor;
    }

    public void setAllConstructor(boolean allConstructor) {
        this.allConstructor = allConstructor;
    }

    public boolean isTrimStrings() {
        return trimStrings;
    }

    public void setTrimStrings(boolean trimStrings) {
        this.trimStrings = trimStrings;
    }

    public boolean isImmutable() {
        return immutable;
    }

    public void setImmutable(boolean immutable) {
        this.immutable = immutable;
    }

    public boolean isUseLombok() {
        return useLombok;
    }

    public void setUseLombok(boolean useLombok) {
        this.useLombok = useLombok;
    }
}
