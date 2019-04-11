package com.example.generator.code.task;

import com.example.generator.code.generator.ServiceGenerator;
import com.example.generator.code.task.base.BaseTask;
import com.example.generator.code.task.base.FileUtil;
import com.example.generator.code.task.base.FreemarkerUtil;
import com.example.generator.config.Configuration;
import com.example.generator.db.ColumnInfo;
import com.example.generator.util.StringUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public class ServiceTask extends BaseTask {

    public ServiceTask(String className, List<ColumnInfo> tableInfo, boolean isView, Configuration configuration) {
        super(className, tableInfo, isView, configuration);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Service填充数据
        System.out.println("Generating " + className + "Service.java");
        Map<String, String> serviceData = new HashMap<>();

        serviceData.put("ServicePackageName", configuration.getServiceGenerator().getService());
        serviceData.put("BasePackageName", configuration.getCommentGenerator().getBasePackageName());
        serviceData.put("ResponseClass", ServiceGenerator.responseClass);
        serviceData.put("EntityDTOPackageName", configuration.getCommonGenerator().getDtoGenerator().getTargetPackage());
        String clazzName = StringUtil.firstToLowerCase(className);
        serviceData.put("ClassName", className);
        serviceData.put("className", clazzName);

        String title = className + "Service";
        String description = className + "接口层";
        serviceData.put("Remark", ServiceGenerator.generateRemark(title, description, configuration));

        ColumnInfo primaryKeyColumnInfo = getPrimaryKeyColumnInfo(tableInfo);
        serviceData.put("listRemark", ServiceGenerator.listRemark(className, configuration));
        serviceData.put("list", ServiceGenerator.list());
        serviceData.put("selectRemark", ServiceGenerator.selectRemark(className, primaryKeyColumnInfo, configuration));
        serviceData.put("select", ServiceGenerator.select(className, primaryKeyColumnInfo));
        if (!isView) {
            serviceData.put("insertRemark", ServiceGenerator.insertRemark(className, clazzName, configuration));
            serviceData.put("insert", ServiceGenerator.insert(className, clazzName));
            serviceData.put("updateRemark", ServiceGenerator.updateRemark(className, clazzName, primaryKeyColumnInfo, configuration));
            serviceData.put("update", ServiceGenerator.update(className, clazzName, primaryKeyColumnInfo));
            serviceData.put("deleteRemark", ServiceGenerator.deleteRemark(className, primaryKeyColumnInfo, configuration));
            serviceData.put("delete", ServiceGenerator.delete(primaryKeyColumnInfo));
        }

        String targetProject = configuration.getServiceGenerator().getTargetProject();
        String targetPackage = configuration.getServiceGenerator().getService();

        String filePath = FileUtil.getGeneratePath(configuration.getConfigFilePath(), targetProject, targetPackage);
        String fileName = "I" + className + "Service.java";
        int type = FreemarkerUtil.FileTypeEnum.SERVICE.getCode();
        boolean generate = configuration.getServiceGenerator().isGenerator();
        // 生成Service文件
        FileUtil.generateToCode(filePath, fileName, serviceData, type, generate, false);
    }
}
