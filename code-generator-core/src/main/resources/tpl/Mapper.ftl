<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${DaoPackageName}.${ClassName}Mapper">
<#if baseResultMap??>
    ${baseResultMap}
</#if>
<#--<#if baseColumnList??>-->
    <#--${baseColumnList}-->
<#--</#if>-->
<#--<#if selectByPrimaryKey??>-->
    <#--${selectByPrimaryKey}-->
<#--</#if>-->
<#--<#if deleteByPrimaryKey??>-->
    <#--${deleteByPrimaryKey}-->
<#--</#if>-->
<#--<#if insert??>-->
    <#--${insert}-->
<#--</#if>-->
<#--<#if insertSelective??>-->
    <#--${insertSelective}-->
<#--</#if>-->
<#--<#if updateByPrimaryKeySelective??>-->
    <#--${updateByPrimaryKeySelective}-->
<#--</#if>-->
<#--<#if updateByPrimaryKey??>-->
    <#--${updateByPrimaryKey}-->
<#--</#if>-->
<#--<#if selectPageList??>-->
    <#--${selectPageList}-->
<#--</#if>-->
<#--<#if deleteByIdList??>-->
    <#--${deleteByIdList}-->
<#--</#if>-->
</mapper>
