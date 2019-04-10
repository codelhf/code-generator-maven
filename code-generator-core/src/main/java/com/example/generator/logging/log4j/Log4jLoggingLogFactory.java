package com.example.generator.logging.log4j;

import com.example.generator.logging.AbstractLogFactory;
import com.example.generator.logging.Log;
import com.example.generator.logging.Log;

public class Log4jLoggingLogFactory implements AbstractLogFactory {
    @Override
    public Log getLog(Class<?> clazz) {
        return new Log4jImpl(clazz);
    }
}