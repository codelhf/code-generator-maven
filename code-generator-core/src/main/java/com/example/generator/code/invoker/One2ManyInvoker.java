package com.example.generator.code.invoker;

import com.example.generator.code.invoker.base.BaseBuilder;
import com.example.generator.code.invoker.base.BaseInvoker;
import com.example.generator.code.task.*;
import com.example.generator.config.Configuration;
import com.example.generator.util.StringUtil;

import java.sql.SQLException;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public class One2ManyInvoker extends BaseInvoker {

    @Override
    protected void getTableInfo() throws SQLException {
        this.tableInfo = connectionUtil.getMetaData(tableName);
        this.parentTableInfo = connectionUtil.getMetaData(parentTableName);
    }

    @Override
    protected void initTasks() {
        taskQueue.add(new ControllerTask(className, false, configuration));
        taskQueue.add(new ServiceTask(className,false, configuration));
        taskQueue.add(new ServiceImplTask(className, false, configuration));
        taskQueue.add(new DaoTask(className, false, configuration));
        taskQueue.add(new MapperTask(tableName, className, parentTableName, parentClassName, foreignKey, tableInfo, parentTableInfo, false, configuration));
        taskQueue.add(new EntityTask(className, parentClassName, foreignKey, tableInfo, configuration));
        taskQueue.add(new EntityDtoTask(className, parentClassName, foreignKey, tableInfo, configuration));
    }

    public static class Builder extends BaseBuilder {
        private One2ManyInvoker invoker = new One2ManyInvoker();

        public Builder setConfiguration(Configuration configuration) {
            invoker.setConfiguration(configuration);
            return this;
        }

        public Builder setTableName(String tableName) {
            invoker.setTableName(tableName);
            return this;
        }

        public Builder setClassName(String className) {
            invoker.setClassName(className);
            return this;
        }

        public Builder setParentTableName(String parentTableName) {
            invoker.setParentTableName(parentTableName);
            return this;
        }

        public Builder setParentClassName(String parentClassName) {
            invoker.setParentClassName(parentClassName);
            return this;
        }

        public Builder setForeignKey(String foreignKey) {
            invoker.setForeignKey(foreignKey);
            return this;
        }

        @Override
        public BaseInvoker build() {
            if (!isParametersValid()) {
                return null;
            }
            return invoker;
        }

        @Override
        public void checkBeforeBuild() throws Exception {
            if (StringUtil.isBlank(invoker.getTableName())) {
                throw new Exception("Expect table's name, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getParentTableName())) {
                throw new Exception("Expect parent table's name, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getForeignKey())) {
                throw new Exception("Expect foreign key, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getClassName())) {
                invoker.setClassName(StringUtil.tableName2ClassName(invoker.getTableName()));
            }
            if (StringUtil.isBlank(invoker.getParentClassName())) {
                invoker.setParentClassName(StringUtil.tableName2ClassName(invoker.getParentTableName()));
            }
        }
    }
}
