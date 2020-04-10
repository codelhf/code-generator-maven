package com.example.generator.logging.slf4j;

import com.example.generator.logging.AbstractLogFactory;
import com.example.generator.logging.Log;

public class Slf4jLoggingLogFactory implements AbstractLogFactory {
    @Override
    public Log getLog(Class<?> clazz) {
        return new Slf4jImpl(clazz);
    }
}