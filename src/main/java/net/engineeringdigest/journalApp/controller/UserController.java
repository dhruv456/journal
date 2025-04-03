package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserEntryService userEntryService;

    @GetMapping
    public List<User> getAllUser() {
        return userEntryService.getAllUser();
    }

    @PostMapping
    public boolean createUser(@RequestBody User user) {
        userEntryService.SaveEntry(user);
        return true;
    }

    @PutMapping
    public boolean updateUser(@RequestBody User user) {
        userEntryService.updateUserByName(user);
        return true;
    }
}
