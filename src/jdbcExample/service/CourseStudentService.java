package jdbcExample.service;

import jdbcExample.dao.CourseStudentDao;
import jdbcExample.dao.core.SecondaryBaseDao;
import jdbcExample.entity.CourseStudent;

public class CourseStudentService extends  SecondaryAbstractCrudService<CourseStudent,Integer,Integer>{

    public CourseStudentService() {
        setSecondaryBaseDao(new CourseStudentDao());
    }

    @Override
    public CourseStudentDao getSecondaryBaseDao() {
        return (CourseStudentDao) super.getSecondaryBaseDao();
    }
}
