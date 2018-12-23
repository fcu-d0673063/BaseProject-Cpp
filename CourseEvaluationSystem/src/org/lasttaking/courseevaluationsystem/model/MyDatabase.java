package org.lasttaking.courseevaluationsystem.model;

import com.mongodb.MongoClient;
import com.mongodb.client.*;
import java.util.Properties;
import org.bson.Document;
//import com.mongodb.*;

/**
 *
 * @author D0656146
 */
public class MyDatabase {

    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    MongoCollection<Document> teachers;
    MongoCollection<Document> courses;
    MongoCollection<Document> evaluations;

    public MyDatabase(String url) {
        //Properties properties = new //Properties();
        try {
            mongoClient = new MongoClient(url);
            mongoDatabase = mongoClient.getDatabase("ces");
            teachers = mongoDatabase.getCollection("teacher");
            courses = mongoDatabase.getCollection("course");
            evaluations = mongoDatabase.getCollection("evaluation");
            
            System.out.println("Connect to database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    // = new MongoClients("localhost", 27021)
}
