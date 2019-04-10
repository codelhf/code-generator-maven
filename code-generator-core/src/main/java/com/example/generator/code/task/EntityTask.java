package com.example.generator.code.task;

import com.example.generator.code.generator.EntityGenerator;
import com.example.generator.code.task.base.BaseTask;
import com.example.generator.code.task.base.FileUtil;
import com.example.generator.code.task.base.FreemarkerUtil;
import com.example.generator.db.ColumnInfo;
import com.example.generator.code.generator.EntityGenerator;
import com.example.generator.code.task.base.BaseTask;
import com.example.generator.code.task.base.FileUtil;
import com.example.generator.code.task.base.FreemarkerUtil;
import com.example.generator.db.ColumnInfo;
import com.example.generator.util.StringUtil;
import com.example.generator.util.StringUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/23 10:29
 */
public class EntityTask extends BaseTask {

    /**
     * 1.单表生成  2.多表时生成子表实体
     */
    public EntityTask(String className, List<ColumnInfo> tableInfo) {
        this(className, null, null, tableInfo);
    }

    /**
     * 一对多关系生成主表实体
     */
    public EntityTask(String className, String parentClassName, String foreignKey, List<ColumnInfo> tableInfo) {
        this(className, parentClassName, foreignKey, null, tableInfo);
    }

    /**
     * 多对多关系生成主表实体
     */
    public EntityTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfo) {
        super(className, parentClassName, foreignKey, parentForeignKey, tableInfo);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Entity填充数据
        System.out.println("Generating " + className + ".java");
        Map<String, String> entityData = new HashMap<>();

        String title = className;
        String description = className + "实体类";

        entityData.put("EntityPackageName", configuration.getCommonGenerator().getModelGenerator().getTargetPackage());
        entityData.put("ClassName", className);

        entityData.put("Remark", EntityGenerator.generateRemark(title, description));
        boolean useLombok = configuration.getCommonGenerator().getModelGenerator().isUseLombok();
        if (useLombok) {
            entityData.put("Lombok", EntityGenerator.generateLombok());
        }
        if (StringUtil.isNotBlank(parentForeignKey)) { // 多对多：主表实体
            entityData.put("Properties", EntityGenerator.generateProperties(parentClassName, tableInfo));
            if (!useLombok) {
                entityData.put("Constructor", "Constructor");
                entityData.put("Methods", EntityGenerator.generateMethods(parentClassName, tableInfo));
            }

        } else if (StringUtil.isNotBlank(foreignKey)) { // 多对一：主表实体
            entityData.put("Properties", EntityGenerator.generateProperties(parentClassName, tableInfo, foreignKey));
            if (!useLombok) {
                entityData.put("Constructor", "Constructor");
                entityData.put("Methods", EntityGenerator.generateMethods(parentClassName, tableInfo, foreignKey));
            }
        } else { // 单表关系
            entityData.put("Properties", EntityGenerator.generateProperties(tableInfo));
            if (!useLombok) {
                entityData.put("Constructor", "Constructor");
                entityData.put("AllArgsConstructor", EntityGenerator.generateAllArgsConstructor(className, tableInfo));
                entityData.put("NoArgsConstructor", EntityGenerator.generateNoArgsConstructor(className));
                entityData.put("Methods", EntityGenerator.generateMethods(tableInfo));
            }
        }

        String targetProject = configuration.getCommonGenerator().getModelGenerator().getTargetProject();
        String targetPackage = configuration.getCommonGenerator().getModelGenerator().getTargetPackage();

        String filePath = FileUtil.getBasicProjectPath() + targetProject + FileUtil.package2Path(targetPackage);
        String fileName = className + ".java";
        int type = FreemarkerUtil.FileTypeEnum.ENTITY.getCode();
        boolean override = configuration.getCommonGenerator().isOverwrite();
        // 生成Entity文件
        FileUtil.generateToCode(filePath, fileName, entityData, type, true, override);
    }
}
