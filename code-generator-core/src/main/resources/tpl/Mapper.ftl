<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${DaoPackageName}.${ClassName}Mapper">
<#if baseResultMap? if_exists>
    ${BaseResultMap}
</#if>
<#if baseColumnList? if_exists>
    ${baseColumnList}
</#if>
<#if selectAllListByKeyword? if_exists>
    ${selectAllListByKeyword}
</#if>
<#if selectPageListByKeyword? if_exists>
    ${selectPageListByKeyword}
</#if>
<#if selectByPrimaryKey? if_exists>
    ${selectByPrimaryKey}
</#if>
<#if deleteByPrimaryKey? if_exists>
    ${deleteByPrimaryKey}
</#if>
<#if insert? if_exists>
    ${insert}
</#if>
<#if insertSelective? if_exists>
    ${insertSelective}
</#if>
<#if updateByPrimaryKeySelective? if_exists>
    ${updateByPrimaryKeySelective}
</#if>
<#if updateByPrimaryKey??>
    ${updateByPrimaryKey}
</#if>
</mapper>
