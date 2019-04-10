package com.example.generator.logging.commons;

import com.example.generator.logging.AbstractLogFactory;
import com.example.generator.logging.Log;
import com.example.generator.logging.Log;

public class JakartaCommonsLoggingLogFactory implements AbstractLogFactory {
    @Override
    public Log getLog(Class<?> clazz) {
        return new JakartaCommonsLoggingImpl(clazz);
    }
}
