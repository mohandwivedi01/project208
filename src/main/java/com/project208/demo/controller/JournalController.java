package com.project208.demo.controller;

import com.project208.demo.model.JournalModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    private Map<Long, JournalModel> journalModelMap = new HashMap<>();

    @GetMapping()
    public List<JournalModel> getAll(){
        return new ArrayList<>(journalModelMap.values());
    }

    @PostMapping()
    public boolean createEntry(@RequestBody JournalModel journalModelEntry){
        journalModelMap.put(journalModelEntry.getId(), journalModelEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalModel getJournalById(@PathVariable Long myId){
        return journalModelMap.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalModel deleteJournalById(@PathVariable Long myId){
        return journalModelMap.remove(myId);
    }

    @PutMapping("id/{id}")
    public JournalModel updateJournalById(@PathVariable Long id, @RequestBody JournalModel myEntry){
        return journalModelMap.put(id, myEntry);
    }

}
