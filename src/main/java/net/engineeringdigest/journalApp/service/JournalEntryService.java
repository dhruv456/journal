package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryService userEntryService;

    @Transactional
    public void SaveEntry(JournalEntry journalEntry, String username) {
        try {
            journalEntry.setDate(LocalDateTime.now());
            User user = userEntryService.findByUserName(username);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userEntryService.SaveEntry(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public List<JournalEntry> getAllJournal() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public Optional<JournalEntry> updateJournalById(ObjectId id, JournalEntry newEntry) {
        Optional<JournalEntry> oldEntry = journalEntryRepository.findById(id);
        if(oldEntry.isPresent()) {
            JournalEntry oldEntryOg = oldEntry.get();
            oldEntryOg.setContent((newEntry.getContent() == null || newEntry.getContent() == "") ? oldEntryOg.getContent() : newEntry.getContent());
            oldEntryOg.setTitle((newEntry.getTitle() == null || newEntry.getTitle() == "") ? oldEntryOg.getTitle() : newEntry.getTitle());
            journalEntryRepository.save(oldEntryOg);
        }
        return oldEntry;
    }

    public boolean deleteById(ObjectId id, String username) {
        User user = userEntryService.findByUserName(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        journalEntryRepository.deleteById(id);
        userEntryService.SaveEntry(user);
        return true;
    }
}
