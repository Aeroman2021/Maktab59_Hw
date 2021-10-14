package Hw10.q1.backend.entities;

import java.util.HashMap;

public class PrescriptionStore {

    private HashMap<Integer,Prescription> patientsPrescriptions;

    public PrescriptionStore() {
        patientsPrescriptions = new HashMap<>();
    }

    public void addPrescriptionToStore(int id,Prescription prescription){
        patientsPrescriptions.put(id,prescription);
    }


}
