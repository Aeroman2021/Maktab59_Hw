package Hw10.q1.frontend;

import Hw10.q1.backend.dao.PatientDao;
import Hw10.q1.backend.dao.PrescriptionDao;
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
import java.util.Scanner;

public class UiMethods {

    Scanner input = new Scanner(System.in);
    private Admin admin = new Admin();
    private UtilityMethods utilityMethods = new UtilityMethods(admin);
    private ArrayList<Patient> patientList;
    private UiMenus pharmacyUiManager;
    private Prescription prescription;
    private Patient patient = new Patient();
    private PrescriptionDao prescriptionDao;
    private PatientDao patientDao;

    public UiMethods() {
        pharmacyUiManager = new UiMenus();
        patientList = new ArrayList<>();
        prescription = new Prescription();
        prescriptionDao = new PrescriptionDao();
        patientDao = new PatientDao();
    }

    public void addPrescriptionByPatient(String username, String password) throws SQLException, ManagerException {
        Patient patient = patientDao.getPatientByUsernameAndPassword(username,password);
        if (patientDao.getPrescriptionCounter(patient.getId()) < 4) {
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
            int newPrescCount = patientDao.getPrescriptionCounter(patient.getId());
            patientDao.updatePrescriptionCounter(newPrescCount++,patient.getId());
            System.out.println();
        } else
            throw new ManagerException("You have reached Max capacity of your prescription insert");
    }


    public void printPrescriptionForPatient(String username, String password) throws ManagerException, SQLException {
        if (patientDao.getPatientByUsernameAndPassword(username,password) != null) {
            Patient patient = patientDao.getPatientByUsernameAndPassword(username,password);
            int prescriptionId = Input.getInputValue(">> Enter the prescription id (1, 2 or 3)");
            admin.printPrescriptionForPatient(patient.getId(), prescriptionId);
        } else
            throw new ManagerException("The user does not exist");
    }

    public void updateItemByPatient(String username, String password) throws SQLException {
        Patient patient = patientDao.getPatientByUsernameAndPassword(username,password);
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

    public void deletePrescriptionByPatient(String username, String password) throws SQLException {
        Patient patient = patientDao.getPatientByUsernameAndPassword(username,password);
        int prescriptionId = Input.getInputValue(">> Enter the id of prescription you want to delete:");
        admin.deletePrescriptionByUser(patient.getId(), prescriptionId);
        Prescription prescription = patient.getPrescriptionList().get(prescriptionId);
        prescription.deleteByIndex(prescriptionId);
    }

    public void updateMedicineListInStoreByAdmin() {
        int itemId = Input.getInputValue("Enter the id of the medicine:");
        int quantity = Input.getInputValue("Enter the new quantity of the medicine ");
        double price = Input.getInputValue("Enter the new price for each unit of medicine");
        System.out.println("Enter the exist status");
        boolean isExist = input.nextBoolean();

        admin.updateItemsAtStore(itemId, price, quantity, isExist);
    }

    public void updatePrescriptionListByAdmin() throws SQLException {
        int itemId = Input.getInputValue("Enter item id");
        double itemPrice = Input.getDoubleValue("Enter item's price");
        System.out.println("Does this item exist in store?");
        Boolean doesExist = input.nextBoolean();
        System.out.println("Do you want to confirm this item?");
        Boolean doesConfirmed = input.nextBoolean();

        admin.updatePrescriptionByAdmin(itemId, itemPrice, doesExist, doesConfirmed);
    }

    public void addPatientToTheList(Patient patient) {
        patientList.add(patient);
    }

    public boolean userValidator(String username, String password) throws SQLException {
            return  (patientDao.patientAuthenticator(username,password));
    }

    public void advancedPrescItemUpdateByAdmin() {
        int patientId = Input.getInputValue("Enter patientId");
        int prescriptionID = Input.getInputValue("Enter PrescriptionId");
        admin.advancedItemUpdater(patientId, prescriptionID);
    }

    public void printTotalPriceOfThePrescriptionForAdmin() {
        int patientId = Input.getInputValue("Enter patientId");
        int prescriptionID = Input.getInputValue("Enter PrescriptionId");
        admin.printThePrescriptionTotalPrice(patientId, prescriptionID);
    }

    public void prescriptionItemChecker() throws SQLException {
        int patientId = Input.getInputValue("Enter patient id");
        int prescriptionId = Input.getInputValue("Enter prescription id");
        ArrayList<PrescriptionItems> prescriptionItems =
                prescriptionDao.getAPrescriptionBYPatientID(patientId, prescriptionId);
        admin.isExistAndIsConfirmedUpdater(prescriptionItems, patientId);
    }

    public void setPriceOfPrescription() throws SQLException {
        int patientId = Input.getInputValue("Enter patient id");
        double prescOneTotalPrice = Input.getDoubleValue("Enter the total cost of the first prescription");
        double prescTwoTotalPrice = Input.getDoubleValue("Enter the total cost of the second prescription");
        double prescThreeTotalPrice = Input.getDoubleValue("Enter the total cost of the three prescription");
        patientDao.setTotalPriceOfThePrescription(patientId, prescOneTotalPrice, prescTwoTotalPrice, prescThreeTotalPrice);
    }

    public void printTotalCostOfThePrescriptionForPatient(String username, String password) throws SQLException {
        Patient patient = patientDao.getPatientByUsernameAndPassword(username,password);
        int id = patient.getId();
        patientDao.readTotalCostOfPrescriptionByPatient(id);
    }

}

