package com.example.generator.api;

public interface ProgressCallback {
    /**
     * Called to note the start of the introspection phase, and to note the
     * maximum number of startTask messages that will be sent for the
     * introspection phase.
     * 
     * @param totalTasks
     *            the maximum number of times startTask will be called for the
     *            introspection phase.
     */
    void introspectionStarted(int totalTasks);

    /**
     * Called to note the start of the generation phase, and to note the maximum
     * number of startTask messages that will be sent for the generation phase.
     * 
     * @param totalTasks
     *            the maximum number of times startTask will be called for the
     *            generation phase.
     */
    void generationStarted(int totalTasks);

    /**
     * Called to note the start of the file saving phase, and to note the
     * maximum number of startTask messages that will be sent for the file
     * saving phase phase.
     * 
     * @param totalTasks
     *            the maximum number of times startTask will be called for the
     *            file saving phase.
     */
    void saveStarted(int totalTasks);

    /**
     * Called to denote the beginning of a save task.
     * 
     * @param taskName
     *            a descriptive name of the current work step
     */
    void startTask(String taskName);

    /**
     * This method is called when all generated files have been saved.
     */
    void done();

    /**
     * The method is called periodically during a long running method.
     * If the the implementation throws <code>InterruptedException</code> then
     * the method will be canceled. Any files that have already been saved will
     * remain on the file system.
     * 
     * @throws InterruptedException
     *             if the operation should be halted
     */
    void checkCancel() throws InterruptedException;
}
