package com.project208.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
//@Getter
//@Setter
@Data
@NoArgsConstructor
public class JournalModel {
    @Id
    private ObjectId id;
    private String title;
    private String content;
}
