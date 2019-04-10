package com.example.generator.code.task;

import com.example.generator.code.generator.MapperGenerator;
import com.example.generator.code.task.base.BaseTask;
import com.example.generator.code.task.base.FileUtil;
import com.example.generator.code.task.base.FreemarkerUtil;
import com.example.generator.db.ColumnInfo;
import com.example.generator.code.generator.MapperGenerator;
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
 * @CreateTime: 2019/3/16 21:17
 */
public class MapperTask extends BaseTask {

    /**
     * 单表Mapper
     */
    public MapperTask(String className, String tableName, List<ColumnInfo> tableInfo, boolean isView) {
        this(tableName, className, null, null, null, tableInfo, null, isView);
    }

    /**
     * 一对多Mapper
     */
    public MapperTask(String tableName, String className, String parentTableName, String parentClassName,
                      String foreignKey, List<ColumnInfo> tableInfo, List<ColumnInfo> parentTableInfo, boolean isView) {
        this(tableName, className, parentTableName, parentClassName, foreignKey, null, null, tableInfo, parentTableInfo, isView);
    }

    /**
     * 多对多Mapper
     */
    public MapperTask(String tableName, String className, String parentTableName, String parentClassName,
                      String foreignKey, String parentForeignKey, String relationalTableName,
                      List<ColumnInfo> tableInfo, List<ColumnInfo> parentTableInfo, boolean isView) {
        super(tableName, className, parentTableName, parentClassName, foreignKey, parentForeignKey, relationalTableName, tableInfo, parentTableInfo, isView);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Mapper填充数据
        System.out.println("Generating " + className + "Mapper.xml");
        Map<String, String> mapperData = new HashMap<>();

        mapperData.put("DaoPackageName", configuration.getCommonGenerator().getDaoGenerator().getTargetPackage());
        mapperData.put("ClassName", className);

        boolean userMapper = false;
        String entityPackageName = configuration.getCommonGenerator().getModelGenerator().getTargetPackage();
        ColumnInfo primaryKeyColumnInfo = getPrimaryKeyColumnInfo(tableInfo);

        if (!StringUtil.isBlank(parentForeignKey)) { // 多对多
            mapperData.put("baseResultMap", MapperGenerator.baseResultMap(entityPackageName, className, tableInfo));
            mapperData.put("baseColumnList", MapperGenerator.baseColumnList(tableInfo));
        } else if (!StringUtil.isBlank(foreignKey)) { // 一对多
            mapperData.put("baseResultMap", MapperGenerator.baseResultMap(entityPackageName, className, tableInfo));
            mapperData.put("baseColumnList", MapperGenerator.baseColumnList(tableInfo));
        } else { // 单表
            mapperData.put("baseResultMap", MapperGenerator.baseResultMap(entityPackageName, className, tableInfo));
            mapperData.put("baseColumnList", MapperGenerator.baseColumnList(tableInfo));
            if (!userMapper) {
                mapperData.put("selectAllListByKeyword", MapperGenerator.selectAllListByKeyword(tableName));
                mapperData.put("selectPageListByKeyword", MapperGenerator.selectPageListByKeyword(entityPackageName, className, tableName, tableInfo));
                mapperData.put("selectByPrimaryKey", MapperGenerator.selectByPrimaryKey(tableName, primaryKeyColumnInfo));
                if (!isView) {
                    mapperData.put("deleteByPrimaryKey", MapperGenerator.deleteByPrimaryKey(tableName, primaryKeyColumnInfo));
                    mapperData.put("insert", MapperGenerator.insert(entityPackageName, className, tableName, tableInfo));
                    mapperData.put("insertSelective", MapperGenerator.insertSelective(entityPackageName, className, tableName, tableInfo));
                    mapperData.put("updateByPrimaryKeySelective", MapperGenerator.updateByPrimaryKeySelective(entityPackageName, className, tableName, tableInfo, primaryKeyColumnInfo));
                    mapperData.put("updateByPrimaryKey", MapperGenerator.updateByPrimaryKey(entityPackageName, className, tableName, tableInfo, primaryKeyColumnInfo));
                }
            }
        }

        String targetProject = configuration.getCommonGenerator().getMapperGenerator().getTargetProject();
        String targetPackage = configuration.getCommonGenerator().getMapperGenerator().getTargetPackage();

        String filePath = FileUtil.getBasicProjectPath() + targetProject + FileUtil.package2Path(targetPackage);
        String fileName = className + "Mapper.xml";
        int type = FreemarkerUtil.FileTypeEnum.MAPPER.getCode();
        boolean override = configuration.getCommonGenerator().isOverwrite();
        // 生成Mapper文件
        FileUtil.generateToCode(filePath, fileName, mapperData, type, true, override);
    }

}
