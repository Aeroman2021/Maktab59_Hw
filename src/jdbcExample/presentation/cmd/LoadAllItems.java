package jdbcExample.presentation.cmd;

import jdbcExample.UI.Input;
import jdbcExample.presentation.viewer.CourseViewer;
import jdbcExample.presentation.viewer.MajorViewer;
import jdbcExample.presentation.viewer.StudentViewer;

public class LoadAllItems implements MenuCommand {

    private CourseViewer courseViewer;
    private MajorViewer majorViewer;
    private StudentViewer studentViewer;


    public LoadAllItems() {
        studentViewer = new StudentViewer();
        courseViewer = new CourseViewer();
        majorViewer = new MajorViewer();

    }

    @Override
    public int select() {
        return 6;
    }

    @Override
    public void execute() {
        System.out.println("""
                To To Load All entity  please select a correspond number:
                1) Student
                2) Course
                3) Major
                """);
        int selectedItem = Input.getInputValue("Enter number");

        switch (selectedItem) {
            case 1 -> studentViewer.loadAllStudents();
            case 2 -> courseViewer.loadAllCourses();
            case 3 -> majorViewer.loadAllMajors();
        }
    }

}
