package com.example.generator.code.generator;

import com.example.generator.code.generator.base.BaseGenerator;
import com.example.generator.db.ColumnInfo;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:18
 */
public class DaoGenerator extends BaseGenerator {

    public static String deleteByPrimaryKey(ColumnInfo primaryKeyColumn) {
        StringBuilder sb = new StringBuilder();
        sb.append("int deleteByPrimaryKey(").append(primaryKeyColumn.getJavaType()).append(" ").append(primaryKeyColumn.getPropertyName()).append(");");
        return sb.toString();
    }

    public static String insert(String ClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append("int insert(").append(ClassName).append(" record);");
        return sb.toString();
    }

    public static String insertSelective(String ClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append("int insertSelective(").append(ClassName).append(" record);");
        return sb.toString();
    }

    public static String selectByPrimaryKey(String ClassName, ColumnInfo primaryKeyColumn) {
        StringBuilder sb = new StringBuilder();
        sb.append(ClassName).append(" selectByPrimaryKey(").append(primaryKeyColumn.getJavaType()).append(" ").append(primaryKeyColumn.getPropertyName()).append(");");
        return sb.toString();
    }

    public static String updateByPrimaryKeySelective(String ClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append("int updateByPrimaryKeySelective(").append(ClassName).append(" record);");
        return sb.toString();
    }

    public static String updateByPrimaryKey(String ClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append("int updateByPrimaryKey(").append(ClassName).append(" record);");
        return sb.toString();
    }

    public static String selectPageList(String ClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append("List<").append(ClassName).append("> selectPageList(").append(ClassName).append(" example);");
        return sb.toString();
    }

    public static String deleteByIdList(ColumnInfo primaryKeyColumn) {
        StringBuilder sb = new StringBuilder();
        String propertyName = primaryKeyColumn.getPropertyName();
        sb.append("int deleteByIdList(@Param(\"").append(propertyName).append("List\") List<String> ").append(propertyName).append("List);");
        return sb.toString();
    }
}
