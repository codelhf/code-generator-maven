# code-generator
code-generator基于maven的代码生成器, 支持**自定义代码模板**, 
能够**生成各种类型的代码文件**, **前端和后端代码一次性生成**
### 使用方法
1. 下载代码 
```
git clone https://github.com:codelhf/code-generator-maven.git
```
2. 打包安装到本地仓库
```$xslt
mvn clean install
```
3. 在目标项目中引入插件
> 版本号和code-generator保持一致
```
<plugin>
    <groupId>com.example.generator</groupId>
    <artifactId>code-generator-maven-plugin</artifactId>
    <version>2.2.1-SNAPSHOT</version>
    <!--<configuration>-->
        <!--<configurationFile>src/test/resources/generatorConfig-test.xml</configurationFile>-->
    <!--</configuration>-->
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.6</version>
        </dependency>
    </dependencies>
</plugin>
```
### 配置文件
1. 在目标项目中添加配置文件
```
<?xml version="1.0" encoding="UTF-8"?>
<!--标签为对象, property为对象的属性-->
<generatorConfiguration>
    <!--导入配置文件,非必须参数(待完善)-->
    <properties resource="datasource.properties"/>

    <!--jdbc的数据库连接-->
    <jdbcConnection>
        <!--driverClass非必须参数(支持MySQL、Oracle、SQLServer)-->
        <property name="driverClass" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/cloud_user?characterEncoding=utf-8"/>
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </jdbcConnection>

    <!--java类型适配(待完善)-->
    <javaTypeResolver>
        <!-- 当表名或者字段名为SQL关键字的时候，可以设置该属性为true，MBG会自动给表名或字段名添加分隔符  -->
        <property name="autoDelimitKeywords" value="true"/>
        <property name="beginningDelimiter" value="`"/>
        <property name="endingDelimiter" value="`"/>
        <!--
            默认false，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer
            true，把JDBC DECIMAL 和 NUMERIC 类型解析为java.math.BigDecimal
        -->
        <property name="forceBigDecimals" value="false"/>
    </javaTypeResolver>

    <!-- optional，旨在创建class时，对注释进行控制 -->
    <commentGenerator>
        <!--代码注释配置-->
        <property name="author" value="liuhf"/>
        <property name="company" value="example"/>
        <property name="dateFormat" value="yyyy/MM/dd HH:mm:ss"/>
        <property name="fileEncode" value="UTF-8"/>
        <property name="httpPrefix" value="mm"/>
        <property name="responseClass" value="ServerResponse"/>
        <property name="generateRemark" value="true"/>
        <property name="generateSwagger" value="true"/>
        <property name="commonMapper" value="false"/>
    </commentGenerator>

    <!--对应模板的配置-->
    <templates>
        <!--名称不能和api中的字段重复,否则被覆盖无效,并且被依赖模块不能注释-->
        <!--common类型的以suffix为文件名-->
        <template name="exceptionResolver" template="template/Default/common/ExceptionResolver.vm"
                  directory="./src/main/java" packageName="com.example.generator.demo.common"
                  suffix="ExceptionResolver" fileType="java" common="true" isGenerate="true" override="true"/>
        <template name="swagger2" template="template/Default/common/Swagger2.vm"
                  directory="./src/main/java" packageName="com.example.generator.demo.common"
                  suffix="Swagger2" fileType="java" common="true" isGenerate="true" override="true"/>
        <template name="responseCode" template="template/Default/common/ResponseCode.vm"
                  directory="./src/main/java" packageName="com.example.generator.demo.common"
                  suffix="ResponseCode" fileType="java" common="true" isGenerate="true" override="true"/>
        <template name="serverResponse" template="template/Default/common/ServerResponse.vm"
                  directory="./src/main/java" packageName="com.example.generator.demo.common"
                  suffix="ServerResponse" fileType="java" common="true" isGenerate="true" override="true"/>
        <!--非common类型的domainName加suffix为文件名-->
        <template name="entity" template="template/Default/dao/Entity.vm"
                  directory="./src/main/java" packageName="com.example.generator.demo.entity"
                  suffix="" fileType="java" isGenerate="true" override="true"/>
        <template name="entityDTO" template="template/Default/dto/EntityDto.vm"
                  directory="./src/main/java" packageName="com.example.generator.demo.dto"
                  suffix="DTO" fileType="java" isGenerate="true" override="true"/>
        <template name="mapper" template="template/Default/dao/Mapper.vm"
                  directory="./src/main/resources" packageName="mappers"
                  suffix="Mapper" fileType="xml" isGenerate="true" override="true"/>
        <template name="dao" template="template/Default/dao/Dao.vm"
                  directory="./src/main/java" packageName="com.example.generator.demo.dao"
                  suffix="Mapper" fileType="java" isGenerate="true" override="true"/>
        <template name="serviceImpl" template="template/Default/service/ServiceImpl.vm"
                  directory="./src/main/java" packageName="com.example.generator.demo.service.impl"
                  suffix="ServiceImpl" fileType="java" isGenerate="true" override="true"/>
        <template name="service" template="template/Default/service/Service.vm"
                  directory="./src/main/java" packageName="com.example.generator.demo.service"
                  suffix="Service" fileType="java" isGenerate="true" override="true"/>
        <template name="controller" template="template/Default/service/Controller.vm"
                  directory="./src/main/java" packageName="com.example.generator.demo.controller"
                  suffix="Controller" fileType="java" isGenerate="true" override="true"/>
        <!--非common类型的suffix以 / 开头的生成在domainName目录下以suffix的最后为文件名-->
        <template name="html" template="template/User/page/Html.vm"
                  directory="./src/main/resources/static" packageName="page/trace"
                  suffix="/index" fileType="html" isGenerate="true" override="true"/>
        <template name="js" template="template/User/page/JS.vm"
                  directory="./src/main/resources/static" packageName="apps/trace"
                  suffix="/js/index" fileType="js" isGenerate="true" override="true"/>
        <template name="tpl" template="template/User/page/TplEdit.vm"
                  directory="./src/main/resources/static" packageName="apps/trace"
                  suffix="/tpl/edit" fileType="tpl" isGenerate="true" override="true"/>
        <template name="tpl" template="template/User/page/TplList.vm"
                  directory="./src/main/resources/static" packageName="apps/trace"
                  suffix="/tpl/list" fileType="tpl" isGenerate="true" override="true"/>
        <template name="api" template="template/User/vue/Api.vm"
                  directory="./src/main/resources/static/vue/src" packageName="api"
                  suffix="/index" fileType="js" isGenerate="true" override="true"/>
        <template name="view" template="template/User/vue/View.vm"
                  directory="./src/main/resources/static/vue/src" packageName="view"
                  suffix="/index" fileType="vue" isGenerate="true" override="true"/>
    </templates>

    <!-- 数据库对应数据表-->
    <tables>
        <table tableName="sys_log" domainName="SysLog">
            <columnOverride columnName="head_img" jdbcType="VARCHAR"/>
        </table>
    </tables>

    <!-- 数据库对应数据视图-->
    <views>
        <view viewName="v_sys_log" domainName="ViewSysLog">
        </view>
    </views>

    <!-- code-generator插件的搭建 -->
