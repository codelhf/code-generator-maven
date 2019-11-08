package com.example.generator.util;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public class StringUtil {

    public static final String EMPTY = "";

    /**
     * 空字符串
     */
    public static boolean isBlank(String string) {
        return string == null || string.trim().equals("");
    }

    /**
     * 非空字符串
     */
    public static boolean isNotBlank(String string) {
        return !isBlank(string);
    }

    /**
     * 是否为true
     */
    public static boolean isTrue(String string) {
        return  "true".equals(string);
    }

    /**
     * 以驼峰命名法生成类名，用于未指定类名时自动生成类名，如sys_user自动生成类名SysUser
     */
    public static String tableName2ClassName(String tableName) {
        if (isBlank(tableName)) {
            return EMPTY;
        }
        // 列名中不包含 "_"
        if (!tableName.contains("_")) {
            if (isAllUpperCase(tableName)) {
                return firstToUpperCase(tableName.toLowerCase());
            }
            return firstToUpperCase(tableName);
        }
        String[] nameStrList = tableName.split("_");
        StringBuilder sb = new StringBuilder();
        for (String string : nameStrList) {
            sb.append(string.substring(0, 1).toUpperCase()).append(string.substring(1));
        }
        return sb.toString();
    }

    /**
     * 数据库列名转换为实体的属性名
     */
    public static String columnName2PropertyName(String columnName) {
        if (isBlank(columnName)) {
            return EMPTY;
        }

        // 列名中不包含 "_"
        if (!columnName.contains("_")) {
            if (isAllUpperCase(columnName)) {
                return columnName.toLowerCase();
            }
            return columnName;
        }
        String[] str = columnName.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder();
        sb.append(str[0]);
        for (int i = 1; i < str.length; i++) {
            sb.append(firstToUpperCase(str[i]));
        }
        return sb.toString();
    }

    /**
     * 实体的属性名转换为数据库列名
     */
    public static String propertyName2ColumnName(String propertyName) {
        if (isBlank(propertyName)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (Character c: propertyName.toCharArray()) {
            if (!Character.isUpperCase(c)) {
                sb.append(c);
            } else {
                sb.append("_").append(c.toString().toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 给定字符串除特定符号外的字符是否全部大写
     */
    public static boolean isAllUpperCase(String string) {
        for (Character c : string.replace("_", "").toCharArray()) {
            if (!Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给定字符串除特定符号外的字符是否全部小写
     */
    public static boolean isAllLowerCase(String string) {
        for (Character c : string.replace("_", "").toCharArray()) {
            if (!Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 首字母大写
     */
    public static String firstToUpperCase(String string) {
        StringBuilder sb = new StringBuilder();
        sb.append(string.substring(0, 1).toUpperCase()).append(string.substring(1));
        return sb.toString();
    }

    /**
     * 首字母小写
     */
    public static String firstToLowerCase(String string) {
        StringBuilder sb = new StringBuilder();
        sb.append(string.substring(0, 1).toLowerCase()).append(string.substring(1));
        return sb.toString();
    }

    /**
     * 截取字符串
     */
    public static String subLastCharacter(String string) {
        return string.substring(0, string.length() - 1);
    }
}
