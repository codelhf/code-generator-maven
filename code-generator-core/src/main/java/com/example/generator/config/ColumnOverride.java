package com.example.generator.config;

import com.example.generator.util.StringUtil;
import com.example.generator.util.StringUtil;

import java.util.List;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/12 23:03
 */
public class ColumnOverride {

    private String columnName;

    private String javaProperty;

    private String jdbcType;

    private String javaType;

    private String typeHandler;

    private boolean isColumnNameDelimited;

    /**
     * If true, the columnName is a GENERATED ALWAYS columnName which means
     * that it should not be used in insert or update statements.
     */
    private boolean isGeneratedAlways;

    public ColumnOverride(String columnName) {
        this.columnName = columnName;
        this.isColumnNameDelimited = columnName.contains(" ");
    }

    public void validate(List<String> errors, String tableName) {
        if (StringUtil.isBlank(columnName)) {
            errors.add(tableName +".columnOverride.columnName can not be empty");
        }
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getJavaProperty() {
        return javaProperty;
    }

    public void setJavaProperty(String javaProperty) {
        this.javaProperty = javaProperty;
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

    public String getTypeHandler() {
        return typeHandler;
    }

    public void setTypeHandler(String typeHandler) {
        this.typeHandler = typeHandler;
    }

    public boolean isColumnNameDelimited() {
        return isColumnNameDelimited;
    }

    public void setColumnNameDelimited(boolean columnNameDelimited) {
        isColumnNameDelimited = columnNameDelimited;
    }

    public boolean isGeneratedAlways() {
        return isGeneratedAlways;
    }

    public void setGeneratedAlways(boolean generatedAlways) {
        isGeneratedAlways = generatedAlways;
    }
}
