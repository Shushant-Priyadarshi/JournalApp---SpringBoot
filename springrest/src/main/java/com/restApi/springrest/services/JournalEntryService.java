package com.restApi.springrest.services;

import com.restApi.springrest.entity.JournalEntry;
import com.restApi.springrest.entity.Users;
import com.restApi.springrest.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepository;

    @Autowired
    private UserServices userServices;

    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            Users user = userServices.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userServices.saveUser(user);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteByID(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }

}
