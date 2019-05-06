package ${ServiceImplPackageName};

import com.alibaba.fastjson.JSON;
import ${BasePackageName}.common.ResponseCode;
import ${BasePackageName}.common.${ResponseClass};
import ${DaoPackageName}.${ClassName}Mapper;
import ${EntityDTOPackageName}.${ClassName}DTO;
import ${EntityPackageName}.${ClassName};
import ${ServicePackageName}.I${ClassName}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Splitter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
