package com.project208.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class JournalModel {
    @Id
    private ObjectId id;
    private String name;
    private String content;
}
