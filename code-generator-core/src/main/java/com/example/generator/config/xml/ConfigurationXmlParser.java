package com.example.generator.config.xml;

import com.example.generator.config.*;
import com.example.generator.exception.XMLParserException;
import com.example.generator.util.StringUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/13 22:23
 */
public class ConfigurationXmlParser {

    private List<String> warnings;
    private List<String> parseErrors;
    private Properties extraProperties;
    private Properties configurationProperties;

    public ConfigurationXmlParser(List<String> warnings) {
        this(null, warnings);
    }

    public ConfigurationXmlParser(Properties extraProperties) {
        this(extraProperties, null);
    }

    public ConfigurationXmlParser(Properties extraProperties, List<String> warnings) {
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
            throws XMLParserException {

        InputSource is = new InputSource(inputStream);

        return parseConfiguration(is);
    }

    private Configuration parseConfiguration(InputSource InputSource)
            throws XMLParserException {
        parseErrors.clear();
        SAXReader reader = new SAXReader();
        Document document = null;

        try {
            document = reader.read(InputSource);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        if (document == null || parseErrors.size() > 0) {
            throw new XMLParserException(parseErrors);
        }

        Element rootElement = document.getRootElement();
        return parseConfiguration(rootElement);
//        DocumentType docType = document.getDocType();
//        if (rootElement.getNodeType() == Element.ELEMENT_NODE
//                && docType.getPublicID().equals(XmlConstants.MYBATIS_GENERATOR_CONFIG_PUBLIC_ID)) {
//            return parseConfiguration(rootElement);
//        } else {
//            throw new XMLParserException(Messages.getString("RuntimeError.5"));
//        }
    }

    private Configuration parseConfiguration(Element rootElement) {

        Configuration configuration = new Configuration();

        List<Element> childElements = rootElement.elements();
        for (Element childNode : childElements) {
            if (childNode.getNodeType() != Element.ELEMENT_NODE) {
                continue;
            }
            String childNodeName = childNode.getName();
            if ("properties".equals(childNodeName)) {
                parseProperties(configuration, childNode);
            } else if ("jdbcConnection".equals(childNodeName)) {
                parseJdbcConnection(configuration, childNode);
            } else if ("javaTypeResolver".equals(childNodeName)) {
                parseJavaTypeResolver(configuration, childNode);
            } else if ("commentGenerator".equals(childNodeName)) {
                parseCommentGenerator(configuration, childNode);
            } else if ("templates".equals(childNodeName)) {
                parseTemplates(configuration, childNode);
            } else if ("tables".equals(childNodeName)) {
                parseTables(configuration, childNode);
            } else if ("views".equals(childNodeName)) {
                parseViews(configuration, childNode);
            }
        }
        return configuration;
    }

    private void parseProperties(Configuration configuration, Element node) {
        Properties attributes = XmlPropertyHolder.parseAttributes(node);
        String resource = attributes.getProperty("resource");
        configuration.setResource(resource);
    }

    private void parseJdbcConnection(Configuration configuration, Element node) {
        Map<String, String> map = XmlPropertyHolder.parseChildProperty(node.elements());
        JdbcConnection dataSource = new JdbcConnection();
        String driverLocation = map.get("driverLocation");
        dataSource.setDriverLocation(driverLocation);

        String driverClass = map.get("driverClass");
        dataSource.setDriverClass(driverClass);

        String url = map.get("url");
        dataSource.setUrl(url);

        String username = map.get("username");
        dataSource.setUsername(username);

        String password = map.get("password");
        dataSource.setPassword(password);

        configuration.setJdbcConnection(dataSource);
    }

    private void parseJavaTypeResolver(Configuration configuration, Element node) {
        JavaTypeResolver javaTypeResolver = new JavaTypeResolver();
        Map<String, String> map = XmlPropertyHolder.parseChildProperty(node.elements());
        String autoDelimitKeywords = map.get("autoDelimitKeywords");
        javaTypeResolver.setAutoDelimitKeywords(StringUtil.isTrue(autoDelimitKeywords));

        String beginningDelimiter = map.get("beginningDelimiter");
        javaTypeResolver.setBeginningDelimiter(beginningDelimiter);

        String endingDelimiter = map.get("endingDelimiter");
        javaTypeResolver.setEndingDelimiter(endingDelimiter);

        String forceBigDecimals = map.get("forceBigDecimals");
        javaTypeResolver.setForceBigDecimals(StringUtil.isTrue(forceBigDecimals));

        configuration.setJavaTypeResolver(javaTypeResolver);
    }

    private void parseCommentGenerator(Configuration configuration, Element node) {
        CommentGenerator commentGenerator = new CommentGenerator();
        Map<String, String> map = XmlPropertyHolder.parseChildProperty(node.elements());
        String author = map.get("author");
        commentGenerator.setAuthor(author);

        String company = map.get("company");
        commentGenerator.setCompany(company);

        String httpPrefix = map.get("httpPrefix");
        if (StringUtil.isNotBlank(httpPrefix)) {
            commentGenerator.setHttpPrefix(httpPrefix);
        }

        String responseClass = map.get("responseClass");
        if (StringUtil.isNotBlank(responseClass)) {
            commentGenerator.setResponseClass(responseClass);
        }

        String suppressAllComments = map.get("suppressAllComments");
        commentGenerator.setSuppressAllComments(StringUtil.isTrue(suppressAllComments));

        String suppressDate = map.get("suppressDate");
        commentGenerator.setSuppressDate(StringUtil.isTrue(suppressDate));

        String dateFormat = map.get("dateFormat");
        if (StringUtil.isNotBlank(dateFormat)) {
            commentGenerator.setDateFormat(dateFormat);
        }

        String addRemarkComments = map.get("addRemarkComments");
        commentGenerator.setAddRemarkComments(StringUtil.isTrue(addRemarkComments));

        configuration.setCommentGenerator(commentGenerator);
    }

    private void parseTemplates(Configuration configuration, Element node) {
        List<TemplateConfiguration> templateList = new ArrayList<>();
        List<Element> nodeList = node.elements();
        if (nodeList.size() > 0) {
            for (Element childNode: node.elements()) {
                if (childNode.getNodeType() != Element.ELEMENT_NODE) {
                    continue;
                }
                String childNodeName = childNode.getName();
                if ("template".equals(childNodeName)) {
                    templateList.add(parseTemplate(childNode));
                }
            }
        }
        configuration.setTemplateList(templateList);
    }

    private TemplateConfiguration parseTemplate(Element templateNode) {
        TemplateConfiguration templateConfiguration = new TemplateConfiguration();
        Properties properties = XmlPropertyHolder.parseAttributes(templateNode);

        String name = properties.getProperty("name");
        templateConfiguration.setName(name);

        String template = properties.getProperty("template");
        templateConfiguration.setTemplate(template);

        String suffix = properties.getProperty("suffix");
        templateConfiguration.setSuffix(suffix);

        String fileType = properties.getProperty("fileType");
        templateConfiguration.setFileType(fileType);

        String directory = properties.getProperty("directory");
        templateConfiguration.setDirectory(directory);

        String packageName = properties.getProperty("packageName");
        templateConfiguration.setPackageName(packageName);

        String common = properties.getProperty("common");
        templateConfiguration.setCommon(StringUtil.isTrue(common));

        String isGenerate = properties.getProperty("isGenerate");
        templateConfiguration.setGenerate(StringUtil.isTrue(isGenerate));

        String override = properties.getProperty("override");
        templateConfiguration.setOverride(StringUtil.isTrue(override));

        return templateConfiguration;
    }

    private void parseTables(Configuration configuration, Element node) {
        List<TableConfiguration> tableList = new ArrayList<>();
        List<Element> nodeList = node.elements();
        if (nodeList.size() > 0) {
            for (Element childNode : nodeList) {
                if (childNode.getNodeType() != Element.ELEMENT_NODE) {
                    continue;
                }
                String childNodeName = childNode.getName();
                if ("table".equals(childNodeName)) {
                    tableList.add(parseTable(childNode));
                }
            }
        }
        configuration.setTableList(tableList);
    }

    private TableConfiguration parseTable(Element table) {
        TableConfiguration tableConfiguration = new TableConfiguration();
        Properties attributes = XmlPropertyHolder.parseAttributes(table);
        String tableName = attributes.getProperty("tableName");
        tableConfiguration.setTableName(tableName);

        String domainName = attributes.getProperty("domainName");
        tableConfiguration.setDomainName(domainName);

        List<Element> nodeList = table.elements();
        if (nodeList.size() > 0) {
            List<ColumnOverride> columnOverrideList = new ArrayList<>();
            for (Element childNode : nodeList) {
                if (childNode.getNodeType() != Element.ELEMENT_NODE) {
                    continue;
                }
                String childNodeName = childNode.getName();
                if ("generatedKey".equals(childNodeName)) {
                    tableConfiguration.setGeneratedKey(parseGeneratedKey(childNode));
                }
                if ("columnOverride".equals(childNodeName)) {
                    columnOverrideList.add(parseColumnOverride(childNode));
                }
            }
            tableConfiguration.setColumnOverrides(columnOverrideList);
        }
        return tableConfiguration;
    }

    private GeneratedKey parseGeneratedKey(Element generatedKeyNode) {
        Properties attributes = XmlPropertyHolder.parseAttributes(generatedKeyNode);
        String column = attributes.getProperty("column");
        String runtimeSqlStatement = attributes.getProperty("runtimeSqlStatement");
        boolean isIdentity = StringUtil.isTrue(attributes.getProperty("isIdentity"));
        String type = attributes.getProperty("type");
        return new GeneratedKey(column, runtimeSqlStatement, isIdentity, type);
    }

    private void parseViews(Configuration configuration, Element node) {
        List<ViewConfiguration> viewConfigurationList = new ArrayList<>();
        List<Element> nodeList = node.elements();
        if (nodeList.size() > 0) {
            for (Element childNode : nodeList) {
                if (childNode.getNodeType() != Element.ELEMENT_NODE) {
                    continue;
                }
                String childNodeName = childNode.getName();
                if ("view".equals(childNodeName)) {
                    viewConfigurationList.add(parseView(childNode));
                }
            }
        }
        configuration.setViewList(viewConfigurationList);
    }

    private ViewConfiguration parseView(Element view) {
        ViewConfiguration viewConfiguration = new ViewConfiguration();
        Properties attributes = XmlPropertyHolder.parseAttributes(view);
        String tableName = attributes.getProperty("viewName");
        viewConfiguration.setViewName(tableName);

        String domainName = attributes.getProperty("domainName");
        viewConfiguration.setDomainName(domainName);

        List<Element> nodeList = view.elements();
        if (nodeList.size() > 0) {
            List<ColumnOverride> columnOverrideList = new ArrayList<>();
            for (Element childNode : nodeList) {
                if (childNode.getNodeType() != Element.ELEMENT_NODE) {
                    continue;
                }
                String childNodeName = childNode.getName();
                if ("columnOverride".equals(childNodeName)) {
                    columnOverrideList.add(parseColumnOverride(childNode));
                }
            }
            viewConfiguration.setColumnOverrides(columnOverrideList);
        }
        return viewConfiguration;
    }


    private ColumnOverride parseColumnOverride(Element columnOverrideNode) {
        Properties attributes = XmlPropertyHolder.parseAttributes(columnOverrideNode);
        String column = attributes.getProperty("columnName");
        ColumnOverride columnOverride = new ColumnOverride(column);
        String javaProperty = attributes.getProperty("javaProperty");
        columnOverride.setJavaProperty(javaProperty);

        String javaType = attributes.getProperty("javaType");
        columnOverride.setJavaType(javaType);

        String jdbcType = attributes.getProperty("jdbcType");
        columnOverride.setJdbcType(jdbcType);

        String typeHandler = attributes.getProperty("typeHandler");
        columnOverride.setTypeHandler(typeHandler);

        String isGeneratedAlways = attributes.getProperty("isGeneratedAlways");
        columnOverride.setGeneratedAlways(StringUtil.isTrue(isGeneratedAlways));

        return columnOverride;
    }
}
