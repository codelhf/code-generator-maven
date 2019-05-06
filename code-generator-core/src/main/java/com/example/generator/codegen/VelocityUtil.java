package com.example.generator.codegen;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.util.Properties;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public class VelocityUtil {

    //enum本身就是单例的
    public enum FileTypeEnum {
        EXCEPTION_RESOLVER(-4, "0-ExceptionResolver.vm"),
        RESPONSE_CODE(-3, "0-ResponseCode.vm"),
        SERVER_RESPONSE(-2, "0-ServerResponse.vm"),
        SWAGGER_2(-1, "0-Swagger2.vm"),
        ENTITY(0, "1-Entity.vm"),
        ENTITY_DTO(1, "1-EntityDto.vm"),
        MAPPER(2, "1-Mapper.vm"),
        DAO(3, "1-Dao.vm"),
        DAO_TABLE_MAPPER(4, "1-1-TableMapper.vm"),
        DAO_VIEW_MAPPER(5, "1-1-ViewMapper.vm"),
        SERVICE_IMPL(6, "2-ServiceImpl.vm"),
        SERVICE(7, "2-Service.vm"),
        CONTROLLER(8, "2-Controller.vm");

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

    private static VelocityEngine velocityEngine = null;

    public static VelocityEngine getInstance() {
//        //初始化参数
//        Properties properties = new Properties();
//        //设置velocity资源加载方式为file
//        properties.setProperty("resource.loader", "file");
//        //设置velocity资源加载方式为file时的处理类
//        properties.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
//        //初始化参数
//        Properties properties = new Properties();
//        //设置velocity资源加载方式为jar
//        properties.setProperty("resource.loader", "jar");
//        //设置velocity资源加载方式为file时的处理类
//        properties.setProperty("jar.resource.loader.class", "org.apache.velocity.runtime.resource.loader.JarResourceLoader");
//        //设置jar包所在的位置
//        properties.setProperty("jar.resource.loader.path", "jar:" + path);
        if (null == velocityEngine) {
            //初始化参数
            Properties properties = new Properties();
            //设置velocity资源加载方式为class
            properties.setProperty("resource.loader", "class");
            //设置velocity资源加载方式为file时的处理类
            properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

            //设置输入输出编码类型。和这次说的解决的问题无关
            properties.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
            properties.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
            //实例化一个VelocityEngine对象
            velocityEngine = new VelocityEngine();
            velocityEngine.init(properties);
        }
        return velocityEngine;
    }

    /**
     * 获取模板
     *
     * @param type 模板类型
     * @return
     */
    public static Template getTemplate(int type) {
        return getInstance().getTemplate("vm/" + FileTypeEnum.codeOf(type).getTpl());
    }
}
