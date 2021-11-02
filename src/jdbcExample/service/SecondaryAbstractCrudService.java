package jdbcExample.service;

import jdbcExample.dao.core.SecondaryBaseDao;
import jdbcExample.entity.base.SecondaryBaseEntity;

public class SecondaryAbstractCrudService<T extends SecondaryBaseEntity<IDOne, IDTwo>,
        IDOne extends Number, IDTwo extends Number> {

    private SecondaryBaseDao<T, IDOne, IDTwo> secondaryBaseDao;

    public SecondaryBaseDao<T, IDOne, IDTwo> getSecondaryBaseDao() {
        return secondaryBaseDao;
    }

    public void setSecondaryBaseDao(SecondaryBaseDao<T, IDOne, IDTwo> secondaryBaseDao) {
        this.secondaryBaseDao = secondaryBaseDao;
    }

    public void saveOrUpdate(T entity) {
        if (entity.getIdOne() == null && entity.getIdTwo() == null) {
            secondaryBaseDao.save(entity);
        } else {
            secondaryBaseDao.update(entity.getIdOne(), entity.getIdTwo(), entity);
        }
    }

    public void deleteById(IDOne idOne, IDTwo idTwo) {
        secondaryBaseDao.delete(idOne, idTwo);
    }

    public void loadById(IDOne idOne, IDTwo idTwo) {
        secondaryBaseDao.loadById(idOne, idTwo);
    }

    public void loadAll() {
        secondaryBaseDao.loadAll();
    }


}
