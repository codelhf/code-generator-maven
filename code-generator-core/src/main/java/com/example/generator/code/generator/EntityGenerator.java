package com.example.generator.code.generator;

import com.example.generator.code.generator.base.BaseGenerator;
import com.example.generator.db.ColumnInfo;
import com.example.generator.util.StringUtil;

import java.sql.Types;
import java.util.List;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:14
 */
public class EntityGenerator extends BaseGenerator {

    /**
     * 生成实体类属性字段（基本数据类型，用于单表关系）
     *
     * @param tableInfo 表结构
     * @return
     */
    public static String generateProperties(List<ColumnInfo> tableInfo) {
        StringBuilder sb = new StringBuilder();
        for (ColumnInfo columnInfo: tableInfo) {
            sb.append("private ").append(columnInfo.getJavaType()).append(" ").append(columnInfo.getPropertyName()).append(";\n    ");
            sb.append("\n    ");
        }
        return sb.toString();
    }

    /**
     * 生成实体类属性字段（引用，用于一对多关系）
     *
     * @param parentClassName 父表类名
     * @param tableInfo       表结构
     * @param foreignKey      外键
     * @return
     */
    public static String generateProperties(String parentClassName, List<ColumnInfo> tableInfo, String foreignKey) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tableInfo.size(); i++) {
            if (tableInfo.get(i).isPrimaryKey() || !tableInfo.get(i).getColumnName().equals(foreignKey)) {
                if (i != 0) {
                    sb.append("    ");
                }
                sb.append("private ").append(tableInfo.get(i).getJavaType()).append(" ").append(tableInfo.get(i).getPropertyName()).append("; \n");
            }
        }
        // 外键为父表实体引用
        sb.append("    ").append("private ").append(parentClassName).append(" ").append(StringUtil.firstToLowerCase(parentClassName)).append("; \n");
        return sb.toString();
    }


    /**
     * 生成实体类属性字段（列表，用于多对多关系）
     *
     * @param parentClassName 父表类名
     * @param tableInfo       表结构
     * @return
     */
    public static String generateProperties(String parentClassName, List<ColumnInfo> tableInfo) {
        StringBuilder sb = new StringBuilder(generateProperties(tableInfo));
        sb.append("    ").append("private List<").append(parentClassName).append(">").append(" ").append(StringUtil.firstToLowerCase(parentClassName)).append("s; \n");
        return sb.toString();
    }


    /**
     * 生成实体类无参构造（无参构造，用于单表、一对多、多对多关系）
     *
     * @param className 类名
     * @return
     */
    public static String generateNoArgsConstructor(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("public ").append(className).append(" () {\n    ");
        sb.append("    super(); \n    ");
        sb.append("}");
        return sb.toString();
    }


    /**
     * 生成实体类全参构造（全参构造，用于单表关系）
     *
     * @param className 类名
     * @param tableInfo 表结构
     * @return
     */
    public static String generateAllArgsConstructor(String className, List<ColumnInfo> tableInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("public ").append(className).append(" (");
        int length = tableInfo.size();
        for (int i = 0; i < length; i++) {
            sb.append(tableInfo.get(i).getJavaType()).append(" ").append(tableInfo.get(i).getPropertyName());
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(") { \n");
        for (int i = 0; i < length; i++) {
            sb.append("        this.").append(tableInfo.get(i).getPropertyName()).append(" = ").append(tableInfo.get(i).getPropertyName()).append("; \n");
        }
        sb.append("    }");
        return sb.toString();
    }


    /**
     * 生成实体类存取方法（用于单表关系）
     *
     * @param tableInfo 表结构
     * @return
     */
    public static String generateMethods(List<ColumnInfo> tableInfo) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tableInfo.size(); i++) {
            if (i != 0) {
                sb.append("    ");
            }
            if (tableInfo.get(i).getType() == Types.BIT || tableInfo.get(i).getType() == Types.TINYINT) {
                sb.append("public ").append(tableInfo.get(i).getJavaType()).append(" is").append(StringUtil.firstToUpperCase(tableInfo.get(i).getPropertyName())).append("(){ return ").append(tableInfo.get(i).getPropertyName()).append(";} \n\n");
            } else {
                sb.append("public ").append(tableInfo.get(i).getJavaType()).append(" get").append(StringUtil.firstToUpperCase(tableInfo.get(i).getPropertyName())).append("(){ return ").append(tableInfo.get(i).getPropertyName()).append(";} \n\n");
            }
            sb.append("    public void set").append(StringUtil.firstToUpperCase(tableInfo.get(i).getPropertyName())).append(" (").append(tableInfo.get(i).getJavaType()).append(" ").append(tableInfo.get(i).getPropertyName()).append(") {this.").append(tableInfo.get(i).getPropertyName()).append(" = ").append(tableInfo.get(i).getPropertyName()).append(";}\n\n");
        }
        return sb.toString();
    }


    /**
     * 生成实体类存取方法（用于一对多关系）
     *
     * @param parentClassName 父表类名
     * @param tableInfo       表结构
     * @param foreignKey      外键
     * @return
     */
    public static String generateMethods(String parentClassName, List<ColumnInfo> tableInfo, String foreignKey) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tableInfo.size(); i++) {
            if (tableInfo.get(i).isPrimaryKey() || !tableInfo.get(i).getColumnName().equals(foreignKey)) {
                if (i != 0) {
                    sb.append("    ");
                }
                if (tableInfo.get(i).getType() == Types.BIT || tableInfo.get(i).getType() == Types.TINYINT) {
                    sb.append("    ").append("public ").append(tableInfo.get(i).getJavaType()).append(" is").append(StringUtil.firstToUpperCase(tableInfo.get(i).getPropertyName())).append("(){ return ").append(tableInfo.get(i).getPropertyName()).append(";} \n");
                } else {
                    sb.append("    ").append("public ").append(tableInfo.get(i).getJavaType()).append(" get").append(StringUtil.firstToUpperCase(tableInfo.get(i).getPropertyName())).append("(){ return ").append(tableInfo.get(i).getPropertyName()).append(";} \n");
                }
                sb.append("public void set").append(StringUtil.firstToUpperCase(tableInfo.get(i).getPropertyName())).append(" (").append(tableInfo.get(i).getJavaType()).append(" ").append(tableInfo.get(i).getPropertyName()).append(") {this.").append(tableInfo.get(i).getPropertyName()).append(" = ").append(tableInfo.get(i).getPropertyName()).append(";} \n");
            }
        }
        // 外键为存取父表实体引用
        sb.append("    ").append("public void set").append(parentClassName).append(" (").append(parentClassName).append(" ").append(StringUtil.firstToLowerCase(parentClassName)).append(") {this.").append(StringUtil.firstToLowerCase(parentClassName)).append(" = ").append(StringUtil.firstToLowerCase(parentClassName)).append(";} \n");
        sb.append("    ").append("public ").append(parentClassName).append(" get").append(parentClassName).append("(){ return this.").append(StringUtil.firstToLowerCase(parentClassName)).append(";} \n");
        return sb.toString();
    }


    /**
     * 生成实体类存取方法（用于多对多关系）
     *
     * @param parentClassName 父表类名
     * @param tableInfo       表结构
     * @return
     */
    public static String generateMethods(String parentClassName, List<ColumnInfo> tableInfo) {
        StringBuilder sb = new StringBuilder(generateMethods(tableInfo));
        sb.append("    ").append("public void set" + parentClassName + "s (List<" + parentClassName + "> " + StringUtil.firstToLowerCase(parentClassName) + "s) { \n this." + StringUtil.firstToLowerCase(parentClassName) + "s = " + StringUtil.firstToLowerCase(parentClassName) + "s;\n} \n");
        sb.append("    ").append("public List<" + parentClassName + "> get" + parentClassName + "s(){ return this." + StringUtil.firstToLowerCase(parentClassName) + "s;} \n");
        return sb.toString();
    }

}
