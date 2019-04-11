package ${ControllerPackageName};

import ${BasePackageName}.common.${ResponseClass};
import ${EntityDTOPackageName}.${ClassName}DTO;
import ${ServicePackageName}.I${ClassName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.Map;

<#if Remark??>
${Remark}
</#if>
@RestController
@RequestMapping(value = "/${className}")
public class ${ClassName}Controller {

    @Autowired
    private I${ClassName}Service i${ClassName}Service;

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
