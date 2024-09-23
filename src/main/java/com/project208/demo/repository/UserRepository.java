package com.project208.demo.repository;


import com.project208.demo.model.JournalModel;
import com.project208.demo.model.UserModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, ObjectId>{
    UserModel findByUserName(String userName);
}
