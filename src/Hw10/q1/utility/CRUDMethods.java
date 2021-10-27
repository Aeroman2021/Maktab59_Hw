package Hw10.q1.utility;

import java.sql.SQLException;

public interface CRUDMethods<T> {

//    List<T> getAll() throws SQLException;
    default void getAll() throws SQLException{}
    default void save(T t) throws SQLException{}
    default void update(T t) throws SQLException{}
    default void delete(T t){}
    default void deleteByIndex(Integer index) throws SQLException {}

     default <V,U> void savePrescriptionItem(T t, V v,U u) throws SQLException {}


}
