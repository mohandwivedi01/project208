package com.project208.demo.services;

import com.project208.demo.model.JournalModel;
import com.project208.demo.model.UserModel;
import com.project208.demo.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalServices {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserServices userServices;

    //taking journal entry and username to save
    public void saveJournalEntry(JournalModel journalEntry, String userName){
        //getting the user by userName to register the journal entry to the user
        System.out.println("###### error1 ######");
        UserModel user = userServices.findByUserName(userName);
        System.out.println("###### error2 ######");
        JournalModel savedEntry = journalRepository.save(journalEntry);
        System.out.println("###### error3 ######");
        //register the journal entry to user's journalEntry field
        user.getJournalEntries().add(savedEntry);
        System.out.println("###### error4 ######");
        userServices.saveUserEntry(user);
    }

    public void saveJournalEntry(JournalModel journalEntry){
        journalRepository.save(journalEntry);
    }
    public List<JournalModel> findAllJournals(){
        return journalRepository.findAll();
    }

    public Optional<JournalModel> findJournalById(ObjectId id){
        return journalRepository.findById(id);
    }

    public void deleteJournalById(ObjectId id, String userName){
        UserModel user = userServices.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userServices.saveUserEntry(user);
        journalRepository.deleteById(id);
    }



}
