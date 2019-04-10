package com.example.generator.config;

import com.example.generator.util.StringUtil;
import com.example.generator.util.StringUtil;

import java.util.List;

/**
 * @Description: dao生成器
 * @Auther: liuhf
 * @CreateTime: 2019/2/28 9:31
 */
public class DaoGenerator {

    private String targetProject;

    private String targetPackage;

    public void validate(List<String> errors) {
        if (StringUtil.isBlank(targetProject)) {
            errors.add("daoGenerator.targetProject can not be blank");
        }
        if (StringUtil.isBlank(targetPackage)) {
            errors.add("daoGenerator.targetPackage can not be blank");
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
}
