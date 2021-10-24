package Hw10.q1.backend.entities;

public class Item {

    private String name;
    private Integer form;
    private Double price;
    private Boolean doesExist;
    private Integer quantity;

    public Item(String name, Integer form, Double price, Boolean doesExist, Integer quantity) {
        this.name = name;
        this.form = form;
        this.price = price;
        this.doesExist = doesExist;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getForm() {
        return form;
    }

    public void setForm(Integer form) {
        this.form = form;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getDoesExist() {
        return doesExist;
    }

    public void setDoesExist(Boolean doesExist) {
        this.doesExist = doesExist;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", form=" + form +
                ", price=" + price +
                ", doesExist=" + doesExist +
                ", quantity=" + quantity +
                '}';
    }
}
