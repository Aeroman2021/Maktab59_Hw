package jdbcExample.service;

import jdbcExample.dao.StudentDao;
import jdbcExample.entity.Student;

public class StudentService extends AbstractCrudService<Student, Integer> {

    public StudentService() {
        setBaseDao(new StudentDao());
    }

    public StudentDao getBaseDao() {
        return (StudentDao) super.getBaseDao();
    }

}
