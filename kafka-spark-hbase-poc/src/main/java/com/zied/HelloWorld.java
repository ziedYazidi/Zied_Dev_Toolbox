package com.zied;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Used to test that log is being sent correctly to kafka using log4j appender
 */
public class HelloWorld {
    private static final Logger logger = LogManager.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        logger.info("Hello World");
    }
}
