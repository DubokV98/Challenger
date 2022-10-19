package org.challenger.storageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author u.dubok
 * @since 9/29/2022
 */
@SpringBootApplication
public class StorageServiceApplication {
    /**
     * Application entry point.
     *
     * @param args passed to app
     */
    public static void main(final String[] args) {
        SpringApplication.run(StorageServiceApplication.class, args);
    }
}
