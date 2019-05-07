package com.example.generator.code.task;

import com.example.generator.code.generator.base.BaseGenerator;
import com.example.generator.code.task.base.BaseTask;
import com.example.generator.code.task.base.FileUtil;
import com.example.generator.code.task.base.VelocityUtil;
import com.example.generator.config.Configuration;
import com.example.generator.db.ColumnInfo;
import com.example.generator.util.StringUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public class ControllerTask extends BaseTask {

    public ControllerTask(String className, List<ColumnInfo> tableInfo, boolean isView, Configuration configuration) {
        super(className, tableInfo, isView, configuration);
    }

    @Override
    public void run() throws IOException {
        // 生成Controller填充数据
        Map<String, Object> controllerData = new HashMap<>();

        controllerData.put("ControllerPackageName", configuration.getTemplateConfig().getController());
        controllerData.put("BasePackageName", configuration.getCommentGenerator().getBasePackageName());
        controllerData.put("ResponseClass", BaseGenerator.responseClass);
        controllerData.put("EntityDTOPackageName", configuration.getCommonGenerator().getDtoGenerator().getTargetPackage());
        controllerData.put("ServicePackageName", configuration.getTemplateConfig().getService());

        String clazzName = StringUtil.firstToLowerCase(className);
        controllerData.put("ClassName", className);
        controllerData.put("className", clazzName);

        String title = className + "Controller";
        String description = className + "控制层";
        controllerData.put("Remark", BaseGenerator.generateRemark(title, description, configuration));

        boolean generateSwagger = true;
        controllerData.put("generateSwagger", generateSwagger);
        ColumnInfo primaryKeyColumn = getPrimaryKeyColumnInfo(tableInfo);
        controllerData.put("propertyName", primaryKeyColumn.getPropertyName());
        controllerData.put("javaType", primaryKeyColumn.getJavaType());

        controllerData.put("isView", isView);

        String targetProject = configuration.getTemplateConfig().getTargetProject();
        String targetPackage = configuration.getTemplateConfig().getController();

        String filePath = FileUtil.getGeneratePath(configuration.getConfigFilePath(), targetProject, targetPackage);
        String fileName = className + "Controller.java";

        int type = VelocityUtil.FileTypeEnum.CONTROLLER.getCode();
        boolean generate = configuration.getTemplateConfig().isGenerator();
        // 生成Controller文件
        FileUtil.generateToCode(filePath, fileName, controllerData, type, generate, false);
        System.out.println("[INFO] Generating " + fileName);
    }
}
