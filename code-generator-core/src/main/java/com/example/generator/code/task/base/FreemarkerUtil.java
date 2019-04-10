package com.example.generator.code.task.base;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public class FreemarkerUtil {

    //enum本身就是单例的
    public enum FileTypeEnum{
        EXCEPTION_RESOLVER(-4, "ExceptionResolver.ftl"),
        RESPONSE_CODE(-3, "ResponseCode.ftl"),
        SERVER_RESPONSE(-2, "ServerResponse.ftl"),
        SWAGGER_2(-1, "Swagger2.ftl"),
        ENTITY(0, "Entity.ftl"),
        ENTITY_DTO(1, "EntityDto.ftl"),
        MAPPER(2, "Mapper.ftl"),
        DAO(3, "Dao.ftl"),
        DAO_TABLE_MAPPER(4, "TableMapper.ftl"),
        DAO_VIEW_MAPPER(5, "ViewMapper.ftl"),
        SERVICE_IMPL(6, "ServiceImpl.ftl"),
        SERVICE(7, "Service.ftl"),
        CONTROLLER(8, "Controller.ftl");

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

        public static FileTypeEnum codeOf(int code) {
            for (FileTypeEnum fileTypeEnum : FileTypeEnum.values()) {
                if (fileTypeEnum.getCode() == code) {
                    return fileTypeEnum;
                }
            }
            throw new RuntimeException("没有找打对应的枚举类!");
        }
    }

    private static String path = new File(FreemarkerUtil.class.getClassLoader().getResource("tpl").getFile()).getPath();

    private static Configuration configuration;

    public static synchronized Configuration getInstance() {
        if (null == configuration) {
            configuration = new Configuration(Configuration.VERSION_2_3_23);
            try {
                if (path.contains("jar")){
                    configuration.setClassForTemplateLoading(FreemarkerUtil.class, "/tpl");
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
        return getInstance().getTemplate(FileTypeEnum.codeOf(type).getTpl());
    }
}
