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

//    private static String path = System.getProperty("user.dir");

    private static final String path = "C:/resources/";

    private static Configuration configuration;

    public static synchronized Configuration getInstance() {
        if (null == configuration) {
            configuration = new Configuration(Configuration.VERSION_2_3_23);
            try {
                if (!path.contains("jar")){
                    configuration.setDirectoryForTemplateLoading(new File(path));
                } else {
                    configuration.setClassForTemplateLoading(FreemarkerUtil.class, "/template/Default/ftl");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            configuration.setEncoding(Locale.CHINA, "utf-8");
        }
        return configuration;
    }
}
