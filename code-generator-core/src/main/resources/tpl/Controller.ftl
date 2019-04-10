package ${ControllerPackageName};

import ${BasePackageName}.common.${ResponseClass};
import ${EntityDTOPackageName}.${ClassName}DTO;
import ${ServicePackageName}.I${ClassName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;

<#if Remark? if_exists>
    ${Remark}
</#if>
@RestController
@RequestMapping(value = "/${className}")
public class ${ClassName}Controller {

    @Autowired
    private I${ClassName}Service i${ClassName}Service;

<#if listRemark? if_exists>
    ${listRemark}
</#if>
<#if list? if_exists>
    ${list}
</#if>
<#if insert? if_exists>
    ${insert}
</#if>
<#if insertRemark? if_exists>
    ${insertRemark}
</#if>
<#if selectRemark? if_exists>
    ${selectRemark}
</#if>
<#if select? if_exists>
    ${select}
</#if>
<#if update? if_exists>
    ${update}
</#if>
<#if updateRemark? if_exists>
    ${updateRemark}
</#if>
<#if deleteRemark? if_exists>
    ${deleteRemark}
</#if>
<#if delete? if_exists>
    ${delete}
</#if>

}
