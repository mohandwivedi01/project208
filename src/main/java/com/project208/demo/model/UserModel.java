package com.project208.demo.model;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class UserModel {
    @Id
    private ObjectId id;
    @Indexed(unique = true) //Create each time a unique user name
    // (Mongodb not do indexing automatically for that we have to specify in application propeerties)
    @NonNull
    private String userName;
    @NonNull //if any field that annotated with NonNull annotation is not present than it through a null pointer ecxeption
    private String password;
    @DBRef
    private List<JournalModel> journalEntries = new ArrayList<>();
}
