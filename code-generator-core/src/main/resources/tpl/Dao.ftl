package ${DaoPackageName};

<#if baseMapper??>
import ${DaoPackageName}.base.${baseMapper};
</#if>
import ${EntityPackageName}.${ClassName};
//import org.apache.ibatis.annotations.Mapper;

import java.util.List;

<#if Remark??>
${Remark}
</#if>
//@Mapper
public interface ${ClassName}Mapper <#if baseMapper??>extends ${baseMapper}<${ClassName}></#if> {

<#if selectAllListByKeyword??>
    ${selectAllListByKeyword}
</#if>

<#if selectPageListByKeyword??>
    ${selectPageListByKeyword}
</#if>

<#if selectByPrimaryKey??>
    ${selectByPrimaryKey}
</#if>

<#if deleteByPrimaryKey??>
    ${deleteByPrimaryKey}
</#if>

<#if insert??>
    ${insert}
</#if>

<#if insertSelective??>
    ${insertSelective}
</#if>

<#if updateByPrimaryKeySelective??>
    ${updateByPrimaryKeySelective}
</#if>

<#if updateByPrimaryKey??>
    ${updateByPrimaryKey}
</#if>
}