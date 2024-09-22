package com.project208.demo.services;

import com.project208.demo.model.JournalModel;
import com.project208.demo.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Component
public class JournalServices {

    @Autowired
    private JournalRepository journalRepository;

    public void saveUserEntry(JournalModel journalModel){
        journalRepository.save(journalModel);
    }

    public List<JournalModel> findAllUsers(){
        return journalRepository.findAll();
    }

    public Optional<JournalModel> findUserById(ObjectId id){
        return journalRepository.findById(id);
    }

    public void deleteUserById(ObjectId id){
        journalRepository.deleteById(id);
    }



}
