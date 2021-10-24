package Hw10.q1.frontend;


import Hw10.q1.backend.dao.MedicineDao;
import Hw10.q1.backend.dao.PrescriptionDao;
import Hw10.q1.backend.entities.*;
import Hw10.q1.backend.manager.Admin;
import Hw10.q1.utility.UtilityMethods;
import hw8.q4.backend.exceptions.ManagerException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UIManager {

    private Admin admin;
    private Scanner input;
    private static int select;
    private Patient patient;
    private PrescriptionItems newItem;
    private Prescription prescription;
    private PrescriptionDao prescriptionDao;
    private MedicineDao medicineDao;
    private UtilityMethods utilityMethods;
    private List<Patient> patientList;
    private Store store;
    private UiMenus pharmacyUiManager;
    private UiMethods uiMethods;


    public UIManager() throws SQLException {
        admin = new Admin();
        input = new Scanner(System.in);
        prescriptionDao = new PrescriptionDao();
        medicineDao = new MedicineDao();
        utilityMethods = new UtilityMethods(admin);
        patientList = new ArrayList<>();
        pharmacyUiManager = new UiMenus();
//        uiMethods = new UiMethods(utilityMethods, patient, prescription);
    }


    public void Run() throws SQLException, ManagerException {
        System.out.println("====================================================================================");
        System.out.println("===========================WELCOME TO PHARMACY APPLICATION==========================");
        System.out.println("====================================================================================");

        while (true) {
            pharmacyUiManager.showTheMainMenu();

            select = input.nextInt();

            switch (select) {

                case 1 -> {
                    pharmacyUiManager.showPatientPrimaryMenu();
                    int choice = input.nextInt();
                    switch (choice) {

                        case 1 -> {
                            Patient patient = utilityMethods.registerTheUser();
                            admin.Register(patient);
                        }

                        case 2 -> {
                            System.out.println("Enter username");
                            String username = input.next();
                            System.out.println("Enter password");
                            String password = input.next();

                            if (utilityMethods.UserPassValidator(username, password)) {
                                pharmacyUiManager.showPatientSecondaryMenu();
                                choice = input.nextInt();
                                switch (choice) {
//                                    case 1 -> uiMethods.insertPrescriptionByPatient(username, password);
                                    case 2 -> uiMethods.printPrescriptionForPatient(username, password);
                                    case 3 -> uiMethods.updateItemByPatient(username, password);
                                    case 4 -> uiMethods.deleteItemByPatient();
                                    case 5 -> uiMethods.deletePrescriptionByPatient(username, password);
                                    case 6 -> System.exit(0);
                                }
                            } else
                                throw new ManagerException("InvalidUserNameOrPassword");
                        }
                    }
                }

                case 2 -> {
                    System.out.println("Enter your username");
                    String username = input.next();
                    System.out.println("Enter your password");
                    String password = input.next();

                    if (utilityMethods.adminValidator(username, password)) {
                        pharmacyUiManager.showAdminMenu();
                        int choice = input.nextInt();

                        while (true) {
                            switch (choice) {
                                case 1 -> prescriptionDao.getAll();
                                case 2 -> medicineDao.getAll();
                                case 3 -> uiMethods.updateMedicineListInStoreByAdmin();
                                case 4 -> uiMethods.updatePrescriptionListByAdmin();
                                case 5 -> System.exit(0);
                            }
                        }
                    } else
                        throw new ManagerException("Invalid username and password");
                }

                case 3 -> {
                    System.out.println("Exiting the application...");
                    System.exit(0);

                }
            }
        }
    }
}