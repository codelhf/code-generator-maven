package ${EntityDtoPackageName};

import java.io.Serializable;
import java.util.Date;
import java.util.List;

<#if Remark??>
${Remark}
</#if>
<#if Lombok??>
${Lombok}
</#if>
public class ${ClassName}DTO implements Serializable {
    private static final long serialVersionUID = 1L;

    ${Properties}

<#if Constructor??>
    ${AllArgsConstructor}
    ${NoArgsConstructor}
</#if>

<#if Methods??>
    ${Methods}
</#if>
}