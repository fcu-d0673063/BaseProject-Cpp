package org.lasttaking.courseevaluationsystem.ui;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.lasttaking.courseevaluationsystem.model.*;

/**
 *
 * @author D0656146
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("ces");
        db.createCollection("teacher");
        db.createCollection("course");
        db.createCollection("evaluation");


        /*ProjectUI ui = new ProjectUI();

        Course c = ui.findCourse("");
        
        Teacher t = ui.findTeacher("");
        
        Evaluation[] es = ui.findEvaluation("");

        ui.addCourse(new Course(""));
        
        ui.addTeacher(new Teacher(""));
        
        ui.addEvaluation(new Evaluation(0
        ~5, new Teacher("test")
        , new Course("test") 
        "content"));
        
        //Evaluation e;
        //delEvaluation(e);*/
    }

}
