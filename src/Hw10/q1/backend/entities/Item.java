package Hw10.q1.backend.entities;

public class Item <E,T extends Number,S extends Number>{
    private E name;
    private T form;
    private S cost;

    public Item(E name, T form, S cost) {
        this.name = name;
        this.form = form;
        this.cost = cost;
    }

    public E getName() {
        return name;
    }

    public void setName(E name) {
        this.name = name;
    }

    public T getForm() {
        return form;
    }

    public void setForm(T form) {
        this.form = form;
    }

    public S getCost() {
        return cost;
    }

    public void setCost(S cost) {
        this.cost = cost;
    }
}
