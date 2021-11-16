package jdbcExample.service;

import jdbcExample.dao.StudentDao;
import jdbcExample.entity.Student;

import java.util.List;

public class StudentService extends AbstractCrudService<Student, Integer> {

    public StudentService() {
        setBaseDao(new StudentDao());
    }

    public StudentDao getBaseDao() {
        return (StudentDao) super.getBaseDao();
    }

    public void printStudentInformationById(int id){
        getBaseDao().printStudentInformationById(id);
    }

    public void printAllStudentsInformation(){
        getBaseDao().printStudentInformation();
    }



}
