package com.project208.demo.services;

import com.project208.demo.model.JournalModel;
import com.project208.demo.model.UserModel;
import com.project208.demo.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalServices {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserServices userServices;

    @Transactional //it makes the complete fuction as a single operation to achive atomicity(the complete operation either success or faild not parsially)
    //taking journal entry and username to save
    public void saveJournalEntry(JournalModel journalEntry, String userName){
        try {
            //getting the user by userName to register the journal entry to the user
            UserModel user = userServices.findByUserName(userName);
            JournalModel savedEntry = journalRepository.save(journalEntry);
            //register the journal entry to user's journalEntry field
            user.getJournalEntries().add(savedEntry);
//            user.setUserName(null);
            userServices.saveUserEntry(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("an error occurred during transaction");
        }
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
