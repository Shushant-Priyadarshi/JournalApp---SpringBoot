package com.restApi.springrest.services;

import com.restApi.springrest.entity.JournalEntry;
import com.restApi.springrest.entity.Users;
import com.restApi.springrest.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepository;

    @Autowired
    private UserServices userServices;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            Users user = userServices.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userServices.saveUser(user);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            throw new RuntimeException("An error occurred while saving the entry:", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        try {
            journalEntryRepository.save(journalEntry);

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

    @Transactional
    public boolean deleteByID(ObjectId id, String userName) {
        boolean removed = false;
        try{
            Users user = userServices.findByUserName(userName);
             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userServices.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }catch(Exception e){
            System.out.println(e);
            throw  new RuntimeException("Error while deleting the entry: "+e);
        }
        return removed;

    }

//    public List<JournalEntry> findByUserName(String userName){
//
//    }
}

