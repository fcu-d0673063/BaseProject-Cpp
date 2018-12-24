package org.lasttaking.courseevaluationsystem.model;

/**
 *
 * @author D0656146
 */
public class Teacher {
    
    private final String id;
    private final String name;
    private final double rate;
    
    public Course[] courses;
    public Evaluation[] evaluations;
    
    public Teacher(String id, String name, double rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
    }
    
    public Teacher(String name){
        this.name = name;
        id = "temp";
        rate = 0;
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

