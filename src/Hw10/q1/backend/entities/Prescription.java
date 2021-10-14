package Hw10.q1.backend.entities;

import java.util.HashMap;

public class Prescription {

    private int Id = 100;
    private HashMap<Integer, PrescriptionItems> itemList;
    private int itemIndex;


    public Prescription() {
        itemList = new HashMap<>();
        this.Id += 2;
    }

    public void addItemsToPrescription(PrescriptionItems item) {
        itemList.put(itemIndex++, item);
    }

    public HashMap<Integer, PrescriptionItems> getItemList() {
        return itemList;
    }

    public void removePrescriptionItems(int key) {
        itemList.remove(key);
    }

    public void updatePrescriptionItems(int key) {
        PrescriptionItems<Integer> ps = itemList.get(key);

        ps.setName();
        ps.setForm();
        ps.setQuantity();

    }
}
