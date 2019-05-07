package com.example.generator.code.task;

import com.example.generator.code.generator.base.BaseGenerator;
import com.example.generator.code.task.base.BaseTask;
import com.example.generator.code.task.base.FileUtil;
import com.example.generator.code.task.base.VelocityUtil;
import com.example.generator.config.Configuration;
import com.example.generator.db.ColumnInfo;
import com.example.generator.util.DateTimeUtil;
import com.example.generator.util.StringUtil;

import java.io.IOException;
import java.util.Date;
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
    public void run() throws IOException {
        // 生成Service填充数据
        Map<String, Object> serviceData = new HashMap<>();

        serviceData.put("ServicePackageName", configuration.getTemplateConfig().getService());
        serviceData.put("BasePackageName", configuration.getCommentGenerator().getBasePackageName());
        serviceData.put("ResponseClass", BaseGenerator.responseClass);
        serviceData.put("EntityDTOPackageName", configuration.getCommonGenerator().getDtoGenerator().getTargetPackage());
        String clazzName = StringUtil.firstToLowerCase(className);
        serviceData.put("ClassName", className);
        serviceData.put("className", clazzName);

        String title = className + "Service";
        String description = className + "接口层";
        serviceData.put("Remark", BaseGenerator.generateRemark(title, description, configuration));

        boolean generateRemark = true;
        serviceData.put("generateRemark", generateRemark);
        serviceData.put("company", configuration.getCommentGenerator().getCompany());
        serviceData.put("author", configuration.getCommentGenerator().getAuthor());
        serviceData.put("createTime", DateTimeUtil.dateToStr(new Date()));

        ColumnInfo primaryKeyColumnInfo = getPrimaryKeyColumnInfo(tableInfo);
        serviceData.put("propertyName", primaryKeyColumnInfo.getPropertyName());
        serviceData.put("javaType", primaryKeyColumnInfo.getJavaType());
        serviceData.put("isView", isView);

        String targetProject = configuration.getTemplateConfig().getTargetProject();
        String targetPackage = configuration.getTemplateConfig().getService();

        String filePath = FileUtil.getGeneratePath(configuration.getConfigFilePath(), targetProject, targetPackage);
        String fileName = "I" + className + "Service.java";
        int type = VelocityUtil.FileTypeEnum.SERVICE.getCode();
        boolean generate = configuration.getTemplateConfig().isGenerator();
        // 生成Service文件
        FileUtil.generateToCode(filePath, fileName, serviceData, type, generate, false);
        System.out.println("[INFO] Generating " + fileName);
    }
}
