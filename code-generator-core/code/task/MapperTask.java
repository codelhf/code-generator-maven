package com.example.generator.code.task;

import com.example.generator.code.generator.MapperGenerator;
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
public class MapperTask extends BaseTask {

    /**
     * 单表Mapper
     */
    public MapperTask(String className, String tableName, List<ColumnInfo> tableInfo, boolean isView, Configuration configuration) {
        this(tableName, className, null, null, null, tableInfo, null, isView, configuration);
    }

    /**
     * 一对多Mapper
     */
    public MapperTask(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, List<ColumnInfo> tableInfo, List<ColumnInfo> parentTableInfo, boolean isView, Configuration configuration) {
        this(tableName, className, parentTableName, parentClassName, foreignKey, null, null, tableInfo, parentTableInfo, isView, configuration);
    }

    /**
     * 多对多Mapper
     */
    public MapperTask(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, String parentForeignKey, String relationalTableName, List<ColumnInfo> tableInfo, List<ColumnInfo> parentTableInfo, boolean isView, Configuration configuration) {
        super(tableName, className, parentTableName, parentClassName, foreignKey, parentForeignKey, relationalTableName, tableInfo, parentTableInfo, isView, configuration);
    }

    @Override
    public void run() throws IOException {
        // 生成Mapper填充数据
        Map<String, String> mapperData = new HashMap<>();

        mapperData.put("DaoPackageName", configuration.getCommonGenerator().getDaoGenerator().getTargetPackage());
        mapperData.put("ClassName", className);

        boolean userMapper = false;
        String entityPackageName = configuration.getCommonGenerator().getModelGenerator().getTargetPackage();
        ColumnInfo primaryKeyColumnInfo = getPrimaryKeyColumnInfo(tableInfo);

        mapperData.put("baseResultMap", MapperGenerator.baseResultMap(entityPackageName, className, tableInfo));
        mapperData.put("baseColumnList", MapperGenerator.baseColumnList(tableInfo));
        if (!userMapper) {
            mapperData.put("selectPageList", MapperGenerator.selectPageList(entityPackageName, className, tableName, tableInfo));
            mapperData.put("selectByPrimaryKey", MapperGenerator.selectByPrimaryKey(tableName, primaryKeyColumnInfo));
            if (!isView) {
                mapperData.put("deleteByPrimaryKey", MapperGenerator.deleteByPrimaryKey(tableName, primaryKeyColumnInfo));
                mapperData.put("insert", MapperGenerator.insert(entityPackageName, className, tableName, tableInfo));
                mapperData.put("insertSelective", MapperGenerator.insertSelective(entityPackageName, className, tableName, tableInfo));
                mapperData.put("updateByPrimaryKeySelective", MapperGenerator.updateByPrimaryKeySelective(entityPackageName, className, tableName, tableInfo, primaryKeyColumnInfo));
                mapperData.put("updateByPrimaryKey", MapperGenerator.updateByPrimaryKey(entityPackageName, className, tableName, tableInfo, primaryKeyColumnInfo));
                mapperData.put("deleteByIdList", MapperGenerator.deleteByIdList(tableName, primaryKeyColumnInfo));
            }
        }

        String targetProject = configuration.getCommonGenerator().getMapperGenerator().getTargetProject();
        String targetPackage = configuration.getCommonGenerator().getMapperGenerator().getTargetPackage();

        String filePath = FileUtil.getGeneratePath(configuration.getConfigFilePath(), targetProject, targetPackage);
        String fileName = className + "Mapper.xml";
        int type = VelocityUtil.FileTypeEnum.MAPPER.getCode();
        boolean override = configuration.getCommonGenerator().isOverwrite();
        // 生成Mapper文件
        FileUtil.generateToCode(filePath, fileName, mapperData, type, true, override);
        System.out.println("[INFO] Generating " + fileName);
    }
}