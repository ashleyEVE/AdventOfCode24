package com.ashley_eve;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application class that implements CommandLineRunner.
 * This class serves as the entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    /** Static class logger. */
    private static final Logger LOG = LogManager.getLogger(Application.class);

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args The command-line arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * This method is called after the Spring Boot application starts.
     *
     * @param args Command-line arguments.
     * @throws Exception if an error occurs.
     */
    @Override
    public void run(final String... args) throws Exception {
        LOG.debug("Hello world");
    }
}