package jdbcExample.presentation.viewer;

import jdbcExample.entity.Course;
import jdbcExample.entity.CourseStudent;
import jdbcExample.service.CourseService;
import jdbcExample.service.CourseStudentService;
import jdbcExample.service.StudentService;
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



    public void deleteCourse() {
        int id = Input.getInputValue("Enter course id");
        courseService.deleteById(id);
    }

    public void deleteCourseForStudent() {
        boolean deleteMoreCourse = true;
        while (deleteMoreCourse) {
            int id = Input.getInputValue("Enter id");
            for (Course course : courseService.getBaseDao().loadAll()) {
                if(course.getId() == id)
                    courseStudentService.deleteById(id);
            }
            int inputValue = Input.getInputValue("Do you want to add more course? 1)Yes 2)NO");
            if (inputValue == 2)
                deleteMoreCourse = false;
        }
    }

    public void loadCourseById() {
        int id = Input.getInputValue("Enter the course id");
        courseService.loadById(id);
    }


    public void loadAllCourses() {
        List<Course> courses = courseService.getBaseDao().loadAll();
        for (Course course : courses)
            System.out.println(course);
    }

}
