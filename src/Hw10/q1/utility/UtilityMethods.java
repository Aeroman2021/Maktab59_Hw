package Hw10.q1.utility;

import Hw10.q1.backend.dao.PrescriptionDao;
import Hw10.q1.backend.entities.*;
import Hw10.q1.backend.manager.Admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UtilityMethods {
    Scanner input = new Scanner(System.in);

    private Admin admin;
    private Medicine medicine;
    private Store store;
    private Prescription prescription;
    private PrescriptionItems prescriptionItems;
    private PrescriptionDao prescriptionDao;
    private static ArrayList<Integer> idStore;
    private static ArrayList<Integer> itemIdStore;
    private  List<Patient> patientList;

    public UtilityMethods(Admin admin) {
        medicine = new Medicine (null, null, null, null, null, null);
        prescription = new Prescription();
        prescriptionItems = new PrescriptionItems(null,null, null, null, null, null,false);
        prescriptionDao = new PrescriptionDao();
        idStore = new ArrayList<>();
        patientList = new ArrayList<>();
        this.admin = admin;
        itemIdStore = new ArrayList<>();
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public boolean UserPassValidator(String username, String pass) {
        List<Patient> patientList = admin.getPatientList();
        for (Patient patient : patientList) {
            if (patient.getUsername().equals(username) && patient.getPassword().equals(pass)) {
                System.out.println("Welcome " + patient.getLastName());
                return true;
            }
        }
        return false;
    }


    public Patient FindPatientById(int id) {
        List<Patient> patientList = admin.getPatientList();
        for (Patient patient : patientList) {
            if (patient.getId() == id)
                return patient;
        }
        return null;
    }



    public boolean adminValidator(String username, String password) {
        return (username.equals("admin") && password.equals("admin"));
    }

    public Medicine findMedicineByNameAndForm(String medicineName, int medicineForm) {
        for (int i = 0; i < store.getMedicineList().size(); i++) {
            Medicine medicine = store.getMedicineList().get(i);
            if (medicine.getName().equalsIgnoreCase(medicineName) && medicine.getForm() == medicineForm)
                return medicine;
        }
        return null;
    }

//    public void prescriptionCostAndIsExistUpdater(int patientId, int prescriptionId) throws SQLException {
//
//        Patient patient = FindPatientById(patientId);
//        HashMap<Integer, Prescription> prescriptionList = patient.getPrescriptionList();
//        Prescription prescription = prescriptionList.get(prescriptionId);
//        HashMap<Integer, PrescriptionItems> itemList = prescription.getItemList();
//
//        for (int i = 0; i < itemList.size(); i++) {
//            PrescriptionItems prescriptionItems = itemList.get(i);
//            String name = prescriptionItems.getName();
//            Integer form = prescriptionItems.getForm();
//            Medicine foundMedicine = findMedicineByNameAndForm(name, form);
//            Double price = foundMedicine.getPrice();
//            Boolean doesExist = foundMedicine.getDoesExist();
//
//            prescriptionDao.updatePrescriptionItemsByAdmin(prescriptionId, patientId, price, doesExist);
//            prescriptionItems.setDoesExist(doesExist);
//            prescriptionItems.setPrice(price);
//        }
//    }

    public static int idGenerator() {
        int upperBound = 999;
        int lowerBound = 100;
        int id = (int) (Math.random() * (upperBound - lowerBound)) + lowerBound;
        if (!idStore.contains(id)) {
            idStore.add(id);
            return id;
        } else idGenerator();
        return id;
    }

    public static int itemIdGenerator() {
        int upperBound = 10;
        int lowerBound = 100;
        int id = (int) (Math.random() * (upperBound - lowerBound)) + lowerBound;
        if (!itemIdStore.contains(id)) {
            itemIdStore.add(id);
            return id;
        } else itemIdGenerator();
        return id;
    }



    public  Patient registerTheUser(){
        System.out.println("Enter firstname");
        String firstName = input.next();
        System.out.println("Enter lastname");
        String lastName = input.next();
        System.out.println("Enter your age");
        int age = input.nextInt();
        System.out.println("Enter your sex ");
        String sex = input.next();
        System.out.println("Enter username");
        String username = input.next();
        System.out.println("Enter password");
        String password = input.next();
        int patientId = UtilityMethods.idGenerator();

        Patient newPatient = new Patient(patientId,firstName,lastName,sex,age,username,password);
        patientList.add(newPatient);

        return newPatient;

    }

}
