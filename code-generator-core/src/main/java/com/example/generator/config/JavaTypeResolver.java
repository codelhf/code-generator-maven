package com.example.generator.config;

import com.example.generator.config.xml.XmlPropertyHolder;
import com.example.generator.util.StringUtil;
import com.example.generator.util.StringUtil;

import java.util.List;

/**
 * @Description: java类型适配
 * @Auther: liuhf
 * @CreateTime: 2019/2/27 16:49
 */
public class JavaTypeResolver extends XmlPropertyHolder {
    /**
     * 当表名或者字段名为SQL关键字的时候，可
     * 以设置该属性为true，MBG会自动给表名或字段名添加分隔符
     */
    private boolean autoDelimitKeywords = true;

    /**
     * 开始分隔符，例如 `
     */
    private String beginningDelimiter;

    /**
     * 结束分隔符，例如 `
     */
    private String endingDelimiter;

    /**
     * 默认false，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer
     * true，把JDBC DECIMAL 和 NUMERIC 类型解析为java.math.BigDecimal
     */
    private boolean forceBigDecimals = false;

    public void validate(List<String> errors){
        if (autoDelimitKeywords) {
            if (StringUtil.isBlank(beginningDelimiter)) {
                errors.add("autoDelimitKeywords is true, beginningDelimiter can not be blank");
            }
            if (StringUtil.isBlank(endingDelimiter)) {
                errors.add("autoDelimitKeywords is true, endingDelimiter can not be blank");
            }
        }
    }

    public boolean isAutoDelimitKeywords() {
        return autoDelimitKeywords;
    }

    public void setAutoDelimitKeywords(boolean autoDelimitKeywords) {
        this.autoDelimitKeywords = autoDelimitKeywords;
    }

    public String getBeginningDelimiter() {
        return beginningDelimiter;
    }

    public void setBeginningDelimiter(String beginningDelimiter) {
        this.beginningDelimiter = beginningDelimiter;
    }

    public String getEndingDelimiter() {
        return endingDelimiter;
    }

    public void setEndingDelimiter(String endingDelimiter) {
        this.endingDelimiter = endingDelimiter;
    }

    public boolean isForceBigDecimals() {
        return forceBigDecimals;
    }

    public void setForceBigDecimals(boolean forceBigDecimals) {
        this.forceBigDecimals = forceBigDecimals;
    }
}
