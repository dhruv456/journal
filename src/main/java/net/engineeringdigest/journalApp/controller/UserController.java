package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.StarwarService;
import net.engineeringdigest.journalApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserEntryService userEntryService;
    @Autowired
    StarwarService starwarService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<List<User>>(userEntryService.getAllUser(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Boolean> updateUser(@RequestBody User user) {
        userEntryService.updateUserByName(user);
        return ResponseEntity.status(200).body(true);
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String randomFacts = starwarService.getRandomStarwarFacts();
        return new ResponseEntity<>(authentication.getName() + "\nFacts: " + randomFacts, HttpStatus.OK);
    }
}
