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
public class ServiceImplTask extends BaseTask {

    public ServiceImplTask(String className, List<ColumnInfo> tableInfo, boolean isView, Configuration configuration) {
        super(className, tableInfo, isView, configuration);
    }

    @Override
    public void run() throws IOException {
        // 生成ServiceImpl填充数据
        Map<String, Object> serviceImplData = new HashMap<>();

        serviceImplData.put("ServiceImplPackageName", configuration.getServiceGenerator().getServiceImpl());
        serviceImplData.put("BasePackageName", configuration.getCommentGenerator().getBasePackageName());
        serviceImplData.put("ResponseClass", BaseGenerator.responseClass);
        serviceImplData.put("DaoPackageName", configuration.getCommonGenerator().getDaoGenerator().getTargetPackage());
        serviceImplData.put("EntityDTOPackageName", configuration.getCommonGenerator().getDtoGenerator().getTargetPackage());
        serviceImplData.put("EntityPackageName", configuration.getCommonGenerator().getModelGenerator().getTargetPackage());
        serviceImplData.put("ServicePackageName", configuration.getServiceGenerator().getService());

        String clazzName = StringUtil.firstToLowerCase(className);
        serviceImplData.put("ClassName", className);
        serviceImplData.put("className", clazzName);

        String title = className + "ServiceImpl";
        String description = className + "业务层";
        serviceImplData.put("Remark", BaseGenerator.generateRemark(title, description, configuration));

        boolean generateRemark = true;
        serviceImplData.put("generateRemark", generateRemark);
        serviceImplData.put("company", configuration.getCommentGenerator().getCompany());
        serviceImplData.put("author", configuration.getCommentGenerator().getAuthor());
        serviceImplData.put("createTime", DateTimeUtil.dateToStr(new Date()));

        ColumnInfo primaryKeyColumnInfo = getPrimaryKeyColumnInfo(tableInfo);
        serviceImplData.put("propertyName", primaryKeyColumnInfo.getPropertyName());
        serviceImplData.put("PropertyName", StringUtil.firstToUpperCase(primaryKeyColumnInfo.getPropertyName()));
        serviceImplData.put("javaType", primaryKeyColumnInfo.getJavaType());
        serviceImplData.put("isView", isView);

        String targetProject = configuration.getServiceGenerator().getTargetProject();
        String targetPackage = configuration.getServiceGenerator().getServiceImpl();

        String filePath = FileUtil.getGeneratePath(configuration.getConfigFilePath(), targetProject, targetPackage);
        String fileName = className + "ServiceImpl.java";
        int type = VelocityUtil.FileTypeEnum.SERVICE_IMPL.getCode();
        boolean generate = configuration.getServiceGenerator().isGenerator();
        // 生成ServiceImpl文件
        FileUtil.generateToCode(filePath, fileName, serviceImplData, type, generate, false);
        System.out.println("[INFO] Generating " + fileName);
    }
}
