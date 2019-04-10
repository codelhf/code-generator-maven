package com.example.generator.code;

import com.example.generator.config.Configuration;
import com.example.generator.db.ColumnInfo;

import java.util.List;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/4/10 23:05
 */
public class BaseInfo {
    protected boolean isView;
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

    public boolean isView() {
        return isView;
    }

    public void setView(boolean view) {
        isView = view;
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
