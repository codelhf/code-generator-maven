package com.example.generator.code.invoker.base;

import com.example.generator.code.BaseInfo;
import com.example.generator.code.task.base.BaseTask;
import com.example.generator.code.task.base.TaskQueue;
import com.example.generator.db.ConnectionUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public abstract class BaseInvoker extends BaseInfo implements Invoker {

    protected ConnectionUtil connectionUtil = new ConnectionUtil(configuration);
    protected TaskQueue<BaseTask> taskQueue = new TaskQueue();
    private ExecutorService executorPool = Executors.newFixedThreadPool(6);

    private void initDataSource() throws Exception {
        if (!connectionUtil.initConnection()) {
            throw new Exception("Failed to connect to database at url:");
        }
        getTableInfo();
        connectionUtil.close();
    }

    protected abstract void getTableInfo() throws SQLException;

    protected abstract void initTasks();

    @Override
    public void execute() {
        try {
            initDataSource();
            initTasks();
            while (!taskQueue.isEmpty()) {
                BaseTask task = taskQueue.poll();
                executorPool.execute(() -> {
                    try {
                        task.run();
                    } catch (IOException | TemplateException e) {
                        e.printStackTrace();
                    }
                });
            }
            executorPool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
