package com.example.generator.code.generator;

import com.example.generator.code.generator.base.BaseGenerator;
import com.example.generator.db.ColumnInfo;

import java.util.List;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public class MapperGenerator extends BaseGenerator {

    public static String baseResultMap(String entityPackageName, String className,
                                       List<ColumnInfo> tableInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("<resultMap id=\"BaseResultMap\" type=\"").append(entityPackageName).append(".").append(className).append("\">\n    ");
        for (ColumnInfo columnInfo: tableInfo) {
            if (columnInfo.isPrimaryKey()) {
                sb.append("    <id ");
            } else {
                sb.append("    <result ");
            }
            sb.append("column=\"").append(columnInfo.getColumnName())
                    .append("\" jdbcType=\"").append(columnInfo.getJdbcType())
                    .append("\" property=\"").append(columnInfo.getPropertyName())
                    .append("\"/>\n    ");
        }
        sb.append("</resultMap>");
        return sb.toString();
    }

    public static String baseColumnList(List<ColumnInfo> tableInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("<sql id=\"Base_Column_List\">\n    ");
        sb.append("    ");
        StringBuilder columns = new StringBuilder();
        for (ColumnInfo columnInfo: tableInfo) {
            columns.append(columnInfo.getColumnName()).append(", ");
        }
        String columnList = columns.substring(0, columns.lastIndexOf(","));
        sb.append(columnList).append("\n    </sql>");
        return sb.toString();
    }

    public static String selectByPrimaryKey(String tableName, ColumnInfo primaryKeyColumn) {
        StringBuilder sb = new StringBuilder();
        sb.append("<select id=\"selectByPrimaryKey\" parameterType=\"").append(primaryKeyColumn.getJavaType()).append("\" resultMap=\"BaseResultMap\">\n    ");
        sb.append("    select <include refid=\"Base_Column_List\"/>\n    ");
        sb.append("    from ").append(tableName).append("\n    ");
        sb.append("    where ").append(primaryKeyColumn.getColumnName()).append(" = #{").append(primaryKeyColumn.getPropertyName()).append("}\n    ");
        sb.append("</select>");
        return sb.toString();
    }

    public static String deleteByPrimaryKey(String tableName, ColumnInfo primaryKeyColumn) {
        StringBuilder sb = new StringBuilder();
        sb.append("<delete id=\"deleteByPrimaryKey\" parameterType=\"").append(primaryKeyColumn.getJavaType()).append("\">\n    ");
        sb.append("    delete from ").append(tableName).append("\n    ");
        sb.append("    where ").append(primaryKeyColumn.getColumnName()).append(" = #{").append(primaryKeyColumn.getPropertyName()).append("}\n    ");
        sb.append("</delete>");
        return sb.toString();
    }

    public static String insert(String entityPackageName, String className, String tableName, List<ColumnInfo> tableInfo) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (ColumnInfo columnInfo: tableInfo) {
            sb1.append(columnInfo.getColumnName()).append(", ");
            sb2.append("#{").append(columnInfo.getPropertyName()).append(", jdbcType=").append(columnInfo.getJdbcType()).append("}, ");
        }
        String columns = sb1.substring(0, sb1.lastIndexOf(","));
        String values = sb2.substring(0, sb2.lastIndexOf(","));
        sb.append("<insert id=\"insert\" parameterType=\"").append(entityPackageName).append(".").append(className).append("\">\n    ");
        sb.append("    insert into ").append(tableName).append("\n        (");
        sb.append(columns);
        sb.append(")\n    ");
        sb.append("    values\n        (");
        sb.append(values);
        sb.append(")\n    ");
        sb.append("</insert>");
        return sb.toString();
    }

    public static String insertSelective(String entityPackageName, String className, String tableName, List<ColumnInfo> tableInfo) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (ColumnInfo columnInfo: tableInfo) {
            sb1.append("        <if test=\"").append(columnInfo.getPropertyName()).append(" != null\">\n    ");
            sb1.append("            ").append(columnInfo.getColumnName()).append(",\n    ");
            sb1.append("        </if>\n    ");
            sb2.append("        <if test=\"").append(columnInfo.getPropertyName()).append(" != null\">\n    ");
            sb2.append("            #{").append(columnInfo.getPropertyName()).append(", jdbcType=").append(columnInfo.getJdbcType()).append("},\n    ");
            sb2.append("        </if>\n    ");
        }
        sb.append("<insert id=\"insertSelective\" parameterType=\"").append(entityPackageName).append(".").append(className).append("\">\n    ");
        sb.append("    insert into ").append(tableName).append("\n    ");
        sb.append("    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n    ");
        sb.append(sb1);
        sb.append("    </trim>\n    ");
        sb.append("    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n    ");
        sb.append(sb2);
        sb.append("    </trim>\n    ");
        sb.append("</insert>");
        return sb.toString();
    }

    public static String updateByPrimaryKeySelective(String entityPackageName, String className, String tableName,
                                                     List<ColumnInfo> tableInfo, ColumnInfo primaryKeyColumn) {
        StringBuilder sb = new StringBuilder();
        sb.append("<update id=\"updateByPrimaryKeySelective\" parameterType=\"").append(entityPackageName).append(".").append(className).append("\">\n    ");
        sb.append("    update ").append(tableName).append("\n    ");
        sb.append("    <set>\n    ");
        for (ColumnInfo columnInfo: tableInfo) {
            if (!columnInfo.isPrimaryKey()) {
                sb.append("        <if test=\"").append(columnInfo.getPropertyName()).append(" != null\">\n    ");
                sb.append("            ").append(columnInfo.getColumnName()).append(" = #{").append(columnInfo.getPropertyName()).append(", jdbcType=").append(columnInfo.getJdbcType()).append("},\n    ");
                sb.append("        </if>\n    ");
            }
        }
        sb.append("    </set>\n    ");
        sb.append("    where ").append(primaryKeyColumn.getColumnName()).append(" = #{").append(primaryKeyColumn.getPropertyName()).append("}\n    ");
        sb.append("</update>");
        return sb.toString();
    }

    public static String updateByPrimaryKey(String entityPackageName, String className, String tableName,
                                            List<ColumnInfo> tableInfo, ColumnInfo primaryKeyColumn) {
        StringBuilder sb = new StringBuilder();
        sb.append("<update id=\"updateByPrimaryKey\" parameterType=\"").append(entityPackageName).append(".").append(className).append("\">\n    ");
        sb.append("    update ").append(tableName).append("\n    ");
        sb.append("    set\n    ");
        StringBuilder sets = new StringBuilder();
        for (ColumnInfo columnInfo: tableInfo) {
            if (!columnInfo.isPrimaryKey()) {
                sets.append("        ").append(columnInfo.getColumnName()).append(" = #{").append(columnInfo.getPropertyName()).append(", jdbcType=").append(columnInfo.getJdbcType()).append("},\n    ");
            }
        }
        String set = sets.substring(0, sets.lastIndexOf(","));
        sb.append(set).append("\n        where ").append(primaryKeyColumn.getColumnName()).append(" = #{").append(primaryKeyColumn.getPropertyName()).append("}\n    ");
        sb.append("</update>");
        return sb.toString();
    }

    public static String selectPageList(String entityPackageName, String className, String tableName, List<ColumnInfo> tableInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("<select id=\"selectPageList\" parameterType=\"").append(entityPackageName).append(".").append(className).append("\" resultMap=\"BaseResultMap\">\n    ");
        sb.append("    select <include refid=\"Base_Column_List\"/>\n    ");
        sb.append("    from ").append(tableName).append("\n    ");
        sb.append("    <where>\n    ");
        for (ColumnInfo columnInfo: tableInfo) {
            sb.append("        <if test=\"").append(columnInfo.getPropertyName()).append(" != null\">\n    ");
            sb.append("            and ").append(columnInfo.getColumnName()).append(" like #{").append(columnInfo.getPropertyName()).append("}\n    ");
            sb.append("        </if>\n    ");
        }
        sb.append("    </where>\n    ");
        sb.append("</select>");
        return sb.toString();
    }

    public static String deleteByIdList(String tableName, ColumnInfo primaryKeyColumn) {
        StringBuilder sb = new StringBuilder();
        String columnName = primaryKeyColumn.getColumnName();
        String propertyName = primaryKeyColumn.getPropertyName();
        sb.append("<delete id=\"deleteByIdList\" parameterType=\"map\">\n    ");
        sb.append("    delete from ").append(tableName).append("\n    ");
        sb.append("    where ").append(columnName).append(" in\n    ");
        sb.append("    <foreach collection=\"").append(propertyName).append("List\" index=\"index\" item=\"").append(propertyName).append("\" open=\"(\" separator=\",\" close=\")\" >\n    ");
        sb.append("        #{").append(propertyName).append("}\n    ");
        sb.append("    </foreach>\n    ");
        sb.append("</delete>");
        return sb.toString();
    }
}
