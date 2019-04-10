package com.example.generator.maven;

import com.example.generator.logging.AbstractLogFactory;
import com.example.generator.logging.Log;
import org.apache.maven.plugin.Mojo;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/1/23 9:46
 */
public class MavenLogFactory implements AbstractLogFactory {
    private final MavenLogImpl logImplementation;

    MavenLogFactory(Mojo mojo) {
        logImplementation = new MavenLogImpl(mojo.getLog());
    }

    @Override
    public Log getLog(Class<?> targetClass) {
        return logImplementation;
    }
}