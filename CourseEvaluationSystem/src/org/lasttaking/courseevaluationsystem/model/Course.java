package org.lasttaking.courseevaluationsystem.model;

/**
 *
 * @author D0656146
 */
public class Course {

    private final String id;
    private final String name;
    private final double rate;

    public Teacher[] teachers;
    public Evaluation[] evaluations;

    public Course(String id, String name, double rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
    }
    
    public Course(String name){
        this.name = name;
        id = "temp";
        rate = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

}
