package com.example.generator.config.xml;

import com.example.generator.config.*;
import com.example.generator.exception.XMLParserException;
import com.example.generator.util.Messages;
import com.example.generator.util.StringUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentType;
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
        DocumentType docType = document.getDocType();
        if (rootElement.getNodeType() == Element.ELEMENT_NODE
                && docType.getPublicID().equals(XmlConstants.MYBATIS_GENERATOR_CONFIG_PUBLIC_ID)) {
            return parseConfiguration(rootElement);
        } else {
            throw new XMLParserException(Messages.getString("RuntimeError.5"));
        }
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
            } else if ("commentGenerator".equals(childNodeName)) {
                parseCommentGenerator(configuration, childNode);
            } else if ("dataSource".equals(childNodeName)) {
                parseDataSource(configuration, childNode);
            } else if ("javaTypeResolver".equals(childNodeName)) {
                parseJavaTypeResolver(configuration, childNode);
            } else if ("serviceGenerator".equals(childNodeName)) {
                parseServiceGenerator(configuration, childNode);
            } else if ("commonGenerator".equals(childNodeName)) {
                parseCommonGenerator(configuration, childNode);
            } else if ("tablesConfiguration".equals(childNodeName)) {
                parseTablesConfiguration(configuration, childNode);
            } else if ("viewsConfiguration".equals(childNodeName)) {
                parseViewsConfiguration(configuration, childNode);
            }
        }
        return configuration;
    }

    private void parseProperties(Configuration configuration, Element node) {
        Properties attributes = XmlPropertyHolder.parseAttributes(node);
        String resource = attributes.getProperty("resource");
        if (StringUtil.isNotBlank(resource)) {
            configuration.setResource(resource);
        }
    }

    private void parseCommentGenerator(Configuration configuration, Element node) {
        Map<String, String> map = XmlPropertyHolder.parseChildProperty(node.elements());
        CommentGenerator commentGenerator = new CommentGenerator();
        String author = map.get("author");
        commentGenerator.setAuthor(author);
        String company = map.get("company");
        commentGenerator.setCompany(company);
        String basePackageName = map.get("basePackageName");
        commentGenerator.setBasePackageName(basePackageName);
        String suppressAllComments = map.get("suppressAllComments");
        if (StringUtil.isNotBlank(suppressAllComments)) {
            commentGenerator.setSuppressAllComments(Boolean.valueOf(suppressAllComments));
        }
        String suppressDate = map.get("suppressDate");
        if (StringUtil.isNotBlank(suppressDate)) {
            commentGenerator.setSuppressDate(Boolean.valueOf(suppressDate));
        }
        String dateFormat = map.get("dateFormat");
        if (StringUtil.isNotBlank(dateFormat)) {
            commentGenerator.setDateFormat(dateFormat);
        }
        String addRemarkComments = map.get("addRemarkComments");
        if (StringUtil.isNotBlank(addRemarkComments)) {
            commentGenerator.setAddRemarkComments(Boolean.valueOf(addRemarkComments));
        }
        configuration.setCommentGenerator(commentGenerator);
    }

    private void parseDataSource(Configuration configuration, Element node) {
        Map<String, String> map = XmlPropertyHolder.parseChildProperty(node.elements());
        DataSource dataSource = new DataSource();
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
        configuration.setDataSource(dataSource);
    }

    private void parseJavaTypeResolver(Configuration configuration, Element node) {
        Map<String, String> map = XmlPropertyHolder.parseChildProperty(node.elements());
        JavaTypeResolver javaTypeResolver = new JavaTypeResolver();
        String autoDelimitKeywords = map.get("autoDelimitKeywords");
        if (StringUtil.isNotBlank(autoDelimitKeywords)) {
            javaTypeResolver.setAutoDelimitKeywords(Boolean.valueOf(autoDelimitKeywords));
            String beginningDelimiter = map.get("beginningDelimiter");
            if (StringUtil.isNotBlank(beginningDelimiter)) {
                javaTypeResolver.setBeginningDelimiter(beginningDelimiter);
            }
            String endingDelimiter = map.get("endingDelimiter");
            if (StringUtil.isNotBlank(endingDelimiter)) {
                javaTypeResolver.setEndingDelimiter(endingDelimiter);
            }
        }
        String forceBigDecimals = map.get("forceBigDecimals");
        if (StringUtil.isNotBlank(forceBigDecimals)) {
            javaTypeResolver.setForceBigDecimals(Boolean.valueOf(forceBigDecimals));
        }
        configuration.setJavaTypeResolver(javaTypeResolver);
    }

    private void parseServiceGenerator(Configuration configuration, Element node) {
        Map<String, String> map = XmlPropertyHolder.parseChildProperty(node.elements());
        ServiceGenerator serviceGenerator = new ServiceGenerator();
        String generator = map.get("generator");
        if (StringUtil.isNotBlank(generator)) {
            serviceGenerator.setGenerator(Boolean.valueOf(generator));
        }
        String enableSubPackages = map.get("enableSubPackages");
        if (StringUtil.isNotBlank(enableSubPackages)) {
            serviceGenerator.setEnableSubPackages(Boolean.valueOf(enableSubPackages));
        }
        String targetProject = map.get("targetProject");
        serviceGenerator.setTargetProject(targetProject);
        String controller = map.get("controller");
        serviceGenerator.setController(controller);
        String service = map.get("service");
        serviceGenerator.setService(service);
        String serviceImpl = map.get("serviceImpl");
        serviceGenerator.setServiceImpl(serviceImpl);
        configuration.setServiceGenerator(serviceGenerator);
    }

    private void parseCommonGenerator(Configuration configuration, Element node) {
        Map<String, String> map = XmlPropertyHolder.parseChildProperty(node.elements());
        CommonGenerator commonGenerator = new CommonGenerator();
        String overwrite = map.get("overwrite");
        if (StringUtil.isNotBlank(overwrite)) {
            commonGenerator.setOverwrite(Boolean.valueOf(overwrite));
        }
        String enableSubPackages = map.get("enableSubPackages");
        if (StringUtil.isNotBlank(enableSubPackages)) {
            commonGenerator.setEnableSubPackages(Boolean.valueOf(enableSubPackages));
        }
        List<Element> nodeList = node.elements();
        for (Element childNode : nodeList) {
            if (childNode.getNodeType() != Element.ELEMENT_NODE) {
                continue;
            }
            String childNodeName = childNode.getName();
            if ("dtoGenerator".equals(childNodeName)) {
                parseDtoGenerator(commonGenerator, childNode);
            } else if ("modelGenerator".equals(childNodeName)) {
                parseModelGenerator(commonGenerator, childNode);
            } else if ("daoGenerator".equals(childNodeName)) {
                parseDaoGenerator(commonGenerator, childNode);
            } else if ("mapperGenerator".equals(childNodeName)) {
                parseMapperGenerator(commonGenerator, childNode);
            }
        }
        configuration.setCommonGenerator(commonGenerator);
    }

    private void parseDtoGenerator(CommonGenerator commonGenerator, Element node) {
        Map<String, String> map = XmlPropertyHolder.parseChildProperty(node.elements());
        DtoGenerator dtoGenerator = new DtoGenerator();
        String targetProject = map.get("targetProject");
        dtoGenerator.setTargetProject(targetProject);
        String targetPackage = map.get("targetPackage");
        dtoGenerator.setTargetPackage(targetPackage);
        String allConstructor = map.get("allConstructor");
        if (StringUtil.isNotBlank(allConstructor)) {
            dtoGenerator.setAllConstructor(Boolean.valueOf(allConstructor));
        }
        String trimStrings = map.get("trimStrings");
        if (StringUtil.isNotBlank(trimStrings)) {
            dtoGenerator.setTrimStrings(Boolean.valueOf(trimStrings));
        }
        String immutable = map.get("immutable");
        if (StringUtil.isNotBlank(immutable)) {
            dtoGenerator.setImmutable(Boolean.valueOf(immutable));
        }
        String useLombok = map.get("useLombok");
        if (StringUtil.isNotBlank(useLombok)) {
            dtoGenerator.setUseLombok(Boolean.valueOf(useLombok));
        }
        commonGenerator.setDtoGenerator(dtoGenerator);
    }

    private void parseModelGenerator(CommonGenerator commonGenerator, Element node) {
        Map<String, String> map = XmlPropertyHolder.parseChildProperty(node.elements());
        ModelGenerator modelGenerator = new ModelGenerator();
        String targetProject = map.get("targetProject");
        modelGenerator.setTargetProject(targetProject);
        String targetPackage = map.get("targetPackage");
        modelGenerator.setTargetPackage(targetPackage);
        String allConstructor = map.get("allConstructor");
        if (StringUtil.isNotBlank(allConstructor)) {
            modelGenerator.setAllConstructor(Boolean.valueOf(allConstructor));
        }
        String trimStrings = map.get("trimStrings");
        if (StringUtil.isNotBlank(trimStrings)) {
            modelGenerator.setTrimStrings(Boolean.valueOf(trimStrings));
        }
        String immutable = map.get("immutable");
        if (StringUtil.isNotBlank(immutable)) {
            modelGenerator.setImmutable(Boolean.valueOf(immutable));
        }
        String useLombok = map.get("useLombok");
        if (StringUtil.isNotBlank(useLombok)) {
            modelGenerator.setUseLombok(Boolean.valueOf(useLombok));
        }
        commonGenerator.setModelGenerator(modelGenerator);
    }

    private void parseDaoGenerator(CommonGenerator commonGenerator, Element node) {
        Map<String, String> map = XmlPropertyHolder.parseChildProperty(node.elements());
        DaoGenerator daoGenerator = new DaoGenerator();
        String targetProject = map.get("targetProject");
        daoGenerator.setTargetProject(targetProject);
        String targetPackage = map.get("targetPackage");
        daoGenerator.setTargetPackage(targetPackage);
        commonGenerator.setDaoGenerator(daoGenerator);
    }

    private void parseMapperGenerator(CommonGenerator commonGenerator, Element node) {
        Map<String, String> map = XmlPropertyHolder.parseChildProperty(node.elements());
        MapperGenerator mapperGenerator = new MapperGenerator();
        String targetProject = map.get("targetProject");
        mapperGenerator.setTargetProject(targetProject);
        String targetPackage = map.get("targetPackage");
        mapperGenerator.setTargetPackage(targetPackage);
        commonGenerator.setMapperGenerator(mapperGenerator);
    }

    private void parseTablesConfiguration(Configuration configuration, Element node) {
        List<Element> nodeList = node.elements();
        if (nodeList.size() > 0) {
            List<TableConfiguration> tableConfigurationList = new ArrayList<>();
            for (Element childNode : nodeList) {
                if (childNode.getNodeType() != Element.ELEMENT_NODE) {
                    continue;
                }
                String childNodeName = childNode.getName();
                if ("table".equals(childNodeName)) {
                    tableConfigurationList.add(parseTable(childNode));
                }
            }
            configuration.setTablesConfiguration(tableConfigurationList);
        }
    }

    private TableConfiguration parseTable(Element table) {
        TableConfiguration tableConfiguration = new TableConfiguration();
        Properties attributes = XmlPropertyHolder.parseAttributes(table);
        String tableName = attributes.getProperty("tableName");
        tableConfiguration.setTableName(tableName);
        String domainName = attributes.getProperty("domainName");
        if (StringUtil.isNotBlank(domainName)) {
            tableConfiguration.setDomainName(domainName);
        }
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

    private void parseViewsConfiguration(Configuration configuration, Element node) {
        List<Element> nodeList = node.elements();
        if (nodeList.size() > 0) {
            List<ViewConfiguration> viewConfigurationList = new ArrayList<>();
            for (Element childNode : nodeList) {
                if (childNode.getNodeType() != Element.ELEMENT_NODE) {
                    continue;
                }
                String childNodeName = childNode.getName();
                if ("view".equals(childNodeName)) {
                    viewConfigurationList.add(parseView(childNode));
                }
            }
            configuration.setViewsConfiguration(viewConfigurationList);
        }
    }

    private ViewConfiguration parseView(Element view) {
        ViewConfiguration viewConfiguration = new ViewConfiguration();
        Properties attributes = XmlPropertyHolder.parseAttributes(view);
        String tableName = attributes.getProperty("viewName");
        viewConfiguration.setViewName(tableName);
        String domainName = attributes.getProperty("domainName");
        if (StringUtil.isNotBlank(domainName)) {
            viewConfiguration.setDomainName(domainName);
        }
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
        if (StringUtil.isNotBlank(javaProperty)) {
            columnOverride.setJavaProperty(javaProperty);
        }
        String javaType = attributes.getProperty("javaType");
        if (StringUtil.isNotBlank(javaType)) {
            columnOverride.setJavaType(javaType);
        }
        String jdbcType = attributes.getProperty("jdbcType");
        if (StringUtil.isNotBlank(jdbcType)) {
            columnOverride.setJdbcType(jdbcType);
        }
        String typeHandler = attributes.getProperty("typeHandler");
        if (StringUtil.isNotBlank(typeHandler)) {
            columnOverride.setTypeHandler(typeHandler);
        }
        String isGeneratedAlways = attributes.getProperty("isGeneratedAlways");
        if (StringUtil.isNotBlank(isGeneratedAlways)) {
            columnOverride.setGeneratedAlways(StringUtil.isTrue(isGeneratedAlways));
        }
        return columnOverride;
    }
}
