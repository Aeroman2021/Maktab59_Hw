package Hw10.q1.backend.entities;

import Hw10.q1.utility.CRUDMethods;

import java.util.HashMap;


public class Prescription implements CRUDMethods<PrescriptionItems> {

    private Integer patientId;
    private Integer prescriptionId;
    private HashMap<Integer, PrescriptionItems> itemList;
    private Integer itemIndex;

    public Prescription(int patientId, int prescriptionId, HashMap<Integer,PrescriptionItems> itemList) {
        this.patientId = patientId;
        this.prescriptionId = prescriptionId;
        this.itemList=itemList;
    }

    public Prescription() {

    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Override
    public void save(PrescriptionItems newItem) {
        itemList.put(itemIndex++, newItem);
    }

    public HashMap<Integer, PrescriptionItems> getItemList() {
        return itemList;
    }

    @Override
    public void deleteByIndex(Integer index) {
        itemList.remove(index);
    }
}
