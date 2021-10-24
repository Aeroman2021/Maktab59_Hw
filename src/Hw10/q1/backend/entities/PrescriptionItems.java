package Hw10.q1.backend.entities;

public class PrescriptionItems extends Item {
    private Boolean doesConfirmed;
    private Integer itemId;

    public PrescriptionItems(Integer itemId,String name, Integer form, Double price, Boolean doesExist, Integer quantity,Boolean doesConfirmed) {
        super(name, form, price, doesExist, quantity);
        this.doesConfirmed = doesConfirmed;
        this.itemId = itemId;
    }

    public Boolean getDoesConfirmed() {
        return doesConfirmed;
    }

    public void setDoesConfirmed(Boolean doesConfirmed) {
        this.doesConfirmed = doesConfirmed;
    }

    public Integer getItemId() {
        return itemId;
    }

}