</generatorConfiguration>
```
2. 在目标项目中执行命令或者使用IDE
```$xslt
mvn code-generator:generate
```
### 注意事项
1. dateFormat为生成代码注释中的时间格式
```$xslt
<property name="dateFormat" value="yyyy/MM/dd HH:mm:ss"/>
```
2. 请求接口前缀, 不需要 / 开头
```$xslt
<property name="httpPrefix" value="mm"/>
```
3. 请求接口通用返回类名, 在使用生成器生成通用返回类时必须指定
```$xslt
<property name="responseClass" value="ServerResponse"/>
```
4. 模板代码中是否使用mybatis通用mapper
```$xslt
<property name="commonMapper" value="false"/>
```
 5. 模板配置
> name为模板名称, 不能和6中的字段重复, template模板相对generator的resources的路径
>
> directory以 ./ 开头是以pom文件为相对路径, packageName为目标项目的代码生成的包路径
>
> suffix是生成代码文件的后缀名
>   1. common=true生成的代码文件名: suffix.fileType
>   2. common=false生成的代码文件名: domainNamesuffix.fileType
>   3. common=false且suffix以 / 开头, 文件名: domainName/js/'index'.js
>
> fileType为生成的代码文件类型
>
> common代码模板的类型, 是否为通用模板,默认为false
>
> isGenerate是否生成代码, 默认为true
>
> override是否覆盖原有代码文件, 默认为false
```$xslt
<template name="serverResponse" template="template/Default/common/ServerResponse.vm"
          directory="./src/main/java" packageName="com.example.generator.demo.common"
          suffix="ServerResponse" fileType="java" common="true" isGenerate="true" override="true"/>
```
6. 代码生成器模板中的通用字段
```
属性
${company}         设置中的公司 java.lang.String
${author}          设置中的作者 java.lang.String
${createTime}      设置中的创建时间 java.lang.String
${httpPrefix}      设置中的接口前缀 java.lang.String
${responseClass}   设置中的接口响应类 java.lang.String
${generateRemark}  设置中的是否生成注解 java.lang.String
${generateSwagger} 设置中的是否生成接口文档 java.lang.String
${commonMapper}    设置中的是否使用通用Mapper java.lang.String

对象
${tableName} 表名 java.lang.String
${isView} 是否为视图 java.lang.Boolean
${ClassName} 实体类类名 java.lang.String
${className} 实体类变量 java.lang.String
${fullColumn} 表所有列
${pkColumn} 主键列

columnInfo 列对象
    columnName 列名（首字母小写） java.lang.String
    propertyName 属性名 java.lang.String
    type 列类型 java.lang.Integer
    jdbcType 数据库类型 java.lang.String
    javaType java类型 java.lang.String
    isPrimaryKey 是否为主键 java.lang.Boolean
    comment 列注释 java.lang.String

工具
${StringUtil}
    isBlank(String str) 判断字符串是否为空
    isNotBlank(String str) 判断字符串是否不为空
    isTrue(String str) 判断字符串是否是true
    tableName2ClassName(String tableName) 以驼峰命名法生成类名
    columnName2PropertyName(String columnName) 数据库列名转换为实体的属性名
    isAllUpperCase(String str) 给定字符串除特定符号外的字符是否全部大写
    isAllLowerCase(String str) 给定字符串除特定符号外的字符是否全部小写
    firstToUpperCase(String str) 首字母大写方法
    firstToLowerCase(String str) 首字母小写方法
```
---
