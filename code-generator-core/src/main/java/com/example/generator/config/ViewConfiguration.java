package com.example.generator.config;

import com.example.generator.util.StringUtil;
import com.example.generator.util.StringUtil;

import java.util.List;

/**
 * @Description: 视图配置
 * @Auther: liuhf
 * @CreateTime: 2019/2/28 9:37
 */
public class ViewConfiguration {

    private String viewName;

    private String domainName;

    private List<ColumnOverride> columnOverrides;

    public void validate(List<String> errors, Integer listPosition) {
        if (StringUtil.isBlank(viewName)) {
            errors.add("Missing view name in table configuration at index {" + listPosition +"}");
        }
        if (StringUtil.isBlank(domainName)) {
            this.domainName = StringUtil.columnName2PropertyName(viewName);
        }
        if (columnOverrides != null && columnOverrides.size() > 0) {
            for (ColumnOverride columnOverride: columnOverrides) {
                columnOverride.validate(errors, viewName);
            }
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public List<ColumnOverride> getColumnOverrides() {
        return columnOverrides;
    }

    public void setColumnOverrides(List<ColumnOverride> columnOverrides) {
        this.columnOverrides = columnOverrides;
    }
}
