package jdbcExample.dao.core;

import jdbcExample.entity.base.SecondaryBaseEntity;

import java.util.List;

public interface SecondaryBaseDao<T extends SecondaryBaseEntity<IDone, IDTwo>, IDone extends Number, IDTwo extends Number> {
    void save(T entity);

    void update(IDone idOne, IDTwo idTwo, T newEntity);

    void delete(IDone idOne, IDTwo idTwo);

    T loadById(IDone idOne, IDTwo idTwo);

    List<T> loadAll();

}
