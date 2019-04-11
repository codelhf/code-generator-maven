package com.example.generator.code.task;

import com.example.generator.code.generator.DaoGenerator;
import com.example.generator.code.task.base.BaseTask;
import com.example.generator.code.task.base.FileUtil;
import com.example.generator.code.task.base.FreemarkerUtil;
import com.example.generator.config.Configuration;
import com.example.generator.config.DataSource;
import com.example.generator.db.ColumnInfo;
import com.example.generator.util.StringUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.ArrayList;
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
    public void run() throws IOException, TemplateException {
        // 生成Dao填充数据
        System.out.println("Generating " + className + "Dao.java");
        Map<String, String> daoData = new HashMap<>();

        daoData.put("DaoPackageName", configuration.getCommonGenerator().getDaoGenerator().getTargetPackage());
        daoData.put("EntityPackageName",configuration.getCommonGenerator().getModelGenerator().getTargetPackage());
        daoData.put("ClassName", className);
        daoData.put("className", StringUtil.firstToLowerCase(className));

        String title = className + "Mapper";
        String description = className + "实体类";
        daoData.put("Remark", DaoGenerator.generateRemark(title, description, configuration));

        boolean useMapper = false;
        ColumnInfo primaryKeyColumn = getPrimaryKeyColumnInfo(tableInfo);
        if (!useMapper) {
            daoData.put("selectPageList", DaoGenerator.selectPageList(className));
            daoData.put("selectByPrimaryKey", DaoGenerator.selectByPrimaryKey(className, primaryKeyColumn));
            if (!isView) {
                daoData.put("deleteByPrimaryKey", DaoGenerator.deleteByPrimaryKey(primaryKeyColumn));
                daoData.put("insert", DaoGenerator.insert(className));
                daoData.put("insertSelective", DaoGenerator.insertSelective(className));
                daoData.put("updateByPrimaryKeySelective", DaoGenerator.updateByPrimaryKeySelective(className));
                daoData.put("updateByPrimaryKey", DaoGenerator.updateByPrimaryKey(className));
                daoData.put("deleteByIdList", DaoGenerator.deleteByIdList(primaryKeyColumn));
            }
        } else {
            daoData.put("baseMapper", isView ? "ViewMapper" : "TableMapper");
            String targetProject = configuration.getCommonGenerator().getDaoGenerator().getTargetProject();
            String targetPackage = configuration.getCommonGenerator().getDaoGenerator().getTargetPackage();

            String filePath = FileUtil.getGeneratePath(configuration.getConfigFilePath(), targetProject, targetPackage);
            String fileName = "TableMapper.java";
            int type = FreemarkerUtil.FileTypeEnum.DAO_TABLE_MAPPER.getCode();
            FileUtil.generateToCode(filePath, fileName, daoData, type, true, true);

            fileName = "ViewMapper.java";
            type = FreemarkerUtil.FileTypeEnum.DAO_VIEW_MAPPER.getCode();
            FileUtil.generateToCode(filePath, fileName, daoData, type, true, true);
        }

        String targetProject = configuration.getCommonGenerator().getDaoGenerator().getTargetProject();
        String targetPackage = configuration.getCommonGenerator().getDaoGenerator().getTargetPackage();

        String filePath = FileUtil.getGeneratePath(configuration.getConfigFilePath(), targetProject, targetPackage);
        String fileName = className + "Mapper.java";
        int type = FreemarkerUtil.FileTypeEnum.DAO.getCode();
        boolean override = configuration.getCommonGenerator().isOverwrite();
        // 生成dao文件
        FileUtil.generateToCode(filePath, fileName, daoData, type, true, override);
    }
}
