package com.example.generator.db;

import java.sql.Types;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public class TypeUtil {

    /**
     * 将数据库数据类型转换为Java数据类型
     *
     * @param sqlType
     * @return
     */
    public static String sqlTypeToJavaType(int sqlType) {
        StringBuilder sb = new StringBuilder();
        switch (sqlType) {
            case Types.BIT:
            case Types.BOOLEAN:
                sb.append("boolean");
                break;
            case Types.TINYINT:
                sb.append("byte");
                break;
            case Types.SMALLINT:
                sb.append("short");
                break;
            case Types.INTEGER:
                sb.append("int");
                break;
            case Types.BIGINT:
                sb.append("long");
                break;
            case Types.REAL:
                sb.append("float");
                break;
            case Types.FLOAT:
            case Types.DOUBLE:
                sb.append("double");
                break;
            case Types.DECIMAL:
            case Types.NUMERIC:
                sb.append("BigDecimal");
                break;
            case Types.VARCHAR:
            case Types.CHAR:
            case Types.NCHAR:
            case Types.NVARCHAR:
            case Types.LONGVARCHAR:
            case Types.LONGNVARCHAR:
                sb.append("String");
                break;
            case Types.DATE:
                sb.append("Date");
                break;
            case Types.TIME:
                sb.append("Time");
                break;
            case Types.TIMESTAMP:
                sb.append("Date");
                break;
            case Types.NCLOB:
            case Types.CLOB:
            case Types.BLOB:
            case Types.BINARY:
            case Types.VARBINARY:
            case Types.LONGVARBINARY:
                sb.append("byte[]");
                break;
            case Types.NULL:
            case Types.OTHER:
            case Types.JAVA_OBJECT:
                sb.append("object");
                break;
            default:
                sb.append("object");

        }
        return sb.toString();
    }

    /**
     * 将数据库类型的数字转换为大写字母
     *
     * @param sqlType
     * @return
     */
    public static String sqlTypeToJdbcType(int sqlType) {
        switch (sqlType) {
            case Types.BIT:
                return "BIT";
            case Types.BOOLEAN:
                return "BOOLEAN";
            case Types.TINYINT:
                return "TINYINT";
            case Types.SMALLINT:
                return "SMALLINT";
            case Types.INTEGER:
                return "INTEGER";
            case Types.BIGINT:
                return "BIGINT";
            case Types.REAL:
                return "REAL";
            case Types.FLOAT:
                return "FLOAT";
            case Types.DOUBLE:
                return "DOUBLE";
            case Types.DECIMAL:
                return "DECIMAL";
            case Types.NUMERIC:
                return "NUMERIC";
            case Types.VARCHAR:
                return "VARCHAR";
            case Types.CHAR:
                return "CHAR";
            case Types.NCHAR:
                return "NCHAR";
            case Types.NVARCHAR:
                return "NVARCHAR";
            case Types.LONGVARCHAR:
                return "LONGVARCHAR";
            case Types.LONGNVARCHAR:
                return "LONGNVARCHAR";
            case Types.DATE:
                return "DATE";
            case Types.TIME:
                return "TIME";
            case Types.TIMESTAMP:
                return "TIMESTAMP";
            case Types.NCLOB:
                return "NCLOB";
            case Types.CLOB:
                return "CLOB";
            case Types.BLOB:
                return "BLOB";
            case Types.BINARY:
                return "BINARY";
            case Types.VARBINARY:
                return "VARBINARY";
            case Types.LONGVARBINARY:
                return "LONGVARBINARY";
            case Types.NULL:
                return "NULL";
            case Types.OTHER:
                return "OTHER";
            case Types.JAVA_OBJECT:
                return "JAVA_OBJECT";
            default:
                return null;
        }
    }

}
