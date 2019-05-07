package com.example.generator.codegen;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/4/30 12:39
 */
public class FreemarkerUtil {
    //enum本身就是单例的
    public enum FileTypeEnum{
        EXCEPTION_RESOLVER(-4, "0-ExceptionResolver.ftl"),
        RESPONSE_CODE(-3, "0-ResponseCode.ftl"),
        SERVER_RESPONSE(-2, "0-ServerResponse.ftl"),
        SWAGGER_2(-1, "0-Swagger2.ftl"),
        ENTITY(0, "1-Entity.ftl"),
        ENTITY_DTO(1, "1-EntityDto.ftl"),
        MAPPER(2, "1-Mapper.ftl"),
        DAO(3, "1-Dao.ftl"),
        DAO_TABLE_MAPPER(4, "1-1-TableMapper.ftl"),
        DAO_VIEW_MAPPER(5, "1-1-ViewMapper.ftl"),
        SERVICE_IMPL(6, "2-ServiceImpl.ftl"),
        SERVICE(7, "2-Service.ftl"),
        CONTROLLER(8, "2-Controller.ftl");

        private int code;
        private String tpl;

        FileTypeEnum(int code, String tpl) {
            this.code = code;
            this.tpl = tpl;
        }

        public int getCode() {
            return code;
        }

        public String getTpl() {
            return tpl;
        }

        public static VelocityUtil.FileTypeEnum codeOf(int code) {
            for (VelocityUtil.FileTypeEnum fileTypeEnum : VelocityUtil.FileTypeEnum.values()) {
                if (fileTypeEnum.getCode() == code) {
                    return fileTypeEnum;
                }
            }
            throw new RuntimeException("没有找打对应的枚举类!");
        }
    }

    private static String path = new File(VelocityUtil.class.getClassLoader().getResource("ftl").getFile()).getPath();

    private static Configuration configuration;

    public static synchronized Configuration getInstance() {
        if (null == configuration) {
            configuration = new Configuration(Configuration.VERSION_2_3_23);
            try {
                if (path.contains("jar")){
                    configuration.setClassForTemplateLoading(FreemarkerUtil.class, "/ftl");
                } else {
                    configuration.setDirectoryForTemplateLoading(new File(path));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            configuration.setEncoding(Locale.CHINA, "utf-8");
        }
        return configuration;
    }

    /**
     * 获取模板
     *
     * @param type 模板类型
     * @return
     * @throws IOException
     */
    public static Template getTemplate(int type) throws IOException {
        return getInstance().getTemplate(FreemarkerUtil.FileTypeEnum.codeOf(type).getTpl());
    }
}
