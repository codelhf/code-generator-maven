package com.example.generator.logging;

import com.example.generator.logging.commons.JakartaCommonsLoggingLogFactory;
import com.example.generator.logging.jdk14.Jdk14LoggingLogFactory;
import com.example.generator.logging.log4j.Log4jLoggingLogFactory;
import com.example.generator.logging.log4j2.Log4j2LoggingLogFactory;
import com.example.generator.logging.nologging.NoLoggingLogFactory;
import com.example.generator.logging.slf4j.Slf4jLoggingLogFactory;
import com.example.generator.logging.log4j2.Log4j2LoggingLogFactory;
import com.example.generator.logging.nologging.NoLoggingLogFactory;
import com.example.generator.logging.slf4j.Slf4jLoggingLogFactory;
import com.example.generator.util.Messages;
import com.example.generator.util.Messages;

public class LogFactory {
    private static AbstractLogFactory logFactory;
    public static String MARKER = "MYBATIS-GENERATOR"; //$NON-NLS-1$

    static {
        tryImplementation(new Slf4jLoggingLogFactory());
        tryImplementation(new JakartaCommonsLoggingLogFactory());
        tryImplementation(new Log4j2LoggingLogFactory());
        tryImplementation(new Log4jLoggingLogFactory());
        tryImplementation(new Jdk14LoggingLogFactory());
        tryImplementation(new NoLoggingLogFactory());
    }

    public static Log getLog(Class<?> clazz) {
        try {
            return logFactory.getLog(clazz);
        } catch (Throwable t) {
            throw new RuntimeException(Messages.getString("RuntimeError.21", //$NON-NLS-1$
                    clazz.getName(), t.getMessage()), t);
        }
    }

    /**
     * This method will switch the logging implementation to Java native
     * logging. This is useful in situations where you want to use Java native
     * logging to log activity but Log4J is on the classpath. Note that this
     * method is only effective for log classes obtained after calling this
     * method. If you intend to use this method you should call it before
     * calling any other method.
     */
    public static synchronized void forceJavaLogging() {
        setImplementation(new Jdk14LoggingLogFactory());
    }

    public static synchronized void forceSlf4jLogging() {
        setImplementation(new Slf4jLoggingLogFactory());
    }

    public static synchronized void forceCommonsLogging() {
        setImplementation(new JakartaCommonsLoggingLogFactory());
    }

    public static synchronized void forceLog4jLogging() {
        setImplementation(new Log4jLoggingLogFactory());
    }

    public static synchronized void forceLog4j2Logging() {
        setImplementation(new Log4j2LoggingLogFactory());
    }

    public static synchronized void forceNoLogging() {
        setImplementation(new NoLoggingLogFactory());
    }

    public static void setLogFactory(AbstractLogFactory logFactory) {
        setImplementation(logFactory);
    }

    private static void tryImplementation(AbstractLogFactory factory) {
        if (logFactory == null) {
            try {
                setImplementation(factory);
            } catch (LogException e) {
                // ignore
            }
        }
    }

    private static void setImplementation(AbstractLogFactory factory) {
        try {
            Log log = factory.getLog(LogFactory.class);
            if (log.isDebugEnabled()) {
                log.debug("Logging initialized using '" + factory + "' adapter."); //$NON-NLS-1$ //$NON-NLS-2$
            }
            logFactory = factory;
        } catch (Throwable t) {
            throw new LogException("Error setting Log implementation.  Cause: " + t.getMessage(), t); //$NON-NLS-1$
        }
    }
}
