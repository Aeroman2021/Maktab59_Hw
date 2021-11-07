package jdbcExample.presentation.cmd;

import jdbcExample.UI.Input;
import jdbcExample.presentation.viewer.CourseViewer;
import jdbcExample.presentation.viewer.MajorViewer;
import jdbcExample.presentation.viewer.StudentViewer;

public class LoadItemById implements MenuCommand {

    private CourseViewer courseViewer;
    private MajorViewer majorViewer;
    private StudentViewer studentViewer;


    public LoadItemById() {
        studentViewer = new StudentViewer();
        courseViewer = new CourseViewer();
        majorViewer = new MajorViewer();

    }

    @Override
    public int select() {
        return 5;
    }

    @Override
    public void execute() {
        System.out.println("""
                To To Load an entity by please select a correspond number:
                1) Student
                2) Course
                3) Major
                """);
        int selectedItem = Input.getInputValue("Enter number");

        switch (selectedItem) {
            case 1 -> studentViewer.loadStudentById();
            case 2 -> courseViewer.loadCourseById();
            case 3 -> majorViewer.loadMajorById();
        }
    }


}
