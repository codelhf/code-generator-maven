package com.example.generator.db;

import com.example.generator.util.StringUtil;
import com.example.generator.util.StringUtil;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public class ColumnInfo implements Serializable {
    private String columnName; // 列名
    private String propertyName; // 属性名
    private int type; // 类型代码
    private String jdbcType; //jdbcType
    private String javaType; //javaType;
    private boolean isPrimaryKey; // 是否主键

    public ColumnInfo() { }

    public ColumnInfo(String columnName, int type, boolean isPrimaryKey) {
        this.columnName = columnName;
        this.propertyName = StringUtil.columnName2PropertyName(columnName);
        this.type = type;
        this.jdbcType = TypeUtil.sqlTypeToJdbcType(type);
        this.javaType = TypeUtil.sqlTypeToJavaType(type);
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }
}
