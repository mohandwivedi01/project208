package com.project208.demo.controller;

import com.project208.demo.model.JournalModel;
import com.project208.demo.model.UserModel;
import com.project208.demo.repository.JournalRepository;
import com.project208.demo.services.JournalServices;
import com.project208.demo.services.UserServices;
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

    @Autowired
    private UserServices userServices;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
            System.out.println("###### error1 ######");
        UserModel user = userServices.findByUserName(userName);
            System.out.println("###### error2 ######");
        List<JournalModel> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()){
            System.out.println("###### error3 ######");
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalModel> getById(@PathVariable ObjectId myId){
        Optional<JournalModel> userById = journalServices.findJournalById(myId);
//        if(userById.isPresent()){
//            return new ResponseEntity<>(userById.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return userById.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("{userName}")
    public ResponseEntity<JournalModel> createEntry(@RequestBody JournalModel myEntry, @PathVariable String userName){
        try {
//            UserModel user = userServices.findByUserName(userName);
            journalServices.saveJournalEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("$$$$$$$$$  "+ e );
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/id/{userName}/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id, @PathVariable String userName){
        journalServices.deleteJournalById(id, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<?> updateById(
            @PathVariable ObjectId id,
            @RequestBody JournalModel newEntry,
            @PathVariable String userName
    ){
        JournalModel old = journalServices.findJournalById(id).orElse(null);
        if(old != null){
            old.setName(newEntry.getName() != null && !newEntry.getName().equals("") ? newEntry.getName() : old.getName());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalServices.saveJournalEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
