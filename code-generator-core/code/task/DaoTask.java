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
 * @CreateTime: 2019/3/16 21:18
 */
public class DaoTask extends BaseTask {

    public DaoTask(String className, List<ColumnInfo> tableInfo, boolean isView, Configuration configuration) {
        super(className, tableInfo, isView, configuration);
    }

    @Override
    public void run() throws IOException {
        // 生成Dao填充数据
        Map<String, Object> daoData = new HashMap<>();

        daoData.put("DaoPackageName", configuration.getCommonGenerator().getDaoGenerator().getTargetPackage());
        daoData.put("EntityPackageName",configuration.getCommonGenerator().getModelGenerator().getTargetPackage());

        String clazzName = StringUtil.firstToLowerCase(className);
        daoData.put("ClassName", className);
        daoData.put("className", clazzName);

        String title = className + "Mapper";
        String description = className + "实体类";
        daoData.put("Remark", BaseGenerator.generateRemark(title, description, configuration));

        boolean CommonMapper = false;
        daoData.put("CommonMapper", CommonMapper);
        daoData.put("isView", isView);
        if (CommonMapper) {
            String targetProject = configuration.getCommonGenerator().getDaoGenerator().getTargetProject();
            String targetPackage = configuration.getCommonGenerator().getDaoGenerator().getTargetPackage();
            String filePath = FileUtil.getGeneratePath(configuration.getConfigFilePath(), targetProject, targetPackage);
            if (!isView) {
                daoData.put("baseMapper", "TableMapper");
                String fileName = "TableMapper.java";
                int type = VelocityUtil.FileTypeEnum.DAO_TABLE_MAPPER.getCode();
                FileUtil.generateToCode(filePath, fileName, daoData, type, true, true);
            } else {
                daoData.put("baseMapper", "ViewMapper");
                String fileName = "ViewMapper.java";
                int type = VelocityUtil.FileTypeEnum.DAO_VIEW_MAPPER.getCode();
                FileUtil.generateToCode(filePath, fileName, daoData, type, true, true);
            }
        }

        ColumnInfo primaryKeyColumn = getPrimaryKeyColumnInfo(tableInfo);
        daoData.put("propertyName", primaryKeyColumn.getPropertyName());
        daoData.put("javaType", primaryKeyColumn.getJavaType());

        String targetProject = configuration.getCommonGenerator().getDaoGenerator().getTargetProject();
        String targetPackage = configuration.getCommonGenerator().getDaoGenerator().getTargetPackage();

        String filePath = FileUtil.getGeneratePath(configuration.getConfigFilePath(), targetProject, targetPackage);
        String fileName = className + "Mapper.java";
        int type = VelocityUtil.FileTypeEnum.DAO.getCode();
        boolean override = configuration.getCommonGenerator().isOverwrite();
        // 生成dao文件
        FileUtil.generateToCode(filePath, fileName, daoData, type, true, override);
        System.out.println("[INFO] Generating " + fileName);
    }
}
