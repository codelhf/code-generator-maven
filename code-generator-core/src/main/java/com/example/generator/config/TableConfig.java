package com.example.generator.config;

import com.example.generator.util.StringUtil;

import java.util.List;

/**
 * @Description: 表格配置
 * @Auther: liuhf
 * @CreateTime: 2019/2/28 9:37
 */
public class TableConfig {

    private String tableName;

    private String domainName;

    private GeneratedKey generatedKey;

    private List<ColumnOverride> columnOverrides;

    public void validate(List<String> errors, Integer listPosition) {
        if (StringUtil.isBlank(tableName)) {
            errors.add("Missing table name in table configuration at index {" + listPosition +"}");
        }
        if (StringUtil.isBlank(domainName)) {
            this.domainName = StringUtil.columnName2PropertyName(tableName);
        }
        if (generatedKey != null) {
            generatedKey.validate(errors, tableName);
        }
        if (columnOverrides != null && columnOverrides.size() > 0) {
            for (ColumnOverride columnOverride: columnOverrides) {
                columnOverride.validate(errors, tableName);
            }
        }
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public GeneratedKey getGeneratedKey() {
        return generatedKey;
    }

    public void setGeneratedKey(GeneratedKey generatedKey) {
        this.generatedKey = generatedKey;
    }

    public List<ColumnOverride> getColumnOverrides() {
        return columnOverrides;
    }

    public void setColumnOverrides(List<ColumnOverride> columnOverrides) {
        this.columnOverrides = columnOverrides;
    }
}
