package com.example.generator.codegen;

import freemarker.template.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/4/30 12:39
 */
public class FreemarkerUtil {

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
}
