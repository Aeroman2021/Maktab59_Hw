package jdbcExample.presentation.viewer;

import jdbcExample.entity.CourseStudent;
import jdbcExample.service.CourseStudentService;
import jdbcExample.utility.Input;

public class CourseStudentViewer {
    private CourseStudentService courseStudentService;

    public CourseStudentViewer() {
        courseStudentService = new CourseStudentService();
    }

    public void addCourseForStudent() {
        boolean addMoreCourse = true;
        while (addMoreCourse) {
            int id = Input.getInputValue("Enter id");
            int studentId = Input.getInputValue("Enter student id");
            int courseId = Input.getInputValue("Enter course id");
            CourseStudent courseStudent = new CourseStudent(id, studentId, courseId, 0d);
            courseStudentService.save(courseStudent);
            int inputValue = Input.getInputValue("Do you want to add more course? 1)Yes 2)NO");
            if (inputValue == 2)
                addMoreCourse = false;
        }
    }

    public void updateCourseListForStudent() {
        boolean updateMoreCourse = true;
        while (updateMoreCourse) {
            int id = Input.getInputValue("Enter id");
            int courseId = Input.getInputValue("Enter course id");
            courseStudentService.updateStudentCourse(id, courseId);
            int inputValue = Input.getInputValue("Do you want to add more course? 1)Yes 2)NO");
            if (inputValue == 2)
                updateMoreCourse = false;

        }
    }


}
