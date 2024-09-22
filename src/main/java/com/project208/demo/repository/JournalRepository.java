package com.project208.demo.repository;


import com.project208.demo.model.JournalModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalModel, ObjectId>{
}
