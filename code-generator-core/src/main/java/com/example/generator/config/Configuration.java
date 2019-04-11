package com.example.generator.config;

import com.example.generator.exception.InvalidConfigurationException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * GeneratorConfiguration
 */
public class Configuration implements Serializable {
    /**
     * 数据库配置文件
     */
    private String resource;
    /**
     * generatorConfig配置文件路径
     */
    private String configFilePath;
    /**
     * 注释控制器
     */
    private CommentGenerator commentGenerator;
    /**
     * 数据源
     */
    private DataSource dataSource;
    /**
     * java类型适配器
     */
    private JavaTypeResolver javaTypeResolver;
    /**
     * service层生成器
     */
    private ServiceGenerator serviceGenerator;
    /**
     * dao层生成器
     */
    private CommonGenerator commonGenerator;
    /**
     * 表格
     */
    private List<TableConfiguration> tablesConfiguration = new ArrayList<>();
    /**
     * 视图
     */
    private List<ViewConfiguration> viewsConfiguration = new ArrayList<>();
    /**
     * jdk8
     */
    private boolean isJava8Targeted = true;

    public void validate() throws InvalidConfigurationException {
        List<String> errors = new ArrayList<>();
        if (configFilePath == null || "".equals(configFilePath.trim())) {
            errors.add("configFilePath can not be empty");
        }
        if (commentGenerator == null) {
            errors.add("commentGenerator can not be empty");
        } else {
            commentGenerator.validate(errors);
        }
        if (dataSource == null) {
            errors.add("dataSource can not be empty");
        } else {
            dataSource.validate(errors);
        }
        if (javaTypeResolver == null) {
            errors.add("javaTypeResolver can not be empty");
        } else {
            javaTypeResolver.validate(errors);
        }
        if (serviceGenerator == null) {
            errors.add("serviceGenerator can not be empty");
        } else {
            serviceGenerator.validate(errors);
        }
        if (commonGenerator == null) {
            errors.add("commonGenerator can not be empty");
        } else {
            commonGenerator.validate(errors);
        }

        if (tablesConfiguration == null && viewsConfiguration == null) {
            errors.add("tablesConfiguration or viewsConfiguration can not be empty and has no table");
        } else {
            if (tablesConfiguration != null && tablesConfiguration.size() > 0) {
                for (int i = 0, iLength = tablesConfiguration.size(); i < iLength; i++) {
                    tablesConfiguration.get(i).validate(errors, i);
                }
            }
            if (viewsConfiguration != null && viewsConfiguration.size() > 0) {
                for (int i = 0, iLength = viewsConfiguration.size(); i < iLength; i++) {
                    viewsConfiguration.get(i).validate(errors, i);
                }
            }
        }

        if (errors.size() > 0) {
            throw new InvalidConfigurationException(errors);
        }
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getConfigFilePath() {
        return configFilePath;
    }

    public void setConfigFilePath(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    public CommentGenerator getCommentGenerator() {
        return commentGenerator;
    }

    public void setCommentGenerator(CommentGenerator commentGenerator) {
        this.commentGenerator = commentGenerator;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JavaTypeResolver getJavaTypeResolver() {
        return javaTypeResolver;
    }

    public void setJavaTypeResolver(JavaTypeResolver javaTypeResolver) {
        this.javaTypeResolver = javaTypeResolver;
    }

    public ServiceGenerator getServiceGenerator() {
        return serviceGenerator;
    }

    public void setServiceGenerator(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
    }

    public CommonGenerator getCommonGenerator() {
        return commonGenerator;
    }

    public void setCommonGenerator(CommonGenerator commonGenerator) {
        this.commonGenerator = commonGenerator;
    }

    public List<TableConfiguration> getTablesConfiguration() {
        return tablesConfiguration;
    }

    public void setTablesConfiguration(List<TableConfiguration> tablesConfiguration) {
        this.tablesConfiguration = tablesConfiguration;
    }

    public List<ViewConfiguration> getViewsConfiguration() {
        return viewsConfiguration;
    }

    public void setViewsConfiguration(List<ViewConfiguration> viewsConfiguration) {
        this.viewsConfiguration = viewsConfiguration;
    }

    public boolean isJava8Targeted() {
        return isJava8Targeted;
    }

    public void setJava8Targeted(boolean java8Targeted) {
        isJava8Targeted = java8Targeted;
    }
}
