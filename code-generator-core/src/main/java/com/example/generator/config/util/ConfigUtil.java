package com.example.generator.config.util;

import com.example.generator.config.Configuration;
import com.example.generator.config.xml.ConfigurationXmlParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/1/23 9:26
 */
public class ConfigUtil {

    public static Configuration getConfiguration(File configurationFile, String projectDir) {
        Configuration configuration = null;
        try {
            if (!configurationFile.exists()) {
                throw new FileNotFoundException(configurationFile.getName());
            }
            List<String> errors = new ArrayList<>();
            ConfigurationXmlParser parser = new ConfigurationXmlParser(errors);
            configuration = parser.parseConfiguration(configurationFile);
            configuration.setProjectDir(projectDir);
            configuration.validate();
        } catch (Exception e) {
            e.printStackTrace();
            return configuration;
        }
        return configuration;
    }

}
