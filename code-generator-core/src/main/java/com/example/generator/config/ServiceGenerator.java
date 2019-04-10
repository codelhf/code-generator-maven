package com.example.generator.config;

import com.example.generator.util.StringUtil;
import com.example.generator.util.StringUtil;

import java.util.List;

/**
 * @Description: service层开始要和dao能够分开,不提供重写文件功能
 * @Auther: liuhf
 * @CreateTime: 2019/2/27 16:59
 */
public class ServiceGenerator {
    /**
     * 是否生成代码
     */
    private boolean generator = true;
    /**
     * 是否允许子包，即targetPackage.schemaName.tableName
     */
    private boolean enableSubPackages = false;
    /**
     * 代码生成根目录
     */
    private String targetProject;
    /**
     * controller生成包路径
     */
    private String controller;
    /**
     * service生成包路径
     */
    private String service;
    /**
     * serviceImpl生成包路径
     */
    private String serviceImpl;

    public void validate(List<String> errors) {
        if (StringUtil.isBlank(targetProject)) {
            errors.add("serviceGenerator.targetProject can not be blank");
        }
        if (StringUtil.isBlank(controller)) {
            errors.add("serviceGenerator.controller can not be blank");
        }
        if (StringUtil.isBlank(service)) {
            errors.add("serviceGenerator.service can not be blank");
        }
        if (StringUtil.isBlank(serviceImpl)) {
            errors.add("serviceGenerator.serviceImpl can not be blank");
        }
    }

    public boolean isGenerator() {
        return generator;
    }

    public void setGenerator(boolean generator) {
        this.generator = generator;
    }

    public boolean isEnableSubPackages() {
        return enableSubPackages;
    }

    public void setEnableSubPackages(boolean enableSubPackages) {
        this.enableSubPackages = enableSubPackages;
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServiceImpl() {
        return serviceImpl;
    }

    public void setServiceImpl(String serviceImpl) {
        this.serviceImpl = serviceImpl;
    }
}
