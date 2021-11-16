package jdbcExample.dao.core;

import jdbcExample.entity.base.BaseEntity;

import java.util.List;


public interface BaseDao<T extends BaseEntity<ID>, ID extends Number> {

    void save(T entity);

    void update(ID id, T newEntity);

    void delete(ID id);
    default void deleteByStudentIdAndCourseID(ID id1,ID id2){}

    T loadById(ID id);

    List<T> loadAll();
}
