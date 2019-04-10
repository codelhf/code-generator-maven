package com.example.generator.maven;

import com.example.generator.api.NullProgressCallback;
import org.apache.maven.plugin.logging.Log;

/**
 * @Description: This callback logs progress messages with the Maven logger.
 * @Auther: liuhf
 * @CreateTime: 2019/3/13 21:24
 */
public class MavenProgressCallback extends NullProgressCallback {

    private Log log;
    private boolean verbose;

    public MavenProgressCallback(Log log, boolean verbose) {
        super();
        this.log = log;
        this.verbose = verbose;
    }

    @Override
    public void startTask(String subTaskName) {
        if (verbose) {
            log.info(subTaskName);
        }
    }
}
