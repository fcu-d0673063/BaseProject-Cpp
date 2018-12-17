package org.lasttaking.courseevaluationsystem.model;

/**
 *
 * @author D0656146
 */
public class Teacher {
    
    private String id;
    private String name;
    private double rate;
    
    public Course[] courses;
    public Evaluation[] evaluations;
    
    public Teacher(String name){
        this.name = name;
    }
    
    public String getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public double getRate(){
        return rate;
    }
}

