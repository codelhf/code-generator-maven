package com.example.generator.code.task;

import com.example.generator.code.generator.EntityDtoGenerator;
import com.example.generator.code.generator.EntityGenerator;
import com.example.generator.code.task.base.BaseTask;
import com.example.generator.code.task.base.FileUtil;
import com.example.generator.code.task.base.FreemarkerUtil;
import com.example.generator.db.ColumnInfo;
import com.example.generator.code.task.base.BaseTask;
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
 * @CreateTime: 2019/3/16 21:17
 */
public class EntityDtoTask extends BaseTask {

    /**
     * 1.单表生成  2.多表时生成子表实体
     */
    public EntityDtoTask(String className, List<ColumnInfo> tableInfo) {
        this(className, null, null, tableInfo);
    }

    /**
     * 一对多关系生成主表实体
     */
    public EntityDtoTask(String className, String parentClassName, String foreignKey, List<ColumnInfo> tableInfo) {
        this(className, parentClassName, foreignKey, null, tableInfo);
    }

    /**
     * 多对多关系生成主表实体
     */
    public EntityDtoTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfo) {
        super(className, parentClassName, foreignKey, parentForeignKey, tableInfo);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成EntityDto填充数据
        System.out.println("Generating " + className + ".java");
        Map<String, String> entityDtoData = new HashMap<>();

        entityDtoData.put("EntityDtoPackageName", configuration.getCommonGenerator().getDtoGenerator().getTargetPackage());
        entityDtoData.put("ClassName", className);

        String title = className;
        String description = className + "实体类DTO";
        entityDtoData.put("Remark", EntityGenerator.generateRemark(title, description));
        boolean useLombok = configuration.getCommonGenerator().getModelGenerator().isUseLombok();
        if (useLombok) {
            entityDtoData.put("Lombok", EntityGenerator.generateLombok());
        }
        if (StringUtil.isNotBlank(parentForeignKey)) { // 多对多：主表实体
            entityDtoData.put("Properties", EntityGenerator.generateProperties(parentClassName, tableInfo));
            entityDtoData.put("Methods", EntityGenerator.generateProperties(parentClassName, tableInfo));
        } else if (!StringUtil.isNotBlank(foreignKey)) { // 多对一：主表实体
            entityDtoData.put("Properties", EntityGenerator.generateProperties(parentClassName, tableInfo, foreignKey));
            entityDtoData.put("Methods", EntityGenerator.generateMethods(parentClassName, tableInfo, foreignKey));
        } else { // 单表关系
            entityDtoData.put("Properties", EntityDtoGenerator.generateProperties(tableInfo));
            if (!useLombok) {
                entityDtoData.put("Constructor", "Constructor");
                entityDtoData.put("AllArgsConstructor", EntityDtoGenerator.generateAllArgsConstructor(className, tableInfo));
                entityDtoData.put("NoArgsConstructor", EntityDtoGenerator.generateNoArgsConstructor(className));
                entityDtoData.put("Methods", EntityDtoGenerator.generateMethods(tableInfo));
            }
        }

        String targetProject = configuration.getCommonGenerator().getDtoGenerator().getTargetProject();
        String targetPackage = configuration.getCommonGenerator().getDtoGenerator().getTargetPackage();

        String filePath = FileUtil.getBasicProjectPath() + targetProject + FileUtil.package2Path(targetPackage);
        String fileName = className + "DTO.java";
        int type = FreemarkerUtil.FileTypeEnum.ENTITY_DTO.getCode();
        boolean override = configuration.getCommonGenerator().isOverwrite();
        // 生成EntityDto文件
        FileUtil.generateToCode(filePath, fileName, entityDtoData, type, true, override);
    }
}
