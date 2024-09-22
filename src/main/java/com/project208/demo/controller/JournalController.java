package com.project208.demo.controller;

import com.project208.demo.model.JournalModel;
import com.project208.demo.repository.JournalRepository;
import com.project208.demo.services.JournalServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalServices journalServices;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalModel> all = journalServices.findAllUsers();
        if (all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalModel> getById(@PathVariable ObjectId myId){
        Optional<JournalModel> userById = journalServices.findUserById(myId);
//        if(userById.isPresent()){
//            return new ResponseEntity<>(userById.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return userById.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<JournalModel> createEntry(@RequestBody JournalModel myEntry){
        try {
            journalServices.saveUserEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id){
        journalServices.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId id, @RequestBody JournalModel newEntry){
        JournalModel old = journalServices.findUserById(id).orElse(null);
        if(old != null){
            old.setName(newEntry.getName() != null && !newEntry.getName().equals("") ? newEntry.getName() : old.getName());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalServices.saveUserEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
