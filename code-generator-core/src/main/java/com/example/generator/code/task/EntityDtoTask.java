package com.example.generator.code.task;

import com.example.generator.code.generator.EntityDtoGenerator;
import com.example.generator.code.generator.base.BaseGenerator;
import com.example.generator.code.task.base.BaseTask;
import com.example.generator.code.task.base.FileUtil;
import com.example.generator.code.task.base.VelocityUtil;
import com.example.generator.config.Configuration;
import com.example.generator.db.ColumnInfo;

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
    public EntityDtoTask(String className, List<ColumnInfo> tableInfo, Configuration configuration) {
        this(className, null, null, tableInfo, configuration);
    }

    /**
     * 一对多关系生成主表实体
     */
    public EntityDtoTask(String className, String parentClassName, String foreignKey, List<ColumnInfo> tableInfo, Configuration configuration) {
        this(className, parentClassName, foreignKey, null, tableInfo, configuration);
    }

    /**
     * 多对多关系生成主表实体
     */
    public EntityDtoTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfo, Configuration configuration) {
        super(className, parentClassName, foreignKey, parentForeignKey, tableInfo, configuration);
    }

    @Override
    public void run() throws IOException {
        // 生成EntityDto填充数据
        Map<String, Object> entityDtoData = new HashMap<>();

        entityDtoData.put("EntityDtoPackageName", configuration.getCommonGenerator().getDtoGenerator().getTargetPackage());
        entityDtoData.put("ClassName", className);

        String title = className + "DTO";
        String description = className + "实体类DTO";
        entityDtoData.put("Remark", BaseGenerator.generateRemark(title, description, configuration));
        entityDtoData.put("Lombok", BaseGenerator.generateLombok());

        entityDtoData.put("Properties", EntityDtoGenerator.generateProperties(tableInfo));
        entityDtoData.put("tableInfo", tableInfo);

        String targetProject = configuration.getCommonGenerator().getDtoGenerator().getTargetProject();
        String targetPackage = configuration.getCommonGenerator().getDtoGenerator().getTargetPackage();

        String filePath = FileUtil.getGeneratePath(configuration.getConfigFilePath(),targetProject, targetPackage);
        String fileName = className + "DTO.java";
        int type = VelocityUtil.FileTypeEnum.ENTITY_DTO.getCode();
        boolean override = configuration.getCommonGenerator().isOverwrite();
        // 生成EntityDto文件
        FileUtil.generateToCode(filePath, fileName, entityDtoData, type, true, override);
        System.out.println("[INFO] Generating " + fileName);
    }
}
