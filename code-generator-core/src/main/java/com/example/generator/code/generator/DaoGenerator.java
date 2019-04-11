package com.example.generator.code.generator;

import com.example.generator.code.generator.base.BaseGenerator;
import com.example.generator.db.ColumnInfo;

import java.util.List;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:18
 */
public class DaoGenerator extends BaseGenerator {

    public static String selectAllListByKeyword(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("List<").append(className).append("> selectAllListByKeyword()");
        return sb.toString();
    }

    public static String selectPageListByKeyword(String className, List<String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("List<").append(className).append("> selectPageListByKeyword(");
        int length = params.size();
        for (int i = 0; i < length; i++) {
            sb.append("@param(\"").append(params.get(i)).append("\") ").append(params.get(i));
            if (i != length -1) {
                sb.append(",");
            }
        }
        sb.append(");");
        return sb.toString();
    }

    public static String selectByPrimaryKey(String className, List<ColumnInfo> tableInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(className).append(" selectByPrimaryKey(");
        for (ColumnInfo columnInfo : tableInfo) {
            if (columnInfo.isPrimaryKey()) {
                sb.append(columnInfo.getJavaType()).append(" ").append(columnInfo.getColumnName());
            }
        }
        sb.append(");");
        return sb.toString();
    }

    public static String deleteByPrimaryKey(List<ColumnInfo> tableInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("int deleteByPrimaryKey(");
        for (ColumnInfo columnInfo : tableInfo) {
            if (columnInfo.isPrimaryKey()) {
                sb.append(columnInfo.getJavaType()).append(" ").append(columnInfo.getColumnName());
            }
        }
        sb.append(");");
        return sb.toString();
    }

    public static String insert(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("int insert(").append(className).append("record);");
        return sb.toString();
    }

    public static String insertSelective(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("int insertSelective(").append(className).append("record);");
        return sb.toString();
    }

    public static String updateByPrimaryKeySelective(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("int updateByPrimaryKeySelective(").append(className).append("record);");
        return sb.toString();
    }

    public static String updateByPrimaryKey(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("int updateByPrimaryKey(").append(className).append("record);");
        return sb.toString();
    }
}
