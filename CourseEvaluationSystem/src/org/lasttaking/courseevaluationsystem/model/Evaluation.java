package org.lasttaking.courseevaluationsystem.model;

/**
 *
 * @author D0656146
 */
public class Evaluation {
    
	private String id;
    private int score;
    private Teacher teacher;
    private Course course;
    private String comment;
   
    

    
    public String getId(){
        return id;
    }
    
    public int getScore(){
        return score;
    }
    
    public Teacher getTeacher(){
        return teacher;
    }
    
    public Course getCourse(){
        return course;
    }
    
    public String getComment(){
        return comment;
    }
    
	
}
