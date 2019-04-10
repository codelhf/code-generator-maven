package com.example.generator.config.util;

import com.example.generator.code.task.base.FileUtil;
import com.example.generator.config.Configuration;
import com.example.generator.config.xml.ConfigurationXmlParser;
import com.example.generator.config.yml.ConfigurationYmlParser;
import com.example.generator.config.xml.ConfigurationXmlParser;
import com.example.generator.config.yml.ConfigurationYmlParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/1/23 9:26
 */
public class ConfigUtil {

    private final static String GENERATOR_YML = "generatorConfig.yml";
    private final static String GENERATOR_XML = "generatorConfig.xml";
    private static Configuration configuration;
    private static File configurationFile = new File(FileUtil.getResourcePath() + GENERATOR_XML);

    public static Configuration getConfiguration() {
        if (null == configuration) {
            getConfiguration(configurationFile);
        }
        return configuration;
    }

    public static Configuration getConfiguration(File filePath) {

        InputStream inputStream = null;
        try {
            if (!filePath.exists()) {
                throw new FileNotFoundException(filePath.getName());
            }
            configurationFile = filePath;
            String fileName = configurationFile.getName();
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            inputStream = new FileInputStream(configurationFile);
            List<String> errors = new ArrayList<>();
            if (".xml".equals(fileType)) {
                ConfigurationXmlParser parser = new ConfigurationXmlParser(errors);
                configuration = parser.parseConfiguration(inputStream);
            } else {
                ConfigurationYmlParser parser = new ConfigurationYmlParser(errors);
                configuration = parser.parseConfiguration(inputStream);
            }
            configuration.validate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return configuration;
    }

}
