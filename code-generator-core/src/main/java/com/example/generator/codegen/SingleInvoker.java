package com.example.generator.codegen;

import com.example.generator.config.ColumnOverride;
import com.example.generator.config.Configuration;
import com.example.generator.config.GeneratedKey;
import com.example.generator.db.ColumnInfo;
import com.example.generator.db.ConnectionUtil;
import com.example.generator.util.StringUtil;
import org.apache.velocity.Template;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/5/6 23:29
 */
public class SingleInvoker {
    //表名
    private String tableName;
    //类名
    private String className;
    //表中所有的列
    private List<ColumnInfo> tableInfo;
    //是否为视图, 针对单表
    private boolean isView;
    //指定列生成主键
    private GeneratedKey generatedKey;
    //重写列集合
    private List<ColumnOverride> columnOverrideList;

    private Configuration configuration;
    private ConnectionUtil connectionUtil = new ConnectionUtil();

    private void getTableInfo() throws SQLException {
        if (!connectionUtil.initConnection(configuration)) {
            throw new RuntimeException("Failed to connect to database at url:" + configuration.getDataSource().getUrl());
        }
        List<ColumnInfo> columnInfoList = connectionUtil.getMetaData(tableName);
        //修改用户自定义配置
        for (ColumnInfo columnInfo: columnInfoList) {
            String column = columnInfo.getColumnName();
            if (generatedKey != null && column.equals(generatedKey.getColumn())) {
                columnInfo.setPrimaryKey(true);
            }
            if (columnOverrideList != null) {
                for (ColumnOverride columnOverride: columnOverrideList) {
                    if (column.equals(columnOverride.getColumnName())) {
                        columnInfo.setJdbcType(columnOverride.getJdbcType());
//                        columnInfo.setJavaType(TypeUtil.jdbcTypeToJavaType(columnOverride.getJdbcType()));
                        break;
                    }
                }
            }
        }
        this.tableInfo = columnInfoList;
        connectionUtil.close();
    }

    public void execute() throws IOException, SQLException {
        this.getTableInfo();
        Map<String, Object> data = new HashMap<>();
        data.put("ClassName", className);
        data.put("className", StringUtil.firstToLowerCase(className));
        data.put("pkColumn", getPrimaryKeyColumnInfo(tableInfo));
        data.put("basePackageName", configuration.getCommentGenerator().getBasePackageName());
        data.put("ResponseClassName", BaseGenerator.responseClass);

        List<Template> templateList = new ArrayList<>();

        for (Template template: templateList) {
            FileUtil.generateToCode("", "", template, data, false);
        }
    }

    private static ColumnInfo getPrimaryKeyColumnInfo(List<ColumnInfo> list) {
        for (ColumnInfo columnInfo : list) {
            if (columnInfo.isPrimaryKey()) {
                return columnInfo;
            }
        }
        //设置默认主键, 并非表中真实存在的
        return new ColumnInfo("id", 1, true);
    }

    public static class Builder {
        SingleInvoker invoker = new SingleInvoker();
        public Builder configuration(Configuration configuration) {
            invoker.setConfiguration(configuration);
            return this;
        }

        public Builder tableName(String tableName) {
            invoker.setTableName(tableName);
            return this;
        }

        public Builder className(String className) {
            invoker.setClassName(className);
            return this;
        }

        public Builder isView(boolean isView) {
            invoker.setView(isView);
            return this;
        }

        public Builder generatedKey(GeneratedKey generatedKey) {
            invoker.setGeneratedKey(generatedKey);
            return this;
        }

        public Builder columnOverrideList(List<ColumnOverride> columnOverrideList) {
            invoker.setColumnOverrideList(columnOverrideList);
            return this;
        }

        public SingleInvoker build() {
            try {
                checkBeforeBuild();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return invoker;
        }

        void checkBeforeBuild() {
            if (invoker.getConfiguration() == null) {
                throw new RuntimeException("configuration can not be null");
            }
            if (StringUtil.isBlank(invoker.getTableName())) {
                throw new RuntimeException("Expect table's name, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getClassName())) {
                invoker.setClassName(StringUtil.tableName2ClassName(invoker.getTableName()));
            }
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

    public void setTableInfo(List<ColumnInfo> tableInfo) {
        this.tableInfo = tableInfo;
    }

    public boolean isView() {
        return isView;
    }

    public void setView(boolean view) {
        isView = view;
    }

    public GeneratedKey getGeneratedKey() {
        return generatedKey;
    }

    public void setGeneratedKey(GeneratedKey generatedKey) {
        this.generatedKey = generatedKey;
    }

    public List<ColumnOverride> getColumnOverrideList() {
        return columnOverrideList;
    }

    public void setColumnOverrideList(List<ColumnOverride> columnOverrideList) {
        this.columnOverrideList = columnOverrideList;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
