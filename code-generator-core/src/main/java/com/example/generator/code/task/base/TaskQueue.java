package com.example.generator.code.task.base;

import java.util.LinkedList;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public class TaskQueue<E> extends LinkedList<E> {

    /**
     * 根据类型检查是否配置了相应的代码路径，未配置则不添加任务
     *
     * @param task 任务
     * @return
     */
    @Override
    public boolean add(E task) {
//        Configuration configuration = ConfigUtil.getConfiguration();
//        if (task instanceof ControllerTask) {
//            if (StringUtil.isBlank(configuration.getServiceGenerator().getController())) {
//                return false;
//            }
//        }
//        if (task instanceof ServiceTask) {
//            if (StringUtil.isBlank(configuration.getServiceGenerator().getService())) {
//                return false;
//            }
//        }
//        if (task instanceof DaoTask) {
//            if (StringUtil.isBlank(configuration.getCommonGenerator().getDaoGenerator().getTargetPackage())) {
//                return false;
//            }
//        }
//        if (task instanceof EntityTask) {
//            if (StringUtil.isBlank(configuration.getCommonGenerator().getModelGenerator().getTargetPackage())) {
//                return false;
//            }
//        }
//        if (task instanceof MapperTask) {
//            if (StringUtil.isBlank(configuration.getCommonGenerator().getMapperGenerator().getTargetPackage()) {
//                return false;
//            }
//        }
        return super.add(task);
    }
}
