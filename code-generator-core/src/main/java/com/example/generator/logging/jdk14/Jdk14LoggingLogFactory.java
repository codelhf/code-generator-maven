package com.example.generator.logging.jdk14;

import com.example.generator.logging.AbstractLogFactory;
import com.example.generator.logging.Log;

public class Jdk14LoggingLogFactory implements AbstractLogFactory {
    @Override
    public Log getLog(Class<?> clazz) {
        return new Jdk14LoggingImpl(clazz);
    }
}