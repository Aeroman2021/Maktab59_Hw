package jdbcExample.presentation.viewer;

import jdbcExample.entity.Course;
import jdbcExample.service.CourseService;
import jdbcExample.utility.Input;

import java.util.List;

public class CourseViewer {
    CourseService courseService;

    public CourseViewer() {
        courseService = new CourseService();
    }

    public void addCourse(){
        int id = Input.getInputValue("Enter course id");
        String name = Input.getStringInputValue("Enter The course name");
        int unit = Input.getInputValue("Enter the unit of this course");
        Course newCourse = new Course(id,name,unit);
        courseService.save(newCourse);

    }

    public void deleteCourse(){
        int id = Input.getInputValue("Enter course id");
        courseService.deleteById(id);
    }

    public void loadCourseById(){
        int id = Input.getInputValue("Enter the course id");
        courseService.loadById(id);
    }


    public void loadAllCourses(){
        List<Course> courses = courseService.getBaseDao().loadAll();
        for (Course course : courses)
            System.out.println(course);
    }

}
