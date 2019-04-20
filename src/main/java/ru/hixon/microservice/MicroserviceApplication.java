package ru.hixon.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class with Spring application method.
 *
 */
@SpringBootApplication
public class MicroserviceApplication {
    /**
     * Start Spring application.
     *
     * @param args application arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(MicroserviceApplication.class, args);
    }
}
