package com.example.generator.config;

import java.util.List;

/**
 * @Description: dao层开始要和service能够分开,提供是否重写文件功能
 * @Auther: liuhf
 * @CreateTime: 2019/2/28 9:21
 */
public class CommonGenerator {
    /**
     * 是否重写文件
     */
    private boolean overwrite = true;
    /**
     * 是否允许子包，即targetPackage.schemaName.tableName
     */
    private boolean enableSubPackages = false;
    /**
     * dto生成器
     */
    private DtoGenerator dtoGenerator;
    /**
     * model生成器
     */
    private ModelGenerator modelGenerator;
    /**
     * dao生成器
     */
    private DaoGenerator daoGenerator;
    /**
     * mapper生成器
     */
    private MapperGenerator mapperGenerator;

    public void validate(List<String> errors){
        if (dtoGenerator == null) {
            errors.add("commonGenerator.dtoGenerator can not be empty");
        } else {
            dtoGenerator.validate(errors);
        }
        if (modelGenerator == null) {
            errors.add("commonGenerator.modelGenerator can not be empty");
        } else {
            modelGenerator.validate(errors);
        }
        if (daoGenerator == null) {
            errors.add("commonGenerator.daoGenerator can not be empty");
        } else {
            daoGenerator.validate(errors);
        }
        if (mapperGenerator == null) {
            errors.add("commonGenerator.mapperGenerator can not be empty");
        } else {
            mapperGenerator.validate(errors);
        }
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public boolean isEnableSubPackages() {
        return enableSubPackages;
    }

    public void setEnableSubPackages(boolean enableSubPackages) {
        this.enableSubPackages = enableSubPackages;
    }

    public DtoGenerator getDtoGenerator() {
        return dtoGenerator;
    }

    public void setDtoGenerator(DtoGenerator dtoGenerator) {
        this.dtoGenerator = dtoGenerator;
    }

    public ModelGenerator getModelGenerator() {
        return modelGenerator;
    }

    public void setModelGenerator(ModelGenerator modelGenerator) {
        this.modelGenerator = modelGenerator;
    }

    public DaoGenerator getDaoGenerator() {
        return daoGenerator;
    }

    public void setDaoGenerator(DaoGenerator daoGenerator) {
        this.daoGenerator = daoGenerator;
    }

    public MapperGenerator getMapperGenerator() {
        return mapperGenerator;
    }

    public void setMapperGenerator(MapperGenerator mapperGenerator) {
        this.mapperGenerator = mapperGenerator;
    }
}
