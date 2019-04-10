package com.example.generator.db;

import com.example.generator.config.Configuration;
import com.example.generator.config.util.ConfigUtil;
import com.example.generator.config.util.ConfigUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public class ConnectionUtil {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private Configuration configuration;

    public ConnectionUtil(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 初始化数据库连接
     */
    public boolean initConnection() {
        try {
            Class.forName(DriverFactory.getDriver(configuration.getDataSource().getUrl()));
            String url = configuration.getDataSource().getUrl();
            String username = configuration.getDataSource().getUsername();
            String password = configuration.getDataSource().getPassword();
            connection = DriverManager.getConnection(url, username, password);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取表结构数据
     *
     * @param tableName 表名
     * @return 包含表结构数据的列表
     */
    public List<ColumnInfo> getMetaData(String tableName) throws SQLException {
        ResultSet tempResultSet = connection.getMetaData().getPrimaryKeys(null, null, tableName);
        String primaryKey = null;
        if (tempResultSet.next()) {
            primaryKey = tempResultSet.getObject(4).toString();
        }
        List<ColumnInfo> columnInfoList = new ArrayList<>();
        statement = connection.createStatement();
        String sql = "SELECT * FROM " + tableName + " WHERE 1 != 1";
        resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            ColumnInfo info;
            if (metaData.getColumnName(i).equals(primaryKey)) {
                info = new ColumnInfo(metaData.getColumnName(i), metaData.getColumnType(i), true);
            } else {
                info = new ColumnInfo(metaData.getColumnName(i), metaData.getColumnType(i), false);
            }
            columnInfoList.add(info);
        }
        statement.close();
        resultSet.close();
        return columnInfoList;
    }

    /**
     * 关闭数据库链接
     */
    public void close() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
