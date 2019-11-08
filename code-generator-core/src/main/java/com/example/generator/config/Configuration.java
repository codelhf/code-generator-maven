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
     * 数据源
     */
    private JdbcConnection jdbcConnection;
    /**
     * java类型适配器
     */
    private JavaTypeResolver javaTypeResolver;
    /**
     * 注释控制器
     */
    private CommentGenerator commentGenerator;
    /**
     * 模板
     */
    private List<TemplateConfig> templateList = new ArrayList<>();
    /**
     * 表格
     */
    private List<TableConfig> tableList = new ArrayList<>();
    /**
     * 视图
     */
    private List<ViewConfig> viewList = new ArrayList<>();
    /**
     * jdk8
     */
    private boolean isJava8Targeted = true;

    public void validate() throws InvalidConfigurationException {
        List<String> errors = new ArrayList<>();
        if (configFilePath == null || "".equals(configFilePath.trim())) {
            errors.add("configFilePath can not be empty");
        }
        if (jdbcConnection == null) {
            errors.add("jdbcConnection can not be empty");
        } else {
            jdbcConnection.validate(errors);
        }
        if (javaTypeResolver == null) {
            errors.add("javaTypeResolver can not be empty");
        } else {
            javaTypeResolver.validate(errors);
        }
        if (commentGenerator == null) {
            errors.add("commentGenerator can not be empty");
        } else {
            commentGenerator.validate(errors);
        }
        if (templateList == null) {
            errors.add("templateList can not be empty and has no template");
        } else {
            for (int i = 0, iLength = templateList.size(); i < iLength; i++) {
                templateList.get(i).validate(errors, i);
            }
        }

        if (tableList == null && viewList == null) {
            errors.add("tableList or viewList can not be empty and has no table");
        } else {
            if (tableList != null && tableList.size() > 0) {
                for (int i = 0, iLength = tableList.size(); i < iLength; i++) {
                    tableList.get(i).validate(errors, i);
                }
            }
            if (viewList != null && viewList.size() > 0) {
                for (int i = 0, iLength = viewList.size(); i < iLength; i++) {
                    viewList.get(i).validate(errors, i);
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

    public JdbcConnection getJdbcConnection() {
        return jdbcConnection;
    }

    public void setJdbcConnection(JdbcConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public JavaTypeResolver getJavaTypeResolver() {
        return javaTypeResolver;
    }

    public void setJavaTypeResolver(JavaTypeResolver javaTypeResolver) {
        this.javaTypeResolver = javaTypeResolver;
    }

    public CommentGenerator getCommentGenerator() {
        return commentGenerator;
    }

    public void setCommentGenerator(CommentGenerator commentGenerator) {
        this.commentGenerator = commentGenerator;
    }

    public List<TemplateConfig> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<TemplateConfig> templateList) {
        this.templateList = templateList;
    }

    public List<TableConfig> getTableList() {
        return tableList;
    }

    public void setTableList(List<TableConfig> tableList) {
        this.tableList = tableList;
    }

    public List<ViewConfig> getViewList() {
        return viewList;
    }

    public void setViewList(List<ViewConfig> viewList) {
        this.viewList = viewList;
    }

    public boolean isJava8Targeted() {
        return isJava8Targeted;
    }

    public void setJava8Targeted(boolean java8Targeted) {
        isJava8Targeted = java8Targeted;
    }
}
