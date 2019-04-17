package com.example.generator.code.task.base;

import com.example.generator.util.Messages;
import com.example.generator.util.StringUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public class FileUtil {

    /**
     * @param type     使用模板类型
     * @param data     填充数据
     * @param filePath 输出文件
     * @throws IOException
     * @throws TemplateException
     */
    public static void generateToCode(String filePath, String fileName, Object data,
                                      int type, boolean generate, boolean override)
            throws IOException, TemplateException {
        if (generate) {
            File directory = new File(filePath);
            if (!directory.exists()) {
                directory.mkdirs();
                directory.setWritable(true);
            }
            File file = new File(filePath + fileName);
            if (file.exists()) {
                if (override) {
                    writerFile(filePath + fileName, data, type);
                } else {
                    file = getUniqueFileName(file.getParentFile(), file.getName());
                    writerFile(file.getAbsolutePath(), data, type);
                }
            } else {
                writerFile(filePath + fileName, data, type);
            }
        }
    }

    public static void writerFile(String filePath, Object data, int type)
            throws IOException, TemplateException {
        // 获取模板文件
        Template tpl = FreemarkerUtil.getTemplate(type);
        // 写入文件
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw, 1024);
        // 填充数据
        tpl.process(data, bw);
        bw.flush();
        fos.close();
    }

    public static String getGeneratePath(String configFilePath, String targetProject, String targetPackage) {
        StringBuilder sb = new StringBuilder();
        if (!targetProject.endsWith("/")) {
            targetProject = targetProject + "/";
        }
        sb.append(getProjectPath(configFilePath)).append(targetProject).append(package2Path(targetPackage));
        return sb.toString();
    }

    /**
     * 获取项目路径
     */
    public static String getProjectPath(String configFilePath) {
//        String path = new File(FileUtil.class.getClassLoader().getResource("").getFile()).getPath() + File.separator;
        StringBuilder sb = new StringBuilder();
        sb.append(configFilePath, 0, configFilePath.indexOf("src"));
        return sb.toString();
    }

    /**
     * 获取源码路径
     */
    public static String getSourcePath(String configFilePath) {
        StringBuilder sb = new StringBuilder();
        sb.append(getProjectPath(configFilePath)).append("src").append(File.separator).append("main").append(File.separator).append("java").append(File.separator);
        return sb.toString();
    }

    /**
     * 获取资源文件路径
     */
    public static String getResourcePath(String configFilePath) {
        StringBuilder sb = new StringBuilder();
        sb.append(getProjectPath(configFilePath)).append("src").append(File.separator).append("main").append(File.separator).append("resources").append(File.separator);
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
        File answer = null;

        // try up to 1000 times to generate a unique file name
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 1000; i++) {
            sb.setLength(0);
            sb.append(fileName).append('.').append(i);

            File testFile = new File(directory, sb.toString());
            if (!testFile.exists()) {
                answer = testFile;
                break;
            }
        }

        if (answer == null) {
            throw new RuntimeException(Messages.getString("RuntimeError.3", directory.getAbsolutePath()));
        }
        return answer;
    }

}
