package ${DaoPackageName};

import ${DaoPackageName}.base.${baseMapper};
import ${EntityPackageName}.${ClassName};
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

<#if Remark? if_exists>
${Remark}
</#if>
@Mapper
public interface ${ClassName}Mapper <#if baseMapper? if_exists>extends ${baseMapper}<${ClassName}></#if> {
//    int deleteByPrimaryKey(String id);

//    int insert(Interface record);

//    int insertSelective(Interface record);

//    Interface selectByPrimaryKey(String id);

//    int updateByPrimaryKeySelective(Interface record);

//    int updateByPrimaryKey(Interface record);

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
<#if updateByPrimaryKey? if_exists>
    ${updateByPrimaryKey}
</#if>
}