package jdbcExample.presentation.cmd;

import jdbcExample.UI.Input;
import jdbcExample.presentation.viewer.CourseViewer;
import jdbcExample.presentation.viewer.MajorViewer;
import jdbcExample.presentation.viewer.StudentViewer;

public class AddItem implements MenuCommand {
    private CourseViewer courseViewer;
    private MajorViewer majorViewer;
    private StudentViewer studentViewer;

    public AddItem() {
        studentViewer = new StudentViewer();
        courseViewer = new CourseViewer();
        majorViewer = new MajorViewer();
    }

    @Override
    public int select() {
        return 2;
    }

    @Override
    public void execute() {

        System.out.println("""
                1) Add Student to the database
                2) Add Course for student
                """);
        int selectedItem = Input.getInputValue("Enter number");

        switch (selectedItem) {
            case 1 -> studentViewer.addStudent();
            case 2 -> courseViewer.addCourseForStudent();
        }
    }
}
