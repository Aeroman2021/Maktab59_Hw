package Hw10.q1.backend.entities;


public class Medicine<T extends Number> extends Item<String,Integer,Double> {
    private T id;

    public Medicine(T id,String name, Integer form, Double cost) {
        super(name, form, cost);
        this.id = id;
    }


    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}

