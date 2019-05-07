package com.example.generator.config;

import com.example.generator.db.DriverFactory;
import com.example.generator.util.StringUtil;

import java.util.List;

/**
 * @Description: 数据源
 * @Auther: liuhf
 * @CreateTime: 2019/2/27 16:48
 */
public class JdbcConnection {
    /**
     * 数据库驱动jar路径，非必须参数
     */
    private String driverLocation;
    /**
     * 数据库驱动类，非必须参数
     */
    private String driverClass;
    /**
     * 数据库链接地址
     */
    private String url;
    /**
     * 数据库用户名
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;

    public void validate(List<String> errors) {
        if (StringUtil.isBlank(url)) {
            errors.add("JdbcConnection url cant not be blank");
        }
        if (StringUtil.isBlank(driverClass)) {
            this.driverClass = DriverFactory.getDriver(url);
        }
        if (StringUtil.isBlank(username)) {
            errors.add("JdbcConnection username can not be blank");
        }
        if (StringUtil.isBlank(password)) {
            errors.add("JdbcConnection password can not be blank");
        }
    }

    public String getDriverLocation() {
        return driverLocation;
    }

    public void setDriverLocation(String driverLocation) {
        this.driverLocation = driverLocation;
    }

    public String getDriverClass() {
        return driverClass == null ? "" : driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username == null ? "" : username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
