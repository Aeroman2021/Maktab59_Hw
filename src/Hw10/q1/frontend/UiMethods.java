package Hw10.q1.frontend;

import Hw10.q1.backend.entities.Patient;
import Hw10.q1.backend.entities.Prescription;
import Hw10.q1.backend.entities.PrescriptionItems;
import Hw10.q1.backend.manager.Admin;
import Hw10.q1.utility.Input;
import Hw10.q1.utility.UtilityMethods;
import hw8.q4.backend.exceptions.ManagerException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UiMethods {

    Scanner input = new Scanner(System.in);
    private Admin admin;
    private UtilityMethods utilityMethods = new UtilityMethods(admin);
    private ArrayList<Patient> patientList;
    private UiMenus pharmacyUiManager;
    private Prescription prescription;
    private Patient patient = new Patient(null, null, null, null, null, null, null);

    public UiMethods() {
        pharmacyUiManager = new UiMenus();
        admin = new Admin();
        patientList = new ArrayList<>();
        prescription = new Prescription();

    }

    public void addPrescriptionByPatient(String username, String password) throws SQLException, ManagerException {

        Patient patient = findPatientByUsernameAndPassword(username, password);
        if (isAbleToAddPrescription(patient)) {
            HashMap<Integer, PrescriptionItems> itemList = new HashMap<>(10);
            for (int i = 1; i < 11; i++) {
                String itemName = Input.getStringInputValue("Enter the name of your medicine:");
                pharmacyUiManager.printMedicineForm();
                int itemForm = Input.getInputValue("Enter the form of your medicine");
                int quantity = Input.getInputValue("Enter the quantity of your medicine:");
                int itemId = UtilityMethods.itemIdGenerator();
                PrescriptionItems newItem = new PrescriptionItems(itemId, itemName, itemForm,
                        null, null, quantity, false);
                itemList.put(i, newItem);
                admin.addPresItemsToPrescriptionDao(patient.getId(), patient.getPrescriptionIndex(), newItem);
//            admin.updateMedicineQuantityInStore(newItem);
                int choice = Input.getInputValue("""
                        Do you want to insert more medicine?   1) yes, 2)No
                        """);
                if (choice == 2) break;
            }
            Prescription newPrescription = new Prescription(patient.getId(), patient.getPrescriptionIndex(), itemList);
            patient.save(newPrescription);
            System.out.println();
        } else
            throw new ManagerException("You have reached Max capacity of your prescription insert");
    }


    public void printPrescriptionForPatient(String username, String password) throws ManagerException {
        if (findPatientByUsernameAndPassword(username, password) != null) {
            Patient patient = findPatientByUsernameAndPassword(username, password);
            int prescriptionId = Input.getInputValue(">> Enter the prescription id (1, 2 or 3)");
            admin.printPrescriptionForPatient(patient.getId(), prescriptionId);
        } else
            throw new ManagerException("The user does not exist");
    }

    public void updateItemByPatient(String username, String password) {
        Patient patient = findPatientByUsernameAndPassword(username, password);
        assert patient != null;
        int medId = Input.getInputValue("Enter the id of the medicine");
        int medNewForm = Input.getInputValue("Enter the form of the medicine");
        int medNewQuantity = Input.getInputValue("Enter the id of the quantity");

        admin.updateMedicineByUser(medId, medNewForm, medNewQuantity);
    }

    public void deleteItemByPatient() {
        System.out.println("Enter the id of the medicine");
        int medId = input.nextInt();
        admin.deleteMedicineByUser(medId);
    }

    public void deletePrescriptionByPatient(String username, String password) {
        Patient patient = findPatientByUsernameAndPassword(username, password);
        assert patient != null;
        int prescriptionId = Input.getInputValue(">> Enter the id of prescription you want to delete:");
        admin.deletePrescriptionByUser(patient.getId(), prescriptionId);
        Prescription prescription = patient.getPrescriptionList().get(prescriptionId);
        prescription.deleteByIndex(prescriptionId);
    }

    public void updateMedicineListInStoreByAdmin() {
        System.out.println("Enter the name of your medicine:");
        String itemName = input.next();
        System.out.println("Choose form of Medicine:");
        System.out.println("1) Tablet");
        System.out.println("2) liquid oral");
        System.out.println("3) solid oral");
        System.out.println("4) Injection");
        int itemForm = input.nextInt();
        System.out.println("Enter the quantity of your medicine:");
        int quantity = input.nextInt();
        System.out.println("Enter the price of each unit");
        double price = input.nextDouble();
        System.out.println("Enter the exist status");
        boolean isExist = input.nextBoolean();
        admin.updateItemsAtStore(itemName, itemForm, price, quantity, isExist);
    }

    public void updatePrescriptionListByAdmin() throws SQLException {
        System.out.println("Enter patientId");
        int patientId = input.nextInt();
        System.out.println("Enter prescriptionId");
        int prescriptionId = input.nextInt();

        utilityMethods.prescriptionCostAndIsExistUpdater(patientId, prescriptionId);
    }

    private boolean isAbleToAddPrescription(Patient patient) {
        return patient.getPrescriptionIndex() < 4;
    }

    private Patient findPatientByUsernameAndPassword(String username, String password) {
        for (Patient patient : patientList) {
            if (patient.getUsername().equals(username) && patient.getPassword().equals(password))
                return patient;
        }
        return null;
    }

    public void addPatientToTheList(Patient patient) {
        patientList.add(patient);
    }

    public boolean userValidator(String username, String password) {
        for (Patient patient : patientList) {
            if (patient.getUsername().equals(username) && patient.getPassword().equals(password)) {
                if (patient.getSex().equalsIgnoreCase("male")) {
                    System.out.println("Welcome M.r " + patient.getLastName());
                } else {
                    System.out.println("Welcome Mr.s " + patient.getLastName());
                }
                return true;
            }
        }
        return false;
    }

}

