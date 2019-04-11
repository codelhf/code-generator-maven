package com.example.generator.code.task;

import com.example.generator.code.task.base.FileUtil;
import com.example.generator.code.task.base.FreemarkerUtil;
import com.example.generator.config.Configuration;
import com.example.generator.util.DateTimeUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/4/3 23:00
 */
public class CommonTask {

    private Configuration configuration;

    public CommonTask(Configuration configuration) {
        this.configuration = configuration;
    }

    public void run() throws IOException, TemplateException {
        // 生成Common填充数据
        Map<String, String> commonData = new HashMap<>();

        String basePackageName = configuration.getCommentGenerator().getBasePackageName();
        String company = configuration.getCommentGenerator().getCompany();
        String author = configuration.getCommentGenerator().getAuthor();
        String createTime = DateTimeUtil.dateToStr(new Date());
        commonData.put("BasePackageName", basePackageName);
        commonData.put("Company", company);
        commonData.put("Author", author);
        commonData.put("CreateTime", createTime);

        String filePath = FileUtil.getSourcePath(configuration.getConfigFilePath()) + FileUtil.package2Path(basePackageName + ".common");

        String fileName = "ExceptionResolver.java";
        int type = FreemarkerUtil.FileTypeEnum.EXCEPTION_RESOLVER.getCode();
        FileUtil.generateToCode(filePath, fileName, commonData, type, true, true);
        System.out.println("Generating common-" + fileName);

        fileName = "ResponseCode.java";
        type = FreemarkerUtil.FileTypeEnum.RESPONSE_CODE.getCode();
        FileUtil.generateToCode(filePath, fileName, commonData, type, true, true);
        System.out.println("Generating common-" + fileName);

        fileName = "ServerResponse.java";
        type = FreemarkerUtil.FileTypeEnum.SERVER_RESPONSE.getCode();
        FileUtil.generateToCode(filePath, fileName, commonData, type, true, true);
        System.out.println("Generating common-" + fileName);

        fileName = "Swagger2.java";
        type = FreemarkerUtil.FileTypeEnum.SWAGGER_2.getCode();
        FileUtil.generateToCode(filePath, fileName, commonData, type, true, true);
        System.out.println("Generating common-" + fileName);
    }
}
