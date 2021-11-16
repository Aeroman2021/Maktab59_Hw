package jdbcExample.service;

import jdbcExample.dao.CourseStudentDao;
import jdbcExample.entity.CourseStudent;

public class CourseStudentService extends AbstractCrudService<CourseStudent,Integer>{

    public CourseStudentService() {
        setBaseDao(new CourseStudentDao());
    }

    @Override
    public CourseStudentDao getBaseDao() {
        return (CourseStudentDao) super.getBaseDao();
    }

    public void updateStudentCourse(Integer id,Integer courseId){
        getBaseDao().updateCourseForStudent(id,courseId);
    }

    public void deleteCourseByStudentIdAndCourseId(Integer studentId,Integer courseId){
        getBaseDao().deleteByStudentIdAndCourseID(studentId,courseId);
    }

    public boolean courseListOfStudentIsEmpty(Integer studentId){
        return getBaseDao().loadCourseOfTheStudent(studentId).isEmpty();
    }
}
