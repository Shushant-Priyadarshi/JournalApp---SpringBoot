package com.restApi.springrest.controller;

import com.restApi.springrest.entity.JournalEntry;
import com.restApi.springrest.entity.Users;
import com.restApi.springrest.services.JournalEntryService;
import com.restApi.springrest.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserServices userServices;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesofUser(@PathVariable String userName) {
        Users user = userServices.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if( all != null && !all.isEmpty()){
            return  new ResponseEntity<>(all,HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName) {
        try {
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myid) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myid);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myid) {
        journalEntryService.deleteByID(myid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PutMapping("/id/{myid}")
//    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId myid , @RequestBody JournalEntry newEntry){
//        JournalEntry old =journalEntryService.findById(myid).orElse(null);
//        if(old != null){
//            old.setTitle(newEntry.getTitle() !=null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
//            old.setContent(newEntry.getContent() !=null && !newEntry.getContent().equals("")? newEntry.getContent() : old.getContent());
//        journalEntryService.saveEntry(old);
//        return  new ResponseEntity<>(old,HttpStatus.OK);
//
//        }
//        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}









