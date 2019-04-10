package com.example.generator.maven;

import org.apache.maven.plugin.logging.Log;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/1/23 9:46
 */
public class MavenLogImpl implements com.example.generator.logging.Log {
    private final Log mavenLog;

    MavenLogImpl(Log log) {
        mavenLog = log;
    }

    @Override
    public boolean isDebugEnabled() {
        return mavenLog.isDebugEnabled();
    }

    @Override
    public void error(String s, Throwable e) {
        mavenLog.error(s, e);
    }

    @Override
    public void error(String s) {
        mavenLog.error(s);
    }

    @Override
    public void debug(String s) {
        mavenLog.debug(s);
    }

    @Override
    public void warn(String s) {
        mavenLog.warn(s);
    }

}
