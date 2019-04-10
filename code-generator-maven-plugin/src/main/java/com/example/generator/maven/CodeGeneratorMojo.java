package com.example.generator.maven;

import com.example.generator.api.CodeGenerator;
import com.example.generator.config.Configuration;
import com.example.generator.config.util.ConfigUtil;
import com.example.generator.logging.LogFactory;
import com.example.generator.util.ClassloaderUtil;
import com.example.generator.util.Messages;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/1/23 9:26
 */
@Mojo(name = "generate",
        defaultPhase = LifecyclePhase.GENERATE_SOURCES,
        requiresDependencyResolution = ResolutionScope.TEST)
public class CodeGeneratorMojo extends AbstractMojo {

    private ThreadLocal<ClassLoader> savedClassloader = new ThreadLocal<>();

    /**
     * Maven Project.
     */
    @Parameter(property = "project", required = true, readonly = true)
    private MavenProject project;

    /**
     * Location of the configuration file.
     */
    @Parameter(property = "code.generator.configurationFile",
            defaultValue = "${project.basedir}/src/main/resources/generatorConfig.xml", required = true)
    private File configurationFile;

    /**
     * Output Directory.
     */
    @Parameter(property = "code.generator.outputDirectory",
            defaultValue = "${project.build.directory}/generated-sources/code-generator", required = true)
    private File outputDirectory;

    /**
     * If true, then dependencies in scope compile, provided, and system scopes will be
     * added to the classpath of the generator.  These dependencies will be searched for
     * JDBC drivers, root classes, root interfaces, generator plugins, etc.
     */
    @Parameter(property = "code.generator.includeCompileDependencies", defaultValue = "false")
    private boolean includeCompileDependencies;

    /**
     * If true, then dependencies in all scopes will be
     * added to the classpath of the generator.  These dependencies will be searched for
     * JDBC drivers, root classes, root interfaces, generator plugins, etc.
     */
    @Parameter(property = "code.generator.includeAllDependencies", defaultValue = "false")
    private boolean includeAllDependencies;

    @Override
    public void execute() throws MojoExecutionException {
        System.out.println("hello");
        saveClassLoader();
        LogFactory.setLogFactory(new MavenLogFactory(this));
        calculateClassPath();

        // add resource directories to the classpath.  This is required to support
        // use of a properties file in the build.  Typically, the properties file
        // is in the project's source tree, but the plugin classpath does not
        // include the project classpath.
        if (configurationFile == null) {
            throw new MojoExecutionException(Messages.getString("RuntimeError.0"));
        }

        if (!configurationFile.exists()) {
            throw new MojoExecutionException(Messages.getString("RuntimeError.1", configurationFile.toString()));
        }

//        List<Resource> resources = project.getResources();
//        List<String> resourceDirectories = new ArrayList<>();
//        for (Resource resource: resources) {
//            resourceDirectories.add(resource.getDirectory());
//        }
//        ClassLoader cl = ClassloaderUtil.getCustomClassloader(resourceDirectories);
//        ObjectFactory.addExternalClassLoader(cl);
        Configuration config = ConfigUtil.getConfiguration(configurationFile);

//        ShellCallback callback = new MavenShellCallback(this);
        CodeGenerator codeGenerator = new CodeGenerator(config);
        codeGenerator.generate(new MavenProgressCallback(getLog(), true));

        if (project != null && outputDirectory != null && outputDirectory.exists()) {
            String absolutePath = outputDirectory.getAbsolutePath();
            project.addCompileSourceRoot(absolutePath);
            Resource resource = new Resource();
            resource.setDirectory(absolutePath);
            resource.addInclude("**/*.xml");
            project.addResource(resource);
        }

        restoreClassLoader();
    }

    private void calculateClassPath() throws MojoExecutionException {
        if (includeCompileDependencies || includeAllDependencies) {
            try {
                // add the project compile classpath to the plugin classpath,
                // so that the project dependency classes can be found
                // directly, without adding the classpath to configuration's classPathEntries
                // repeatedly.Examples are JDBC drivers, root classes, root interfaces, etc.
                Set<String> entries = new HashSet<>();
                if (includeCompileDependencies) {
                    entries.addAll(project.getCompileClasspathElements());
                }

                if (includeAllDependencies) {
                    entries.addAll(project.getTestClasspathElements());
                }

                // remove the output directories (target/classes and target/test-classes)
                // because this mojo runs in the generate-sources phase and
                // those directories have not been created yet (typically)
                entries.remove(project.getBuild().getOutputDirectory());
                entries.remove(project.getBuild().getTestOutputDirectory());

                ClassLoader contextClassLoader = ClassloaderUtil.getCustomClassloader(entries);
                Thread.currentThread().setContextClassLoader(contextClassLoader);
            } catch (DependencyResolutionRequiredException e) {
                throw new MojoExecutionException("Dependency Resolution Required", e);
            }
        }
    }

    private void saveClassLoader() {
        savedClassloader.set(Thread.currentThread().getContextClassLoader());
    }

    private void restoreClassLoader() {
        Thread.currentThread().setContextClassLoader(savedClassloader.get());
    }
}
