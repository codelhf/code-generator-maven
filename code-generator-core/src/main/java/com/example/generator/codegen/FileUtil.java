package com.example.generator.codegen;

import com.example.generator.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.*;
import java.util.Map;

/**
 * @Description: 文件工具类
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public class FileUtil {

    /**
     * @description: 生成代码文件
     * @author liuhf
     * @createtime 2019/5/6 23:04
     *
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @param tpl  文件模板
     * @param data 模板数据
     * @param override 是否覆盖原有文件
     * @throws IOException
     */
    public static void generateToCode(String filePath, String fileName, String fileEncode, Template tpl,
                                      Map<String, Object> data, boolean override) throws IOException {
        File directory = new File(filePath);
        if (!directory.exists()) {
            directory.mkdirs();
            directory.setWritable(true);
        }
        File file = new File(directory + "/" + fileName);
        if (!file.exists() || override) {
            writerFile(file.getAbsolutePath(), fileEncode, tpl, data);
        } else {
            file = getUniqueFileName(file.getParentFile(), file.getName());
            writerFile(file.getAbsolutePath(), fileEncode, tpl, data);
        }
        System.out.println("[INFO] generating " + directory);
        System.out.println("[INFO] Generating " + fileName);
    }

    /**
     * 写入文件
     */
    public static void writerFile(String filePath, String fileEncode, Template tpl, Map<String, Object> data) throws IOException {
        if (StringUtil.isBlank(fileEncode)) {
            fileEncode = "UTF-8";
        }
        // 获取文件的输出流
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), fileEncode);
        BufferedWriter bw = new BufferedWriter(osw, 1024);
        // 填充数据
        tpl.merge(new VelocityContext(data), bw);
        bw.flush();
        bw.close();
    }

    /**
     * 获取生成文件路径
     */
    public static String getGeneratePath(String directory, String packageName, String projectDir) {
        StringBuilder sb = new StringBuilder();
        if (!directory.endsWith("/")) {
            directory = directory + "/";
        }
        //判断是否为相对路径
        if (directory.startsWith("./")){
            //截取"."
            directory = directory.substring(1);
            sb.append(projectDir);
        }
        sb.append(directory).append(package2Path(packageName));
        return sb.toString();
    }

    /**
     * 根据包名获取包路径
     */
    public static String package2Path(String packageName) {
        if (StringUtil.isBlank(packageName)) {
            return StringUtil.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        String[] packages = packageName.split("\\.");
        for (String str : packages) {
            sb.append(str).append(File.separator);
        }
        return sb.toString();
    }

    /**
     * Gets the unique file name.
     *
     * @param directory
     *            the directory
     * @param fileName
     *            the file name
     * @return the unique file name
     */
    public static File getUniqueFileName(File directory, String fileName) {
        File uniqueName = null;

        // try up to 1000 times to generate a unique file name
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 1000; i++) {
            sb.setLength(0);
            sb.append(fileName).append('.').append(i);

            File testFile = new File(directory, sb.toString());
            if (!testFile.exists()) {
                uniqueName = testFile;
                break;
            }
        }

        if (uniqueName == null) {
            throw new RuntimeException("Cannot generate unique file name in directory {" + directory.getAbsolutePath() + "}");
        }
        return uniqueName;
    }

}
