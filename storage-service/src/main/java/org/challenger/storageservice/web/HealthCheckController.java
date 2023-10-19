package org.challenger.storageservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author u.dubok
 * @since 10/19/2023
 */
@RestController
@RequestMapping("/api/storage/health-check")
public class HealthCheckController {
    @GetMapping
    ResponseEntity<HttpStatus> healthCheck() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
