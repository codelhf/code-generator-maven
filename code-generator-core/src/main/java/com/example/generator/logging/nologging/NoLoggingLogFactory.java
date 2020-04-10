package com.example.generator.logging.nologging;

import com.example.generator.logging.AbstractLogFactory;
import com.example.generator.logging.Log;

public class NoLoggingLogFactory implements AbstractLogFactory {
    @Override
    public Log getLog(Class<?> clazz) {
        return new NoLoggingImpl(clazz);
    }
}