package com.inetum.livetooling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class LogTest {
    private static final Logger logger = LogManager.getLogger(LogTest.class);
    @Test
    void the_log_should_write(){
        RuntimeException error = new RuntimeException("exceptioooon");
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal message");
        logger.error("Hello error world!", error);
        logger.error("hola ramon");
    }
}
