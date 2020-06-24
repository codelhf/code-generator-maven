package com.example.generator.codegen;

import com.example.generator.config.*;
import com.example.generator.db.ColumnInfo;
import com.example.generator.db.ConnectionUtil;
import com.example.generator.util.DateTimeUtil;
import com.example.generator.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/5/6 23:29
 */
public class SingleInvoker {
    //表名
    private String tableName;
    //类名
    private String className;
    //表中所有的列
    private List<ColumnInfo> fullColumn;
    //是否为视图, 针对单表
    private boolean isView;
    //指定列生成主键
    private GeneratedKey generatedKey;
    //重写列集合
    private List<ColumnOverride> columnOverrideList;

    private Configuration configuration;

    private ConnectionUtil connectionUtil;

    public void execute() throws IOException, SQLException {
        //检验参数
        checkBeforeExecute();
        //初始化表数据
        initTableInfo();
        //所有模板数据
        Map<String, Object> data = new HashMap<>();
        // TODO: 2019/5/8 模板之间的依赖需要提前获取所有的template的配置以template名字作为key
        // TODO: 2019/5/8 放在上面防止下面key被覆盖
        List<TemplateConfig> templateList = configuration.getTemplateList();
        for (TemplateConfig template: templateList) {
            data.put(template.getName(), template);
        }

        CommentGenerator commentGenerator = configuration.getCommentGenerator();
        //代码生成信息
        data.put("company", commentGenerator.getCompany());
        data.put("author", commentGenerator.getAuthor());
        if (StringUtil.isNotBlank(commentGenerator.getDateFormat())) {
            data.put("createTime", DateTimeUtil.dateToStr(new Date(), commentGenerator.getDateFormat()));
        } else {
            data.put("createTime", DateTimeUtil.dateToStr(new Date()));
        }
        //代码生成策略
        data.put("httpPrefix", commentGenerator.getHttpPrefix());
        data.put("ResponseClass", commentGenerator.getResponseClass());
        data.put("generateRemark", commentGenerator.isGenerateRemark());
        data.put("generateSwagger", commentGenerator.isGenerateSwagger());
        data.put("commonMapper", commentGenerator.isCommonMapper());
        //代码文件编码
        String fileEncode = commentGenerator.getFileEncode();

        //实体表信息
        data.put("isView", isView);
        data.put("ClassName", className);
        data.put("className", StringUtil.firstToLowerCase(className));
        data.put("tableName", tableName);
        data.put("fullColumn", fullColumn);
        data.put("pkColumn", getPrimaryKeyColumnInfo(fullColumn));

        //模板工具
        data.put("StringUtil", StringUtil.class);

        String projectDir = configuration.getProjectDir();
        VelocityEngine velocityEngine = VelocityUtil.getInstance();
        for (TemplateConfig template: templateList) {
            Template tpl = velocityEngine.getTemplate(template.getTemplate());
            //设置包名,设置Template获取当前模板配置
            data.put("Template", template);
            //文件生成路径支持相对路径和绝对路径
            String filePath = FileUtil.getGeneratePath(template.getDirectory(), template.getPackageName(), projectDir);
            String fileName = null;
            if (template.isCommon()) {
                //后缀加文件格式
                fileName = template.getSuffix() + "." + template.getFileType();
            } else {
                //表名加后缀加文件格式
                fileName = className + template.getSuffix() + "." + template.getFileType();
            }
            //文件名包含 / 最后一个 / 后面为生成文件名
            if (fileName.contains("/")) {
                int index = fileName.lastIndexOf("/");
                filePath = filePath + StringUtil.firstToLowerCase(fileName.substring(0, index));
                fileName = fileName.substring(index + 1);
            }
            if (template.isGenerate()) {
                FileUtil.generateToCode(filePath, fileName, fileEncode, tpl, data, template.isOverride());
            }
        }
        //清空数据
        data.clear();
    }

    //执行前检验
    private void checkBeforeExecute() {
        if (configuration == null) {
            throw new RuntimeException("configuration can not be null");
        }
        if (connectionUtil == null) {
            throw new RuntimeException("jdbcConnection can not be null");
        }
        if (StringUtil.isBlank(tableName)) {
            throw new RuntimeException("Expect table's name, but get a blank String.");
        }
        if (StringUtil.isBlank(className)) {
            className = StringUtil.tableName2ClassName(tableName);
        }
    }

    private void initTableInfo() throws SQLException {
        List<ColumnInfo> columnInfoList = connectionUtil.getMetaData(tableName);
        //修改用户自定义配置
        for (ColumnInfo columnInfo: columnInfoList) {
            String column = columnInfo.getColumnName();
            if (generatedKey != null && column.equals(generatedKey.getColumn())) {
                columnInfo.setPrimaryKey(true);
            }
            if (columnOverrideList != null) {
                for (ColumnOverride columnOverride: columnOverrideList) {
                    if (column.equals(columnOverride.getColumnName())) {
                        columnInfo.setJdbcType(columnOverride.getJdbcType());
                        // TODO: 2019/11/8 javaTypeResolver
//                        columnInfo.setJavaType(TypeUtil.jdbcTypeToJavaType(columnOverride.getJdbcType()));
                        break;
                    }
                }
            }
        }
        this.fullColumn = columnInfoList;
    }

    private ColumnInfo getPrimaryKeyColumnInfo(List<ColumnInfo> list) {
        for (ColumnInfo columnInfo : list) {
            if (columnInfo.isPrimaryKey()) {
                return columnInfo;
            }
        }
        //设置默认主键, 并非表中真实存在的
        return new ColumnInfo("id", 1, true, "");
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setFullColumn(List<ColumnInfo> fullColumn) {
        this.fullColumn = fullColumn;
    }

    public boolean isView() {
        return isView;
    }

    public void setView(boolean view) {
        isView = view;
    }

    public GeneratedKey getGeneratedKey() {
        return generatedKey;
    }

    public void setGeneratedKey(GeneratedKey generatedKey) {
        this.generatedKey = generatedKey;
    }

    public List<ColumnOverride> getColumnOverrideList() {
        return columnOverrideList;
    }

    public void setColumnOverrideList(List<ColumnOverride> columnOverrideList) {
        this.columnOverrideList = columnOverrideList;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public ConnectionUtil getConnectionUtil() {
        return connectionUtil;
    }

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }
}
