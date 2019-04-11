package com.example.generator.code.generator;

import com.example.generator.code.generator.base.BaseGenerator;
import com.example.generator.db.ColumnInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:18
 */
public class ControllerGenerator extends BaseGenerator {

    public static String listRemark(String ClassName) {
        String value = "查询" + ClassName + "列表";
        List<String> params = new ArrayList<>();
        params.add("name = \"pageNum\", value = \"分页当前页码\", dataType = \"Integer\", required = false");
        params.add("name = \"pageSize\", value = \"分页一页大小\", dataType = \"Integer\", required = false");
        params.add("name = \"params\", value = \"其他查询参数\", dataType = \"Map\", required = false");
        return generatedSwagger2(value, params);
    }

    /**
     * @GetMapping("")
     * public ServerResponse list(@RequestParam("pageNum") Integer pageNum,
     *                            @RequestParam("pageSize") Integer pageSize,
     *                            @RequestParam("params") Map params) {
     *     return i${ClassName}Service.findAllList();
     * }
     */
    public static String list(String ClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append("@GetMapping(\"\")\n    ");
        sb.append("public ").append(responseClass).append("<Object> list(@RequestParam(\"pageNum\") Integer pageNum,\n    ");
        sb.append("                                   @RequestParam(\"pageSize\") Integer pageSize,\n    ");
        sb.append("                                   @RequestParam(\"params\") Map<String, String> params) {\n    ");
        sb.append("    return i").append(ClassName).append("Service.list(pageNum, pageSize, params);\n    ");
        sb.append("}");
        return sb.toString();
    }

    public static String insertRemark(String ClassName, String className) {
        String value = "保存" + ClassName + "对象";
        List<String> params = new ArrayList<>();
        params.add("name = \""+ className +"DTO\", value = \""+ ClassName +"DTO对象\", dataType = \"" + ClassName + "DTO\", required = true");
        return generatedSwagger2(value, params);
    }

    /**
     * @PostMapping("")
     * public ServerResponse<String> insert(@RequestBody ${ClassName}DTO ${className}DTO) {
     *     return i${ClassName}Service.insert(${className}DTO);
     * }
     */
    public static String insert(String ClassName, String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("@PostMapping(\"\")\n    ");
        sb.append("public ").append(responseClass).append("<String> insert(@RequestBody ").append(ClassName).append("DTO ").append(className).append("DTO) {\n    ");
        sb.append("    return i").append(ClassName).append("Service.insert(").append(className).append("DTO);\n    ");
        sb.append("}");
        return sb.toString();
    }

    public static String selectRemark(String ClassName, ColumnInfo primaryKeyColumn) {
        String javaType = primaryKeyColumn.getJavaType();
        String propertyName = primaryKeyColumn.getPropertyName();
        String value = "查询" + ClassName + "对象";
        List<String> params = new ArrayList<>();
        params.add("name = \""+ propertyName +"\", value = \""+ ClassName +"主键\", dataType = \"" + javaType + "\", required = true");
        return generatedSwagger2(value, params);
    }

    /**
     * @GetMapping("/{id}")
     * public ServerResponse<${ClassName}> select(@PathVariable("id") String id) {
     *     return i${ClassName}Service.get(id);
     * }
     */
    public static String select(String ClassName, ColumnInfo primaryKeyColumn) {
        String javaType = primaryKeyColumn.getJavaType();
        String propertyName = primaryKeyColumn.getPropertyName();
        StringBuilder sb = new StringBuilder();
        sb.append("@GetMapping(\"/{").append(propertyName).append("}\")\n    ");
        sb.append("public ").append(responseClass).append("<").append(ClassName).append("DTO> select(@PathVariable(\"").append(propertyName).append("\") ").append(javaType).append(" ").append(propertyName).append(") {\n    ");
        sb.append("    return i").append(ClassName).append("Service.select(").append(propertyName).append(");\n    ");
        sb.append("}");
        return sb.toString();
    }

    public static String updateRemark(String ClassName, String className, ColumnInfo primaryKeyColumn) {
        String javaType = primaryKeyColumn.getJavaType();
        String propertyName = primaryKeyColumn.getPropertyName();
        String value = "更新" + ClassName + "对象";
        List<String> params = new ArrayList<>();
        params.add("name = \""+ propertyName +"\", value = \""+ ClassName +"主键\", dataType = \"" + javaType + "\", required = true");
        params.add("name = \""+ className +"DTO\", value = \""+ ClassName +"DTO对象\", dataType = \"" + ClassName + "DTO\", required = true");
        return generatedSwagger2(value, params);
    }

    /**
     * @PutMapping("/{id}")
     * public ServerResponse<String> update(@PathVariable("id") String id,
     *                                      @RequestBody ${ClassName}DTO ${className}DTO) {
     *     return i${ClassName}Service.update(${className}DTO);
     * }
     */
    public static String update(String ClassName, String className, ColumnInfo primaryKeyColumn) {
        String javaType = primaryKeyColumn.getJavaType();
        String propertyName = primaryKeyColumn.getPropertyName();
        StringBuilder sb = new StringBuilder();
        sb.append("@PutMapping(\"/{").append(propertyName).append("}\")\n    ");
        sb.append("public ").append(responseClass).append("<String> update(@PathVariable(\"").append(propertyName).append("\") ").append(javaType).append(" ").append(propertyName).append(",\n    ");
        sb.append("                                     @RequestBody ").append(ClassName).append("DTO ").append(className).append("DTO) {\n    ");
        sb.append("    return i").append(ClassName).append("Service.update(").append(propertyName).append(", ").append(className).append("DTO);\n    ");
        sb.append("}");
        return sb.toString();
    }

    public static String deleteRemark(String ClassName, ColumnInfo primaryKeyColumn) {
        String propertyName = primaryKeyColumn.getPropertyName();
        String value = "批量删除" + ClassName + "对象";
        List<String> params = new ArrayList<>();
        params.add("name = \""+ propertyName +"s\", value = \""+ ClassName +"主键字符串,用 , 分隔\", dataType = \"String\", required = true");
        return generatedSwagger2(value, params);
    }

    /**
     * @DeleteMapping("/{ids}")
     * public ServerResponse<String> delete(@PathVariable("ids") String ids) {
     *     return i${ClassName}Service.delete(ids);
     * }
     */
    public static String delete(String ClassName, ColumnInfo primaryKeyColumn) {
        String primaryKeys = primaryKeyColumn.getPropertyName() + "s";
        StringBuilder sb = new StringBuilder();
        sb.append("@DeleteMapping(\"/{").append(primaryKeys).append("}\")\n    ");
        sb.append("public ").append(responseClass).append("<String> delete(@PathVariable(\"").append(primaryKeys).append("\") String ").append(primaryKeys).append(") {\n    ");
        sb.append("    return i").append(ClassName).append("Service.delete(").append(primaryKeys).append(");\n    ");
        sb.append("}");
        return sb.toString();
    }
}
