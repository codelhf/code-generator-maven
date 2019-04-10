package com.example.generator.code.task;

import com.example.generator.code.generator.DaoGenerator;
import com.example.generator.code.task.base.BaseTask;
import com.example.generator.code.task.base.FileUtil;
import com.example.generator.code.task.base.FreemarkerUtil;
import com.example.generator.code.task.base.BaseTask;
import com.example.generator.util.StringUtil;
import com.example.generator.util.StringUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:18
 */
public class DaoTask extends BaseTask {

    public DaoTask(String className, boolean isView) {
        super(className, isView);
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

        String title = className;
        String description = className + "实体类";
        daoData.put("Remark", DaoGenerator.generateRemark(title, description));

        boolean useMapper = false;
        if (!useMapper) {
            daoData.put("selectAllListByKeyword", DaoGenerator.selectAllListByKeyword(className));
            daoData.put("selectPageListByKeyword", DaoGenerator.selectPageListByKeyword(className, null));
            daoData.put("selectByPrimaryKey", DaoGenerator.selectByPrimaryKey(className, tableInfo));
            if (!isView) {
                daoData.put("deleteByPrimaryKey", DaoGenerator.deleteByPrimaryKey(tableInfo));
                daoData.put("insert", DaoGenerator.insert(className));
                daoData.put("insertSelective", DaoGenerator.insertSelective(className));
                daoData.put("updateByPrimaryKeySelective", DaoGenerator.updateByPrimaryKeySelective(className));
                daoData.put("updateByPrimaryKey", DaoGenerator.updateByPrimaryKey(className));
            }
        } else {
            daoData.put("baseMapper", isView ? "ViewMapper" : "TableMapper");
            String targetProject = configuration.getCommonGenerator().getDaoGenerator().getTargetProject();
            String targetPackage = configuration.getCommonGenerator().getDaoGenerator().getTargetPackage();

            String filePath = FileUtil.getBasicProjectPath() + targetProject + FileUtil.package2Path(targetPackage) + "/base";
            String fileName = "TableMapper.java";
            int type = FreemarkerUtil.FileTypeEnum.DAO_TABLE_MAPPER.getCode();
            FileUtil.generateToCode(filePath, fileName, daoData, type, true, true);

            fileName = "ViewMapper.java";
            type = FreemarkerUtil.FileTypeEnum.DAO_VIEW_MAPPER.getCode();
            FileUtil.generateToCode(filePath, fileName, daoData, type, true, true);
        }

        String targetProject = configuration.getCommonGenerator().getDaoGenerator().getTargetProject();
        String targetPackage = configuration.getCommonGenerator().getDaoGenerator().getTargetPackage();

        String filePath = FileUtil.getBasicProjectPath() + targetProject + FileUtil.package2Path(targetPackage);
        String fileName = className + "Mapper.java";
        int type = FreemarkerUtil.FileTypeEnum.DAO.getCode();
        boolean override = configuration.getCommonGenerator().isOverwrite();
        // 生成dao文件
        FileUtil.generateToCode(filePath, fileName, daoData, type, true, override);
    }
}
