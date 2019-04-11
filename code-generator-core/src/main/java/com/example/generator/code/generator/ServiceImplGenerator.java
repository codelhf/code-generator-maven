package com.example.generator.code.generator;

import com.example.generator.code.generator.base.BaseGenerator;
import com.example.generator.db.ColumnInfo;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:22
 */
public class ServiceImplGenerator extends ServiceGenerator {

    /**
     * public ServerResponse<PageInfo> list(Integer pageNum, Integer pageSize, Map<String, String> params) {
     *     if (pageNum != null && pageSize != null) {
     *         PageHelper.startPage(pageNum, pageSize);
     *     }
     *     ${ClassName} ${className} = null;
     *     if (CollectionUtils.isNotEmpty(params)) {
     *         ${className} = JSON.parseObject(JSON.toString(params));
     *     }
     *     List<${ClassName}> ${className}List = ${className}Mapper.findList(${className});
     *     return ServerResponse.createBySuccess(${className}List);
     * }
     */
    public static String list(String ClassName, String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("public ").append(BaseGenerator.responseClass).append("<PageInfo> list(Integer pageNum, Integer pageSize, Map<String, String> params) {\n        ");
        sb.append("    if (pageNum != null && pageSize != null) {\n    ");
        sb.append("        PageHelper.startPage(pageNum, pageSize);\n    ");
        sb.append("    }\n    ");
        sb.append("    ").append(ClassName).append(" ").append(className).append(" = null;\n    ");
        sb.append("    if (CollectionUtils.isNotEmpty(params)) {\n    ");
        sb.append("        ").append(className).append(" = JSON.parseObject(JSON.toString(params));\n    ");
        sb.append("    }\n    ");
        sb.append("    List<").append(ClassName).append("> ").append(className).append("List = ").append(className).append("Mapper.selectList(").append(className).append(");\n    ");
        sb.append("    return ").append(BaseGenerator.responseClass).append(".createBySuccess(").append(className).append("List);\n    ");
        sb.append("}");
        return sb.toString();
    }

    /**
     * public ServerResponse<${ClassName}> select(String id) {
     *     if (StringUtil.isBlank(id)) {
     *         return ServerResponse.createByErrorMessage("id不能为空");
     *     }
     *     ${ClassName} ${className} = ${className}Mapper.selectByPrimaryKey(id);
     *     if (${className} == null) {
     *         return ServerResponse.createByErrorMessage("${ClassName}不存在");
     *     }
     *     return ServerResponse.createBySuccess(${className});
     * }
     */
    public static String select(String ClassName, String className, ColumnInfo primaryKeyColumn) {
        String javaType = primaryKeyColumn.getJavaType();
        String propertyName = primaryKeyColumn.getPropertyName();
        StringBuilder sb = new StringBuilder();
        sb.append("public ").append(BaseGenerator.responseClass).append("<").append(ClassName).append("DTO> select(").append(javaType).append(" ").append(propertyName).append(") {\n    ");
        sb.append("    if (StringUtil.isBlank(").append(propertyName).append(")) {\n    ");
        sb.append("        return ").append(BaseGenerator.responseClass).append(".createByErrorMessage(\"").append(propertyName).append("不能为空\");\n    ");
        sb.append("    }\n    ");
        sb.append("    ").append(ClassName).append(" ").append(className).append(" = ").append(className).append("Mapper.selectByPrimaryKey(").append(propertyName).append(");\n    ");
        sb.append("    if (").append(className).append(" == null) {\n    ");
        sb.append("        return ").append(BaseGenerator.responseClass).append(".createByErrorMessage(\"").append(ClassName).append("不存在\");\n    ");
        sb.append("    }\n    ");
        sb.append("    return ").append(BaseGenerator.responseClass).append(".createBySuccess(BeanUtils.copy(").append(className).append("));\n    ");
        sb.append("}");
        return sb.toString();
    }

    /**
     * public ServerResponse<String> insert(${ClassName}DTO ${className}DTO) {
     *     ${ClassName} ${className} = BeanUtils.copy(${className}DTO);
     *     int rowCount = ${className}Mapper.insertSelective(${className});
     *     if (rowCount == 0) {
     *         return ServerResponse.createByErrorMessage("新增${ClassName}失败");
     *     }
     *     return ServerResponse.createBySuccessMessage("新增${ClassName}成功");
     * }
     */
    public static String insert(String ClassName, String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("public ").append(BaseGenerator.responseClass).append("<String> insert(").append(ClassName).append("DTO ").append(className).append("DTO) {\n    ");
        sb.append("    ").append(ClassName).append(" ").append(className).append(" = BeanUtils.copy(").append(className).append("DTO);\n    ");
        sb.append("    int rowCount = ").append(className).append("Mapper.insertSelective(").append(className).append(");\n    ");
        sb.append("    if (rowCount == 0) {\n    ");
        sb.append("        return ").append(BaseGenerator.responseClass).append(".createByErrorMessage(\"新增").append(ClassName).append("失败\");\n    ");
        sb.append("    }\n    ");
        sb.append("    return ").append(BaseGenerator.responseClass).append(".createBySuccessMessage(\"新增").append(ClassName).append("成功\");\n    ");
        sb.append("}");
        return sb.toString();
    }

