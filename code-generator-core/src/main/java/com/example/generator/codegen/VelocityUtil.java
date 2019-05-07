package com.example.generator.codegen;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.util.Properties;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public class VelocityUtil {

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
}
