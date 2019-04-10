package com.example.generator.api;

/**
 * @Description: This class implements a progress callback that does nothing. It is used when
 *               the client passes in a null for the ProgressCallback.
 * @Auther: liuhf
 * @CreateTime: 2019/3/13 21:24
 */
public class NullProgressCallback implements ProgressCallback {

    public NullProgressCallback() {
        super();
    }

    @Override
    public void introspectionStarted(int totalTasks) {

    }

    @Override
    public void generationStarted(int totalTasks) {

    }

    @Override
    public void saveStarted(int totalTasks) {

    }

    @Override
    public void startTask(String taskName) {

    }

    @Override
    public void done() {

    }

    @Override
    public void checkCancel() throws InterruptedException {

    }
}
