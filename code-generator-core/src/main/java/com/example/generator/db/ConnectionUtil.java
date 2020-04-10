package com.example.generator.db;

import com.example.generator.config.JdbcConnection;

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

    /**
     * 初始化数据库连接
     */
    public boolean initConnection(JdbcConnection dataSource) {
        try {
            Class.forName(DriverFactory.getDriver(dataSource.getUrl()));
            String url = dataSource.getUrl();
            String username = dataSource.getUsername();
            String password = dataSource.getPassword();
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
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        String primaryKey = null;
        ResultSet primaryKeys = databaseMetaData.getPrimaryKeys(null, null, tableName);
        if (primaryKeys.next()) {
            primaryKey = primaryKeys.getObject(4).toString();
        }
        List<ColumnInfo> columnInfoList = new ArrayList<>();
        ResultSet columns = databaseMetaData.getColumns(null, getSchema(connection), tableName, "%");
        while (columns.next()) {
            String columnName = columns.getString("COLUMN_NAME");
            int columnType = columns.getInt("DATA_TYPE");
            String columnComment = columns.getString("REMARKS");
            ColumnInfo columnInfo;
            if (columnName.equals(primaryKey)) {
                columnInfo = new ColumnInfo(columnName, columnType, true, columnComment);
            } else {
                columnInfo = new ColumnInfo(columnName, columnType, false, columnComment);
            }
            columnInfoList.add(columnInfo);
        }
        primaryKeys.close();
        columns.close();
        return columnInfoList;
    }

    //其他数据库不需要这个方法 oracle和db2需要
    private static String getSchema(Connection conn) throws SQLException {
        String driverName = conn.getMetaData().getDriverName();
        if (!"oracle.jdbc.driver.OracleDriver".equals(driverName) && !"com.ibm.db2.jcc.DB2Driver".equals(driverName)) {
            return null;
        }
        String schema = conn.getMetaData().getUserName();
        if ((schema == null) || (schema.length() == 0)) {
            throw new SQLException("ORACLE数据库模式不允许为空");
        }
        return schema.toUpperCase();
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
