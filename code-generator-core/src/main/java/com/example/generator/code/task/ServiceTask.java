package com.example.generator.code.task;

import com.example.generator.code.generator.ServiceGenerator;
import com.example.generator.code.task.base.BaseTask;
import com.example.generator.code.task.base.FileUtil;
import com.example.generator.code.task.base.FreemarkerUtil;
import com.example.generator.db.ColumnInfo;
import com.example.generator.code.generator.ServiceGenerator;
import com.example.generator.code.task.base.BaseTask;
import com.example.generator.code.task.base.FileUtil;
import com.example.generator.code.task.base.FreemarkerUtil;
import com.example.generator.db.ColumnInfo;
import com.example.generator.util.StringUtil;
import com.example.generator.util.StringUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public class ServiceTask extends BaseTask {

    public ServiceTask(String className, boolean isView) {
        super(className, isView);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Service填充数据
        System.out.println("Generating " + className + "Service.java");
        Map<String, String> serviceData = new HashMap<>();

        serviceData.put("ServicePackageName", configuration.getServiceGenerator().getService());
        serviceData.put("EntityPackageName", configuration.getCommonGenerator().getModelGenerator().getTargetPackage());
        String clazzName = StringUtil.firstToLowerCase(className);
        serviceData.put("ClassName", className);
        serviceData.put("className", clazzName);

        String title = className + "Service";
        String description = className + "接口层";
        serviceData.put("Remark", ServiceGenerator.generateRemark(title, description));

        ColumnInfo primaryKeyColumnInfo = getPrimaryKeyColumnInfo(tableInfo);
        serviceData.put("listRemark", ServiceGenerator.listRemark(className));
        serviceData.put("list", ServiceGenerator.list());
        serviceData.put("selectRemark", ServiceGenerator.selectRemark(className, primaryKeyColumnInfo));
        serviceData.put("select", ServiceGenerator.select(className, primaryKeyColumnInfo));
        if (!isView) {
            serviceData.put("insertRemark", ServiceGenerator.insertRemark(className, clazzName));
            serviceData.put("insert", ServiceGenerator.insert(className, clazzName));
            serviceData.put("updateRemark", ServiceGenerator.updateRemark(className, clazzName, primaryKeyColumnInfo));
            serviceData.put("update", ServiceGenerator.update(className, clazzName, primaryKeyColumnInfo));
            serviceData.put("deleteRemark", ServiceGenerator.deleteRemark(className, primaryKeyColumnInfo));
            serviceData.put("delete", ServiceGenerator.delete(primaryKeyColumnInfo));
        }

        String targetProject = configuration.getServiceGenerator().getTargetProject();
        String targetPackage = configuration.getServiceGenerator().getService();

        String filePath = FileUtil.getBasicProjectPath() + targetProject + FileUtil.package2Path(targetPackage);
        String fileName = className + "Service.java";
        int type = FreemarkerUtil.FileTypeEnum.SERVICE.getCode();
        boolean generate = configuration.getServiceGenerator().isGenerator();
        // 生成Service文件
        FileUtil.generateToCode(filePath, fileName, serviceData, type, generate, false);
    }
}
