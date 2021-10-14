package Hw10.q1.backend.manager;

import Hw10.q1.backend.dao.PatientDao;
import Hw10.q1.backend.dao.PrescriptionStoreDao;
import Hw10.q1.backend.entities.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {

    private List<Patient> patientList;
    private PatientDao patientDao;
    private PrescriptionStoreDao prescriptionStoreDao;
    private Store store;
    private  Medicine<Integer> medicine;
    private PrescriptionItems prescriptionItems;


    public Admin() {
        patientDao = new PatientDao();
        prescriptionStoreDao = new PrescriptionStoreDao();
        patientList = new ArrayList<>();
        store = new Store();
        medicine = new Medicine();
    }


    public void Register(String firstName, String lastName, int age, String sex,
                         String username, String password) {

        Patient patient = new Patient(firstName, lastName, sex, age, username, password);
        patientList.add(patient);

        try {
            patientDao.addPatientInformation(patient.getId(), firstName, lastName, age, sex);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void addPrescriptionToPrescriptionStoreDao(int id, Prescription prescription) {
        prescriptionStoreDao.AddPreProcessPrescription();
    }

    public void setPrescriptionToThePatient(int patientId, Prescription prescription) {
        Patient patient = FindPatientById(patientId);
        patient.AddPrescriptionToPrescriptionList(prescription);
    }


    public boolean UserPassValidator(String username, String password) {
        for (Patient patient : patientList) {
            if (patient.getUsername().equals(username) && patient.getPassword().equals(password))
                return true;
        }
        return false;
    }

    public int patientIdFinder() {
        Scanner scanner = new Scanner("System.in");
        System.out.println("Enter your firstname:");
        String name = scanner.next();
        System.out.println("Enter your lastName: ");
        String lastName = scanner.next();

        for (Patient patient : patientList) {
            if (patient.getFirstName().equals(name) && patient.getLastName().equals(lastName))
                return patient.getId();
        }
        return -1;
    }

    private double CheckPriceMedicineAtStore(String medicineName, String medicineForm) {
        if (CheckMedicineAvailabilityAtStore(medicineName, medicineForm)) {
            if (store.getMedicineQuantities().get(medicine.getId()) > 0)
                return (double) medicine.getCost();
        }
        return -1;
    }

    private boolean CheckMedicineAvailabilityAtStore(String medicineName, String medicineForm) {
        for (int i = 0; i < store.getMedicineList().size(); i++) {
            Medicine<Integer> medicine = store.getMedicineList().get(i);
            if (medicine.getName().equals(medicineName) && medicine.getForm().equals(medicineForm))
                return true;
        }
        return false;
    }

    public void updateMedicineQuantity(String medicineName, int medicineForm, int quantity) {
        int medId = findMedicine(medicineName, medicineForm);
        int medQuantity = store.getMedicineQuantities().get(medId);
        medQuantity -= quantity;
        store.getMedicineQuantities().replace(medId, medQuantity);
    }

    public int findMedicine(String medicineName, int medicineForm) {
        for (int i = 0; i < store.getMedicineList().size(); i++) {
            Medicine<Integer> medicine = store.getMedicineList().get(i);
            if (medicine.getName().equals(medicineName) && medicine.getForm().equals(medicineForm))
                return medicine.getId();
        }
        return -1;
    }

    public Patient FindPatientById(int id) {
        for (Patient patient : patientList) {
            if (patient.getId() == id)
                return patient;
        }
        return null;
    }

    public Patient findPatientByUserAndPass(String username, String password) {
        for (Patient patient : patientList) {
            if (patient.getUsername().equals(username) && patient.getPassword().equals(password))
                return patient;
        }
        return null;
    }

    public void updateMedicineQuantityByPrescription(Prescription prescription){
        for (int i = 0; i < prescription.getItemList().size() ; i++) {
            PrescriptionItems<Integer> currentPrescriptionItems = prescription.getItemList().get(i);
            int itemQuantity = currentPrescriptionItems.getQuantity();
            currentPrescriptionItems.getName();
            currentPrescriptionItems.getForm();

            updateMedicineQuantity()

        }






    }






}
