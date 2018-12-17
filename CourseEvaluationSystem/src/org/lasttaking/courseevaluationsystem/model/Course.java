package org.lasttaking.courseevaluationsystem.model;

/**
 *
 * @author D0656146
 */
public class Course {
	
	private String id;
    private String name;
    private double rate;
    
    public Teacher[] teachers;
    public Evaluation[] evaluations;
    
    public Course(String name){
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
