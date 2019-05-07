package com.example.generator.code.task;

import com.example.generator.code.generator.EntityGenerator;
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
 * @CreateTime: 2019/3/23 10:29
 */
public class EntityTask extends BaseTask {

    /**
     * 1.单表生成  2.多表时生成子表实体
     */
    public EntityTask(String className, List<ColumnInfo> tableInfo, Configuration configuration) {
        this(className, null, null, tableInfo, configuration);
    }

    /**
     * 一对多关系生成主表实体
     */
    public EntityTask(String className, String parentClassName, String foreignKey, List<ColumnInfo> tableInfo, Configuration configuration) {
        this(className, parentClassName, foreignKey, null, tableInfo, configuration);
    }

    /**
     * 多对多关系生成主表实体
     */
    public EntityTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfo, Configuration configuration) {
        super(className, parentClassName, foreignKey, parentForeignKey, tableInfo, configuration);
    }

    @Override
    public void run() throws IOException {
        // 生成Entity填充数据
        Map<String, Object> entityData = new HashMap<>();

        entityData.put("EntityPackageName", configuration.getCommonGenerator().getModelGenerator().getTargetPackage());
        entityData.put("ClassName", className);

        String title = className;
        String description = className + "实体类";
        entityData.put("Remark", BaseGenerator.generateRemark(title, description, configuration));
        entityData.put("Lombok", BaseGenerator.generateLombok());

        if (StringUtil.isNotBlank(parentForeignKey)) { // 多对多：主表实体
            entityData.put("Properties", EntityGenerator.generateProperties(parentClassName, tableInfo));
        } else if (StringUtil.isNotBlank(foreignKey)) { // 多对一：主表实体
            entityData.put("Properties", EntityGenerator.generateProperties(parentClassName, tableInfo, foreignKey));
        } else { // 单表关系
            entityData.put("Properties", EntityGenerator.generateProperties(tableInfo));
        }

        entityData.put("tableInfo", tableInfo);

        String targetProject = configuration.getCommonGenerator().getModelGenerator().getTargetProject();
        String targetPackage = configuration.getCommonGenerator().getModelGenerator().getTargetPackage();

        String filePath = FileUtil.getGeneratePath(configuration.getConfigFilePath(), targetProject, targetPackage);
        String fileName = className + ".java";
        int type = VelocityUtil.FileTypeEnum.ENTITY.getCode();
        boolean override = configuration.getCommonGenerator().isOverwrite();
        // 生成Entity文件
        FileUtil.generateToCode(filePath, fileName, entityData, type, true, override);
        System.out.println("[INFO] Generating " + fileName);
    }
}
