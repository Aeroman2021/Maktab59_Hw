package jdbcExample.presentation.viewer;

import jdbcExample.entity.Course;
import jdbcExample.entity.CourseStudent;
import jdbcExample.service.CourseService;
import jdbcExample.service.CourseStudentService;
import jdbcExample.utility.Input;

import java.util.List;

public class CourseViewer {
    CourseService courseService;
    CourseStudentService courseStudentService;

    public CourseViewer() {
        courseService = new CourseService();
        courseStudentService = new CourseStudentService();

    }

    public void addCourse() {
        int id = Input.getInputValue("Enter course id");
        String name = Input.getStringInputValue("Enter The course name");
        int unit = Input.getInputValue("Enter the unit of this course");
        Course newCourse = new Course(id, name, unit);
        courseService.save(newCourse);
    }

    public void addCourseForStudent() {
        boolean addMoreCourse = true;
        int studentId = Input.getInputValue("Enter student id");
        while (addMoreCourse) {
            courseService.printCourseListOfTheStudent(studentId);
            int courseId = Input.getInputValue("Enter course id");
            CourseStudent courseStudent = new CourseStudent(studentId, courseId, 0d);
            courseStudentService.save(courseStudent);
            int inputValue = Input.getInputValue("Do you want to add more course? 1)Yes 2)NO");
            if (inputValue == 2)
                addMoreCourse = false;
        }
    }


    public void deleteCourse() {
        int id = Input.getInputValue("Enter course id");
        courseService.deleteById(id);
    }

    public void deleteCourseForStudent() {

        int studentId = Input.getInputValue("Enter student id");
        if (!courseStudentService.courseListOfStudentIsEmpty(studentId)) {
            boolean deleteMoreCourse = true;
            while (deleteMoreCourse) {
                courseService.printCourseListOfTheStudent(studentId);
                int courseId = Input.getInputValue("Enter course id");
                courseStudentService.deleteCourseByStudentIdAndCourseId(studentId, courseId);
                int inputValue = Input.getInputValue("Do you want to delete more course? 1)Yes 2)NO");
                if (inputValue == 2)
                    deleteMoreCourse = false;
            }
        }
        System.out.println("The course list is empty!");

    }

    public Course loadCourseById() {
        int id = Input.getInputValue("Enter the course id");
        return courseService.getBaseDao().loadById(id);
    }


    public List<Course> loadAllCourses() {
        return courseService.getBaseDao().loadAll();
    }

    public void printCourseInformation() {
        courseService.printCourseInformation();
    }


}
