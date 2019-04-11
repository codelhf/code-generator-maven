package ${ServiceImplPackageName};

import ${BasePackageName}.common.${ResponseClass};
import ${EntityPackageName}.${ClassName};
import ${EntityDTOPackageName}.${ClassName}DTO;
import ${ServicePackageName}.I${ClassName}Service;
import ${DaoPackageName}.${ClassName}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

<#if Remark??>
${Remark}
</#if>
@Service
public class ${ClassName}ServiceImpl implements I${ClassName}Service {

    @Autowired
    private ${ClassName}Mapper ${className}Mapper;

<#if listRemark??>
    ${listRemark}
</#if>
<#if list??>
    ${list}
</#if>

<#if insertRemark??>
    ${insertRemark}
</#if>
<#if insert??>
    ${insert}
</#if>

<#if selectRemark??>
    ${selectRemark}
</#if>
<#if select??>
    ${select}
</#if>

<#if updateRemark??>
    ${updateRemark}
</#if>
<#if update??>
    ${update}
</#if>

<#if deleteRemark??>
    ${deleteRemark}
</#if>
<#if delete??>
    ${delete}
</#if>
}
