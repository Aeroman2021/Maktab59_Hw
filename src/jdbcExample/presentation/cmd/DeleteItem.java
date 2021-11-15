package jdbcExample.presentation.cmd;

import jdbcExample.UI.Input;
import jdbcExample.presentation.viewer.CourseViewer;
import jdbcExample.presentation.viewer.MajorViewer;
import jdbcExample.presentation.viewer.StudentViewer;

public class DeleteItem implements MenuCommand {

    private CourseViewer courseViewer;
    private MajorViewer majorViewer;
    private StudentViewer studentViewer;

    public DeleteItem() {
        studentViewer = new StudentViewer();
        courseViewer = new CourseViewer();
        majorViewer = new MajorViewer();
    }


    @Override
    public int select() {
        return 4;
    }

    @Override
    public void execute() {
        System.out.println("""
                1) Delete a Student from database
                2) Delete Course from  Student's course list.
                """);
        int selectedItem = Input.getInputValue("Enter number");

        switch (selectedItem) {
            case 1 -> studentViewer.deleteStudent();
            case 2 -> courseViewer.deleteCourseForStudent();
        }

    }
}
