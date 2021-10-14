package Hw10.q1.backend.entities;

import java.util.HashMap;
import java.util.List;

public interface Methods<E,T> {
    void addToTheList(HashMap<E,T> hashMap, T item);
    default void updateList(E item){};
    default void deleteItem(T id){};
}
