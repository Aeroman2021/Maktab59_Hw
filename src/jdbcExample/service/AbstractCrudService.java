package jdbcExample.service;

import jdbcExample.dao.core.BaseDao;
import jdbcExample.entity.base.BaseEntity;

public class AbstractCrudService<T extends BaseEntity<ID>,ID extends Number> {

    private BaseDao<T, ID> baseDao;


    public void setBaseDao(BaseDao<T, ID> baseDao) {
        this.baseDao = baseDao;
    }

    public BaseDao<T, ID> getBaseDao() {
        return baseDao;
    }

    public void saveOrUpdate(T entity) {
        if (entity.getId() == null) {
            baseDao.save(entity);
        } else {
            baseDao.update(entity.getId(), entity);
        }
    }

    public void save(T entity){
        baseDao.save(entity);
    }

    public void update(ID id,T entity){
        baseDao.update(id,entity);
    }

    public void deleteById(ID id){
        baseDao.delete(id);
    }

    public void loadById(ID id){
        baseDao.loadById(id);
    }

    public void loadAll(){
        baseDao.loadAll();
    }


}
