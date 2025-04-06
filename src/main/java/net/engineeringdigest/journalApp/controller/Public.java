package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class Public {
    @Autowired
    UserEntryService userEntryService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "<h1>OK</h1>";
    }

    @PostMapping("/create-user")
    public ResponseEntity<Boolean> createUser(@RequestBody User user) {
        try {
            userEntryService.newSaveEntry(user);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }
}
