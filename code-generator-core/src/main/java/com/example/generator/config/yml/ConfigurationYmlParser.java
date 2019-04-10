package com.example.generator.config.yml;

import com.example.generator.config.Configuration;
import com.example.generator.exception.XMLParserException;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/13 22:23
 */
public class ConfigurationYmlParser {

    private List<String> warnings;
    private List<String> parseErrors;
    private Properties extraProperties;
    private Properties configurationProperties;

    public ConfigurationYmlParser(List<String> warnings) {
        this(null, warnings);
    }

    public ConfigurationYmlParser(Properties extraProperties) {
        this(extraProperties, null);
    }

    public ConfigurationYmlParser(Properties extraProperties, List<String> warnings) {
        if (extraProperties == null) {
            this.extraProperties = new Properties();
        } else {
            this.extraProperties = extraProperties;
        }

        if (warnings == null) {
            this.warnings = new ArrayList<>();
        } else {
            this.warnings = warnings;
        }

        parseErrors = new ArrayList<>();
        configurationProperties = new Properties();
    }

    public Configuration parseConfiguration(File inputFile)
            throws IOException, XMLParserException {

        FileInputStream fis = new FileInputStream(inputFile);

        return parseConfiguration(fis);
    }

    public Configuration parseConfiguration(InputStream inputStream)
            throws IOException, XMLParserException {

        Yaml yaml = new Yaml();

        return yaml.loadAs(inputStream, Configuration.class);
    }
}
