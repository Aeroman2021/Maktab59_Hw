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

}
