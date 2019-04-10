package com.example.generator.logging;

public interface AbstractLogFactory {
    Log getLog(Class<?> targetClass);
}
