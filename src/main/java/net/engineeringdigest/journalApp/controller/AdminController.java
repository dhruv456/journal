package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUser = userEntryService.getAllUser();
        if(allUser != null && !allUser.isEmpty()) {
            return new ResponseEntity<>(userEntryService.getAllUser(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("create-admin-user")
    public void createAdminUser(@RequestBody User user) {
        userEntryService.saveAdminUser(user);
    }
}