    /**
     * public ServerResponse<String> update(String id, ${ClassName}DTO ${EntityName}DTO) {
     *     if (StringUtil.isBlank(id)) {
     *         return ServerResponse.createByErrorMessage("id不能为空");
     *     }
     *     ${ClassName} ${className} = BeanUtils.copy(${className}DTO);
     *     ${className}.setId(id);
     *     int rowCount = ${className}Mapper.updateByPrimaryKeySelective(${className});
     *     if (rowCount == 0) {
     *         return ServerResponse.createByErrorMessage("更新${ClassName}失败");
     *     }
     *     return ServerResponse.createBySuccessMessage("更新${ClassName}成功");
     * }
     */

    public static String update(String ClassName, String className, ColumnInfo primaryKeyColumn) {
        String javaType = primaryKeyColumn.getJavaType();
        String propertyName = primaryKeyColumn.getPropertyName();
        StringBuilder sb = new StringBuilder();
        sb.append("public ").append(BaseGenerator.responseClass).append("<String> update(").append(javaType).append(" ").append(propertyName).append(", ").append(ClassName).append("DTO ").append(className).append("DTO) {\n    ");
        sb.append("    if (StringUtil.isBlank(").append(propertyName).append(")) {\n    ");
        sb.append("        return ").append(BaseGenerator.responseClass).append(".createByErrorMessage(\"").append(propertyName).append("不能为空\");\n    ");
        sb.append("    }\n    ");
        sb.append("    ").append(ClassName).append(" ").append(className).append(" = BeanUtils.copy(").append(className).append("DTO);\n    ");
//        sb.append("    ").append(className).append(".setId(").append(propertyName).append(");\n    ");
        sb.append("    int rowCount = ").append(className).append("Mapper.updateByPrimaryKeySelective(").append(className).append(");\n    ");
        sb.append("    if (rowCount == 0) {\n    ");
        sb.append("        return ").append(BaseGenerator.responseClass).append(".createByErrorMessage(\"更新").append(ClassName).append("失败\");\n    ");
        sb.append("    }\n    ");
        sb.append("    return ").append(BaseGenerator.responseClass).append(".createBySuccessMessage(\"更新").append(ClassName).append("成功\");\n    ");
        sb.append("}");
        return sb.toString();
    }

    /**
     * public ServerResponse<String> delete(String ids) {
     *     List<String> idList = ids.split(",");
     *     if (CollectionUtils.isEmpty(idList)) {
     *         return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "id不能为空");
     *     }
     *     int rowCount = ${className}Mapper.deleteByIdList(idList);
     *     if (rowCount == 0 || rowCount < idList.size()) {
     *         return ServerResponse.createByErrorMessage("批量删除${ClassName}失败");
     *     }
     *     return ServerResponse.createBySuccessMessage("批量删除${ClassName}成功");
     * }
     */
    public static String delete(String ClassName, String className, ColumnInfo primaryKeyColumn) {
        String propertyName = primaryKeyColumn.getPropertyName();
        StringBuilder sb = new StringBuilder();
        sb.append("public ").append(BaseGenerator.responseClass).append("<String> delete(String ").append(propertyName).append("s) {\n    ");
        sb.append("    List<String> idList = ").append(propertyName).append("s.split(\",\");\n    ");
        sb.append("    if (idList == null || idList.size() == 0) {\n    ");
        sb.append("        return ").append(BaseGenerator.responseClass).append(".createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), \"").append(propertyName).append("不能为空\");\n    ");
        sb.append("    }\n    ");
        sb.append("    int rowCount = ").append(className).append("Mapper.deleteByIdList(idList);\n    ");
        sb.append("    if (rowCount == 0 || rowCount < idList.size()) {\n    ");
        sb.append("        return ").append(BaseGenerator.responseClass).append(".createByErrorMessage(\"批量删除").append(ClassName).append("失败\");\n    ");
        sb.append("    }\n    ");
        sb.append("    return ").append(BaseGenerator.responseClass).append(".createBySuccessMessage(\"批量删除").append(ClassName).append("成功\");\n    ");
        sb.append("}");
        return sb.toString();
    }
}
