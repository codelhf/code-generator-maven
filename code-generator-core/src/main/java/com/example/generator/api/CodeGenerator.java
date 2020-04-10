package com.example.generator.api;

import com.example.generator.codegen.SingleInvoker;
import com.example.generator.config.*;
import com.example.generator.db.ConnectionUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/6 21:25
 */
public class CodeGenerator {

    /** The configuration. */
    private Configuration configuration;

    public CodeGenerator(Configuration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException("Configuration is required");
        } else {
            this.configuration = configuration;
        }
    }

    public void generate() {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        if (!connectionUtil.initConnection(configuration.getJdbcConnection())) {
            throw new RuntimeException("Failed to connect to database at url:" + configuration.getJdbcConnection().getUrl());
        }

        List<TableConfig> tableConfigurationList = configuration.getTableList();
        if (tableConfigurationList != null) {
            for (TableConfig tableConfiguration: tableConfigurationList) {
                String tableName = tableConfiguration.getTableName();
                String domainName = tableConfiguration.getDomainName();
                List<ColumnOverride> columnOverrideList = tableConfiguration.getColumnOverrides();
                GeneratedKey generatedKey = tableConfiguration.getGeneratedKey();
                single(tableName, domainName, columnOverrideList, generatedKey, false, configuration, connectionUtil);
            }
        }

        List<ViewConfig> viewConfigurationList = configuration.getViewList();
        if (viewConfigurationList != null) {
            for (ViewConfig viewConfiguration: viewConfigurationList) {
                String viewName = viewConfiguration.getViewName();
                String domainName = viewConfiguration.getDomainName();
                List<ColumnOverride> columnOverrideList = viewConfiguration.getColumnOverrides();
                single(viewName, domainName, columnOverrideList, null, true, configuration, connectionUtil);
            }
        }
        connectionUtil.close();
    }

    private static void single(String tableName, String className, List<ColumnOverride> columnOverrideList,
                               GeneratedKey generatedKey, boolean isView, Configuration configuration, ConnectionUtil connectionUtil) {
        SingleInvoker invoker = new SingleInvoker();
        invoker.setTableName(tableName);
        invoker.setClassName(className);
        invoker.setColumnOverrideList(columnOverrideList);
        invoker.setGeneratedKey(generatedKey);
        invoker.setView(isView);
        invoker.setConfiguration(configuration);
        invoker.setConnectionUtil(connectionUtil);
        try {
            invoker.execute();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
