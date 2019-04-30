package ${DaoPackageName};

<#if baseMapper??>
import ${DaoPackageName}.base.${baseMapper};
</#if>
import ${EntityPackageName}.${ClassName};
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

<#if Remark??>
${Remark}
</#if>
@Mapper
public interface ${ClassName}Mapper <#if baseMapper??>extends ${baseMapper}<${ClassName}></#if> {

<#if deleteByPrimaryKey??>
    ${deleteByPrimaryKey}
</#if>

<#if insert??>
    ${insert}
</#if>

<#if insertSelective??>
    ${insertSelective}
</#if>

<#if selectByPrimaryKey??>
    ${selectByPrimaryKey}
</#if>

<#if updateByPrimaryKeySelective??>
    ${updateByPrimaryKeySelective}
</#if>

<#if updateByPrimaryKey??>
    ${updateByPrimaryKey}
</#if>

<#if selectPageList??>
    ${selectPageList}
</#if>

<#if deleteByIdList??>
    ${deleteByIdList}
</#if>
}