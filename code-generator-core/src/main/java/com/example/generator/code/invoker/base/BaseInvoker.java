package com.example.generator.code.invoker.base;

import com.example.generator.code.task.base.BaseTask;
import com.example.generator.code.task.base.TaskQueue;
import com.example.generator.config.util.ConfigUtil;
import com.example.generator.db.ColumnInfo;
import com.example.generator.db.ConnectionUtil;
import com.example.generator.code.task.base.BaseTask;
import com.example.generator.code.task.base.TaskQueue;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    protected ConnectionUtil connectionUtil = new ConnectionUtil();
    protected TaskQueue<BaseTask> taskQueue = new TaskQueue();
    private ExecutorService executorPool = Executors.newFixedThreadPool(6);

    private void initDataSource() throws Exception {
        if (!connectionUtil.initConnection()) {
            throw new Exception("Failed to connect to database at url:" + ConfigUtil.getConfiguration().getDataSource().getUrl());
        }
        getTableInfo();
        connectionUtil.close();
    }

    protected abstract void getTableInfo() throws SQLException;

    protected abstract void initTasks();

    @Override
    public void execute() {
        try {
            initDataSource();
            initTasks();
            while (!taskQueue.isEmpty()) {
                BaseTask task = taskQueue.poll();
                executorPool.execute(() -> {
                    try {
                        task.run();
                    } catch (IOException | TemplateException e) {
                        e.printStackTrace();
                    }
                });
            }
            executorPool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setParentTableName(String parentTableName) {
        this.parentTableName = parentTableName;
    }

    public void setParentClassName(String parentClassName) {
        this.parentClassName = parentClassName;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    public void setRelationalTableName(String relationalTableName) {
        this.relationalTableName = relationalTableName;
    }

    public void setParentForeignKey(String parentForeignKey) {
        this.parentForeignKey = parentForeignKey;
    }

    public String getTableName() {
        return tableName;
    }

    public String getClassName() {
        return className;
    }

    public String getParentTableName() {
        return parentTableName;
    }

    public String getParentClassName() {
        return parentClassName;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public String getRelationalTableName() {
        return relationalTableName;
    }

    public String getParentForeignKey() {
        return parentForeignKey;
    }
}
