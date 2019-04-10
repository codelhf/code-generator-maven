package com.example.generator.code.task.base;

import com.example.generator.config.Configuration;
import com.example.generator.config.util.ConfigUtil;
import com.example.generator.db.ColumnInfo;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public abstract class BaseTask implements Serializable {
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
    protected Configuration configuration = ConfigUtil.getConfiguration();

    /**
     * Controller、Service、ServiceImpl、Dao
     *
     * @param className
     */
    public BaseTask(String className, boolean isView) {
        this.className = className;
        this.isView = isView;
    }

    /**
     * model、dto
     *
     * @param className
     * @param parentClassName
     * @param foreignKey
     * @param tableInfo
     */
    public BaseTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfo) {
        this.className = className;
        this.parentClassName = parentClassName;
        this.foreignKey = foreignKey;
        this.parentForeignKey = parentForeignKey;
        this.tableInfo = tableInfo;
    }


    /**
     * Mapper
     *
     * @param tableName
     * @param className
     * @param parentTableName
     * @param parentClassName
     * @param foreignKey
     * @param parentForeignKey
     * @param tableInfo
     * @param parentTableInfo
     */
    public BaseTask(String tableName, String className,
                    String parentTableName, String parentClassName,
                    String relationalTableName,
                    String foreignKey, String parentForeignKey,
                    List<ColumnInfo> tableInfo,
                    List<ColumnInfo> parentTableInfo,
                    boolean isView) {
        this.tableName = tableName;
        this.className = className;
        this.parentTableName = parentTableName;
        this.parentClassName = parentClassName;
        this.relationalTableName = relationalTableName;
        this.foreignKey = foreignKey;
        this.parentForeignKey = parentForeignKey;
        this.tableInfo = tableInfo;
        this.parentTableInfo = parentTableInfo;
        this.isView = isView;
    }

    public abstract void run() throws IOException, TemplateException;

    protected static ColumnInfo getPrimaryKeyColumnInfo(List<ColumnInfo> list) {
        for (ColumnInfo columnInfo : list) {
            if (columnInfo.isPrimaryKey()) {
                return columnInfo;
            }
        }
        //设置默认主键, 并非表中真实存在的
        return new ColumnInfo("id", 1, true);
    }

}
