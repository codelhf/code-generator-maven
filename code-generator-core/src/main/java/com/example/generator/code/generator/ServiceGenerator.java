package com.example.generator.code.generator;

import com.example.generator.code.generator.base.BaseGenerator;
import com.example.generator.config.Configuration;
import com.example.generator.db.ColumnInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:21
 */
public class ServiceGenerator extends BaseGenerator {

    public static String listRemark(String ClassName, Configuration configuration) {
        String title = "list";
        String description = "查询" + ClassName + "列表";
        List<String> params = new ArrayList<>();
        params.add("pageNum");
        params.add("pageSize");
        params.add("params");
        String returns = responseClass + "<PageInfo>";
        return generateFunctionRemark(title, description, params, returns, configuration);
    }

    /**
     * ServerResponse list(Integer pageNum, Integer pageSize, Map<String, String> params);
     */
    public static String list() {
        StringBuilder sb = new StringBuilder();
        sb.append(responseClass).append("<PageInfo> list(Integer pageNum, Integer pageSize, Map<String, String> params);");
        return sb.toString();
    }

    public static String insertRemark(String ClassName, String className, Configuration configuration) {
        String title = "insert";
        String description = "保存" + ClassName + "对象";
        List<String> params = new ArrayList<>();
        params.add(className + "DTO");
        String returns = responseClass + "<String>";
        return generateFunctionRemark(title, description, params, returns, configuration);
    }

    /**
     * ServerResponse<String> insert(${ClassName}DTO, ${className}DTO);
     */
    public static String insert(String ClassName, String className) {
        StringBuilder sb = new StringBuilder();
        sb.append(responseClass).append("<String> insert(").append(ClassName).append("DTO ").append(className).append("DTO);");
        return sb.toString();
    }

    public static String selectRemark(String ClassName, ColumnInfo primaryKeyColumn, Configuration configuration) {
        String title = "select";
        String description = "查询" + ClassName + "对象";
        List<String> params = new ArrayList<>();
        params.add(primaryKeyColumn.getPropertyName());
        String returns = responseClass + "<" + ClassName + "DTO>";
        return generateFunctionRemark(title, description, params, returns, configuration);
    }

    /**
     * ServerResponse<${ClassName}> select(${javaType}, ${primaryKey});
     */
    public static String select(String ClassName, ColumnInfo primaryKeyColumn) {
        String javaType = primaryKeyColumn.getJavaType();
        String propertyName = primaryKeyColumn.getPropertyName();
        StringBuilder sb = new StringBuilder();
        sb.append(responseClass).append("<").append(ClassName).append("DTO> select(").append(javaType).append(" ").append(propertyName).append(");");
        return sb.toString();
    }

    public static String updateRemark(String ClassName, String className, ColumnInfo primaryKeyColumn, Configuration configuration) {
        String title = "update";
        String description = "更新" + ClassName + "对象";
        List<String> params = new ArrayList<>();
        params.add(primaryKeyColumn.getPropertyName());
        params.add(className + "DTO");
        String returns = responseClass + "<String>";
        return generateFunctionRemark(title, description, params, returns, configuration);
    }

    /**
     * ServerResponse<String> update(${javaType}, ${primaryKey}, ${ClassName}DTO, ${className}DTO);
     */
    public static String update(String ClassName, String className, ColumnInfo primaryKeyColumn) {
        String javaType = primaryKeyColumn.getJavaType();
        String propertyName = primaryKeyColumn.getPropertyName();
        StringBuilder sb = new StringBuilder();
        sb.append(responseClass).append("<String> update(").append(javaType).append(" ").append(propertyName).append(", ").append(ClassName).append("DTO ").append(className).append("DTO);");
        return sb.toString();
    }

    public static String deleteRemark(String ClassName, ColumnInfo primaryKeyColumn, Configuration configuration) {
        String title = "delete";
        String description = "批量删除" + ClassName + "对象";
        List<String> params = new ArrayList<>();
        params.add(primaryKeyColumn.getPropertyName() + "s");
        String returns = responseClass + "<String>";
        return generateFunctionRemark(title, description, params, returns, configuration);
    }

    /**
     * ServerResponse<String> select(String, ${primaryKey}s);
     */
    public static String delete(ColumnInfo primaryKeyColumn) {
        String propertyName = primaryKeyColumn.getPropertyName();
        StringBuilder sb = new StringBuilder();
        sb.append(responseClass).append("<String> delete(String ").append(propertyName).append("s);");
        return sb.toString();
    }
}
