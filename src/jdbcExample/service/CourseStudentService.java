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
}
