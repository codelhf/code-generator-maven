package com.example.generator.code.invoker.base;

/**
 * @Description: TODO
 * @Auther: liuhf
 * @CreateTime: 2019/3/16 21:17
 */
public abstract class BaseBuilder {

    public abstract BaseInvoker build();

    public boolean isParametersValid() {
        try {
            checkBeforeBuild();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public abstract void checkBeforeBuild() throws Exception;

}
