package com.example.generator.code.invoker.base;

import com.example.generator.config.Configuration;
import com.example.generator.db.ColumnInfo;
import com.example.generator.db.ConnectionUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public abstract class BaseInvoker implements Invoker {
    protected String tableName;
    protected String className;
    protected String parentTableName;
    protected String parentClassName;
    protected String foreignKey;
    protected String parentForeignKey;
    protected String relationalTableName;
    protected List<ColumnInfo> tableInfo;
    protected List<ColumnInfo> parentTableInfo;
    protected Configuration configuration;
    protected ConnectionUtil connectionUtil = new ConnectionUtil();

    private void initDataSource() throws Exception {
        if (!connectionUtil.initConnection(configuration)) {
            throw new Exception("Failed to connect to database at url:" + configuration.getDataSource().getUrl());
        }
        getTableInfo();
        connectionUtil.close();
    }

    protected abstract void getTableInfo() throws SQLException;

    protected abstract void initTasks() throws IOException, TemplateException;

    @Override
    public void execute() {
        try {
            initDataSource();
            initTasks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getParentTableName() {
        return parentTableName;
    }

    public void setParentTableName(String parentTableName) {
        this.parentTableName = parentTableName;
    }

    public String getParentClassName() {
        return parentClassName;
    }

    public void setParentClassName(String parentClassName) {
        this.parentClassName = parentClassName;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    public String getParentForeignKey() {
        return parentForeignKey;
    }

    public void setParentForeignKey(String parentForeignKey) {
        this.parentForeignKey = parentForeignKey;
    }

    public String getRelationalTableName() {
        return relationalTableName;
    }

    public void setRelationalTableName(String relationalTableName) {
        this.relationalTableName = relationalTableName;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
