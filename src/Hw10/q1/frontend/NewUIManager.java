package Hw10.q1.frontend;

import Hw10.q1.backend.dao.MedicineDao;
import Hw10.q1.backend.dao.PrescriptionDao;
import Hw10.q1.backend.entities.Patient;
import Hw10.q1.backend.manager.Admin;
import Hw10.q1.utility.Input;
import hw8.q4.backend.exceptions.ManagerException;

import java.sql.SQLException;
import java.util.ArrayList;

public class NewUIManager {
    private Admin admin = new Admin();
    private UiMethods uiMethods = new UiMethods();
    private static ArrayList<Integer> idStore = new ArrayList<>();
    private PrescriptionDao prescriptionDao = new PrescriptionDao();
    private MedicineDao medicineDao = new MedicineDao();


    public void showMainMenu() throws ManagerException, SQLException {
        boolean isExit = false;
        while (!isExit) {

            System.out.println("*** PHARMACY MANAGEMENT SYSTEM ***");
            System.out.println("""
                            --- Select your role:  ---
                                1- Patient
                                2- Admin
                                3- exit
                    """);
            int option = Input.getInputValue("");
            switch (option) {
                case 1 -> showPatientPrimaryMenu();
                case 2 -> showAdminPrimaryMenu();
                case 3 -> isExit = true;
                default -> {
                }
            }
        }

    }

    private void showPatientPrimaryMenu() throws ManagerException, SQLException {
        System.out.println(
                """
                        Please select number\040
                        1) register an account
                        2) Login to your account
                        3) Exit to the main menu
                        """);
        int option = Input.getInputValue("");
        switch (option) {
            case 1 -> registerAPatient();
            case 2 -> patientLogin();
            case 3 -> showMainMenu();
            default -> {
            }
        }
    }

    private void registerAPatient() throws SQLException, ManagerException {
        String name = Input.getStringInputValue("Enter your first name");
        String lastName = Input.getStringInputValue("Enter your last name");
        int age = Input.getInputValue("Enter your age");
        String sex = Input.getStringInputValue("Enter your sex");
        String username = Input.getStringInputValue("Enter your username");
        String password = Input.getStringInputValue("Enter your password");
        int patientId = NewUIManager.idGenerator();
        Patient newPatient = new Patient(patientId, name, lastName, sex, age, username, password);
        uiMethods.addPatientToTheList(newPatient);
        admin.Register(newPatient);
    }

    private void patientLogin() throws ManagerException, SQLException {
        String username = Input.getStringInputValue("Enter your username");
        String password = Input.getStringInputValue("Enter your password");
        if (uiMethods.userValidator(username, password))
            showPatientSecondaryMenu(username, password);
        else
            throw new ManagerException("Invalid username or password");
    }

    public void showPatientSecondaryMenu(String username, String password) throws ManagerException, SQLException {
        System.out.println("""
                Please Choose a Number
                1) Insert your prescription
                2) See the total cost of your prescription
                3) Edit an item in prescription
                4) Delete an item in prescription
                5) Delete A prescription
                6) Exit to the previous Menu
                """);

        int option = Input.getInputValue("");
        switch (option) {
            case 1 -> uiMethods.addPrescriptionByPatient(username, password);
            case 2 -> uiMethods.printPrescriptionForPatient(username, password);
            case 3 -> uiMethods.updateItemByPatient(username, password);
            case 4 -> uiMethods.deleteItemByPatient();
            case 5 -> uiMethods.deletePrescriptionByPatient(username, password);
            case 6 -> showPatientPrimaryMenu();
            default -> System.out.println("Please enter a number in range between 1-6");
        }
    }

    private void showAdminPrimaryMenu() throws ManagerException, SQLException {
        System.out.println("""
                Please Choose a Number
                1) Login to your account
                2) Exit to the Main Menu
                """);
        int option = Input.getInputValue("");
        switch (option) {
            case 1 -> adminLogin();
            case 2 -> showMainMenu();
            default -> System.out.println("Please enter a number in range between 1-6");

        }
    }

    private void adminLogin() throws ManagerException, SQLException {
        String username = Input.getStringInputValue("Enter your username");
        String password = Input.getStringInputValue("Enter your password");
        if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin"))
            showAdminSecondaryMenu();
        else
            throw new ManagerException("Invalid username or password");
    }

    private void showAdminSecondaryMenu() throws SQLException, ManagerException {
        System.out.println("""
                        Select a number please:\040
                        1) Print list of prescription
                        2) Print list of medicines in store");
                        3) Update the list of medicines in store");
                        4) Update the list of prescriptions");
                        5) Exit to the previous menu");
                """);

        int option = Input.getInputValue("");
        switch (option) {
            case 1 -> prescriptionDao.getAll();
            case 2 -> medicineDao.getAll();
            case 3 -> uiMethods.updateMedicineListInStoreByAdmin();
            case 4 -> uiMethods.updatePrescriptionListByAdmin();
            case 5 -> showAdminPrimaryMenu();
            default -> System.out.println("Please enter a number in range between 1-6");

        }
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


}