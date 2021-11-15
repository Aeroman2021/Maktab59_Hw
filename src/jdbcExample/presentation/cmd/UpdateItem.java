package jdbcExample.presentation.cmd;

import jdbcExample.UI.Input;
import jdbcExample.presentation.viewer.CourseStudentViewer;
import jdbcExample.presentation.viewer.CourseViewer;
import jdbcExample.presentation.viewer.MajorViewer;
import jdbcExample.presentation.viewer.StudentViewer;


public class UpdateItem implements MenuCommand {
    private CourseViewer courseViewer;
    private MajorViewer majorViewer;
    private StudentViewer studentViewer;
    private CourseStudentViewer courseStudentViewer;

    public UpdateItem() {
        studentViewer = new StudentViewer();
        courseViewer = new CourseViewer();
        majorViewer = new MajorViewer();
        courseStudentViewer = new CourseStudentViewer();

    }

    @Override
    public int select() {
        return 3;
    }

    @Override
    public void execute() {
        System.out.println("""
                1) Update Student's information
                2) Update Student's Course
                """);
        int selectedItem = Input.getInputValue("Enter number");

        switch (selectedItem) {
            case 1 -> studentViewer.updateStudent();
            case 2 -> courseStudentViewer.updateCourseListForStudent();
        }
    }


}
