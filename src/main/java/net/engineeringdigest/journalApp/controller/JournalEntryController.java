package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAllJournal();
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        return journalEntryService.SaveEntry(myEntry);
    }

    @GetMapping("/id/{id}")
    public JournalEntry getEntryById(@PathVariable ObjectId id){
        return journalEntryService.getJournalById(id).orElse(null);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<JournalEntry> deleteEntryRecord(@PathVariable ObjectId id){
        Optional<JournalEntry> deleted = journalEntryService.deleteById(id);
        return deleted.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateEntryRecord(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry){
        Optional<JournalEntry> old = journalEntryService.updateJournalById(id, journalEntry);
        return old.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).body(null));
    }
}
