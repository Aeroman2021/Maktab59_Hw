package jdbcExample.service;

import jdbcExample.dao.MajorDao;
import jdbcExample.dao.core.BaseDao;
import jdbcExample.entity.Major;

public class MajorService extends AbstractCrudService<Major,Integer>{

    public MajorService() {
        setBaseDao(new MajorDao());
    }

    @Override
    public MajorDao getBaseDao() {
        return (MajorDao )super.getBaseDao();
    }

    public void printCourseInformation(){
        getBaseDao().printCourseInformation();
    }
}
