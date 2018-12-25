package org.lasttaking.courseevaluationsystem.model;

import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import org.bson.Document;

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

    public MyDatabase(String host, int port) {
        try {
            mongoClient = new MongoClient(host, port);
            mongoDatabase = mongoClient.getDatabase("ces");
            teachers = mongoDatabase.getCollection("teacher");
            courses = mongoDatabase.getCollection("course");
            evaluations = mongoDatabase.getCollection("evaluation");

            System.out.println("Connect to database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public boolean addCourse(Course course) {
        if(courses.count(Filters.eq("name", course.getName())) != 0){
            return false;
        }
        
        Document document = new Document("name", course.getName())
                .append("rate", 0.0)
                .append("evaluations", new ArrayList<Evaluation>());
        courses.insertOne(document);
        return true;
    }

    public boolean addTeacher(Teacher teacher) {
        if(teachers.count(Filters.eq("name", teacher.getName())) != 0){
            return false;
        }
        
        Document document = new Document("name", teacher.getName())
                .append("rate", 0.0)
                .append("evaluations", new ArrayList<Evaluation>());
        teachers.insertOne(document);
        return true;
    }

    public boolean addEvaluation(Evaluation evaluation) {
        if(teachers.count(Filters.eq("name", evaluation.getTeacher().getName())) == 0
                || courses.count(Filters.eq("name", evaluation.getCourse().getName())) == 0){
            return false;
        }
        
        Document document = new Document("rate", evaluation.getScore())
                .append("teacher", evaluation.getTeacher().getName())
                .append("course", evaluation.getCourse().getName())
                .append("comment", evaluation.getComment());
        evaluations.insertOne(document);
        
        Document course = courses.find(Filters.eq("name", evaluation.getCourse().getName())).first();
        ArrayList<Evaluation> coursesEva = (ArrayList<Evaluation>)course.get("evaluations");
        coursesEva.add(evaluation);
        
        double courseRate = 0.0;
        for(Evaluation eva : coursesEva){
            courseRate += eva.getScore();
        }
        courseRate /= coursesEva.size();
        
        courses.updateOne(Filters.eq("name", evaluation.getCourse().getName())
                ,new Document("$set", new Document("rate", courseRate)));
        
        Document teacher = teachers.find(Filters.eq("name", evaluation.getTeacher().getName())).first();
        ArrayList<Evaluation> teachersEva = (ArrayList<Evaluation>)teacher.get("evaluations");
        teachersEva.add(evaluation);
        
        double teacherRate = 0.0;
        for(Evaluation eva : teachersEva){
            teacherRate += eva.getScore();
        }
        teacherRate /= teachersEva.size();
        
        teachers.updateOne(Filters.eq("name", evaluation.getTeacher().getName())
                ,new Document("$set", new Document("rate", teacherRate)));
        
        return true;
    }

    public ArrayList<Evaluation> findCourse(String name) {
        FindIterable<Document> iterable = evaluations.find(Filters.eq("course", name));
        MongoCursor<Document> cursor = iterable.iterator();
        ArrayList<Evaluation> result = new ArrayList<>();
        
        while (cursor.hasNext()) {
            Document temp = cursor.next();
            result.add(new Evaluation((int) temp.get("rate"),
                     new Teacher((String) temp.get("teacher")),
                     new Course((String) temp.get("course")),
                     (String) temp.get("comment")));
        }

        return result;
    }

    public ArrayList<Evaluation> findTeacher(String name) {
        FindIterable<Document> iterable = evaluations.find(Filters.eq("teacher", name));
        MongoCursor<Document> cursor = iterable.iterator();
        ArrayList<Evaluation> result = new ArrayList<>();
        
        while (cursor.hasNext()) {
            Document temp = cursor.next();
            result.add(new Evaluation((int) temp.get("rate"),
                     new Teacher((String) temp.get("teacher")),
                     new Course((String) temp.get("course")),
                     (String) temp.get("comment")));
        }

        return result;
    }

    public ArrayList<Evaluation> findEvaluation(String keyword) {
        FindIterable<Document> iterable = evaluations.find();
        MongoCursor<Document> cursor = iterable.iterator();
        ArrayList<Evaluation> result = new ArrayList<>();
        
        while (cursor.hasNext()) {
            Document temp = cursor.next();
            if (((String) temp.get("comment")).contains(keyword)) {
                result.add(new Evaluation((int) temp.get("rate"),
                         new Teacher((String) temp.get("teacher")),
                         new Course((String) temp.get("course")),
                         (String) temp.get("comment")));
            }
        }

        return result;
    }
}
