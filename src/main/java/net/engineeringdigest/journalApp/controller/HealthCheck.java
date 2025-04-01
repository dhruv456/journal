package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class HealthCheck {
    @GetMapping("/health-check")
    public String healthCheck() {
        return "<h1>OK</h1>";
    }
}
