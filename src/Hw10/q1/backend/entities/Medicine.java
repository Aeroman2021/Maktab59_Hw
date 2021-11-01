package Hw10.q1.backend.entities;


public class Medicine extends Item {
    private Integer id;

    public Medicine(Integer id, String name, Integer form, Double price, Integer quantity, Boolean doesExist) {
        super(name, form, price, doesExist, quantity);
        this.id = id;
    }

    public Medicine( ) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                '}';
    }
}






