package Hw10.q1.backend.entities;

public class PrescriptionItems<T extends Number> extends Item<String,Integer,Double>  {
    private T quantity;

    public PrescriptionItems(String name, Integer form,T quantity, Double cost) {
        super(name, form, cost);
        this.quantity = quantity;
    }


    public T getQuantity() {
        return quantity;
    }

    public void setQuantity(T quantity) {
        this.quantity = quantity;
    }
}
