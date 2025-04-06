package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntryService {

    @Autowired
    private UserRepository userEntryRepository;
    private final PasswordEncoder psw = new BCryptPasswordEncoder();

    public void SaveEntry(User user) {
        userEntryRepository.save(user);
    }

    public void newSaveEntry(User user) {
        user.setPassword(psw.encode(user.getPassword()));
        userEntryRepository.save(user);
    }

    public User findByUserName(String username) {
        return userEntryRepository.findByUsername(username);
    }

    public List<User> getAllUser() {
        return userEntryRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId id) {
        return userEntryRepository.findById(id);
    }

    public User updateUserByName(User newEntry) {
        User oldEntry = userEntryRepository.findByUsername(newEntry.getUsername());
        if(oldEntry != null) {
            oldEntry.setUsername((newEntry.getUsername() == null || newEntry.getUsername().isEmpty()) ? oldEntry.getUsername() : newEntry.getUsername());
            oldEntry.setPassword((newEntry.getPassword() == null || newEntry.getPassword().isEmpty()) ? oldEntry.getPassword() : newEntry.getPassword());
            userEntryRepository.save(oldEntry);
        }
        return oldEntry;
    }

//    public Optional<JournalEntry> deleteById(ObjectId id) {
//        Optional<JournalEntry> del = userEntryRepository.findById(id);
//        if(del.isPresent()) userEntryRepository.deleteById(id);
//        return del;
//    }
}
