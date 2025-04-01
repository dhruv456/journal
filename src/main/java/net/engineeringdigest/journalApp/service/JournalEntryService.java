package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public boolean SaveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
        return true;
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

    public Optional<JournalEntry> deleteById(ObjectId id) {
        Optional<JournalEntry> del = journalEntryRepository.findById(id);
        if(del.isPresent()) journalEntryRepository.deleteById(id);
        return del;
    }
}
