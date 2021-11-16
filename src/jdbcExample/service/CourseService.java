package jdbcExample.service;

import jdbcExample.dao.CourseDao;
import jdbcExample.entity.Course;

public class CourseService extends AbstractCrudService<Course,Integer> {


    public CourseService() {
        setBaseDao(new CourseDao());
    }

    @Override
    public CourseDao getBaseDao() {
        return (CourseDao) super.getBaseDao();
    }

    public void printCourseInformation(){
        getBaseDao().printCourseInformation();
    }

    public void printCourseListOfTheStudent(Integer studentId){
        getBaseDao().loadCourseByStudentId(studentId);
    }
}
