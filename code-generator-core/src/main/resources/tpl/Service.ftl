package ${ServicePackageName};

import ${BasePackageName}.common.${ResponseClass};
import ${EntityDTOPackageName}.${ClassName}DTO;

import java.util.Map;

<#if Remark??>
${Remark}
</#if>
public interface I${ClassName}Service {

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
