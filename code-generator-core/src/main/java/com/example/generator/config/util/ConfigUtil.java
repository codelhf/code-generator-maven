package com.example.generator.config.util;

import com.example.generator.config.Configuration;
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

    public Configuration getConfiguration(File configurationFile) {
        Configuration configuration = null;
        try {
            if (!configurationFile.exists()) {
                throw new FileNotFoundException(configurationFile.getName());
            }
            String fileName = configurationFile.getName();
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            List<String> errors = new ArrayList<>();
            if (".xml".equals(fileType)) {
                ConfigurationXmlParser parser = new ConfigurationXmlParser(errors);
                configuration = parser.parseConfiguration(configurationFile);
            } else {
                ConfigurationYmlParser parser = new ConfigurationYmlParser(errors);
                configuration = parser.parseConfiguration(configurationFile);
            }
            configuration.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configuration;
    }

}
