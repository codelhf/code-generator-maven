package com.example.generator.code.generator.base;

import com.example.generator.config.Configuration;
import com.example.generator.util.DateTimeUtil;

import java.util.Date;
import java.util.List;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/23 10:29
 */
public class BaseGenerator {

    /**
     * 返回类类名
     */
    public static final String responseClass = "ServerResponse";

    /**
     * @Title: ${ClassName}ServiceImpl
     * @Description: 单表业务逻辑实现类
     * @Company: ${company}
     * @Author: ${author}
     * @CreateTime: ${createTime}
     */
    public static String generateRemark(String title, String description, Configuration configuration) {
        String company = configuration.getCommentGenerator().getCompany();
        String author = configuration.getCommentGenerator().getAuthor();
        String createTime = DateTimeUtil.dateToStr(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append("/**\n");
        sb.append(" * @Title: ").append(title).append("\n");
        sb.append(" * @Description: ").append(description).append("\n");
        sb.append(" * @Company: ").append(company).append("\n");
        sb.append(" * @Author: ").append(author).append("\n");
        sb.append(" * @CreateTime: ").append(createTime).append("\n");
        sb.append(" */");
        return sb.toString();
    }

    /**
     * @Title: ${function}
     * @Description: 单表业务逻辑实现类
     * @Company: ${company}
     * @Author: ${author}
     * @CreateTime: ${createTime}
     *
     * @param
     * @return
     */
    public static String generateFunctionRemark(String title, String description,
                                                List<String> params, String returns,
                                                Configuration configuration) {
        String company = configuration.getCommentGenerator().getCompany();
        String author = configuration.getCommentGenerator().getAuthor();
        String createTime = DateTimeUtil.dateToStr(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append("/**\n");
        sb.append(" * @Title: ").append(title).append("\n");
        sb.append(" * @Description: ").append(description).append("\n");
        sb.append(" * @Company: ").append(company).append("\n");
        sb.append(" * @Author: ").append(author).append("\n");
        sb.append(" * @CreateTime: ").append(createTime).append("\n");
        sb.append(" * \n");
        for (String param : params) {
            sb.append(" * @param ").append(param).append("\n");
        }
        sb.append(" * @return ").append(returns).append("\n");
        sb.append(" */");
        return sb.toString();
    }

    /**
     * @Getter
     * @Setter
     * @NoArgsConstructor
     * @AllArgsConstructor
     * @Builder
     */
    public static String generateLombok() {
        StringBuilder sb = new StringBuilder();
        sb.append("@Getter\n");
        sb.append("@Setter\n");
        sb.append("@NoArgsConstructor\n");
        sb.append("@AllArgsConstructor\n");
        sb.append("@Builder\n");
        return sb.toString();
    }

    /**
     * @ApiOperation(value = "")
     * @ApiImplicitParams({
     *      @ApiImplicitParam(name = "", value = "", dataType = "", required = true),
     *      @ApiImplicitParam(name = "", value = "", dataType = "", required = true)
     * })
     */

    public static String generatedSwagger2(String value, List<String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("/**\n");
        sb.append(" * @ApiOperation(value = \"").append(value).append("\")\n");
        if (params.size() > 0) {
            sb.append(" * @ApiImplicitParams({\n");
            for (String param: params) {
                sb.append(" *     @ApiImplicitParam(").append(param).append("),\n");
            }
            sb.substring(0, sb.lastIndexOf(","));
            sb.append(" * })\n");
        }
        sb.append(" */");
        return sb.toString();
    }
}
