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

        ProjectUI ui = new ProjectUI();

        Course c = ui.findCourse("");
        Teacher t = ui.findTeacher("");
        Evaluation[] es = ui.findEvaluation("");

        ui.addCourse(new Course(""));
        ui.addEvaluation(new Evaluation(0
        ~5, new Teacher("test")
        , new Course("test") 
        "content"));
    }
    
}
