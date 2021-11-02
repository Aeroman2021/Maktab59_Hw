package Hw10.q1.frontend;

import Hw10.q1.backend.Exception.ManagerException;
import Hw10.q1.backend.dao.MedicineDao;
import Hw10.q1.backend.dao.PrescriptionDao;
import Hw10.q1.backend.entities.Patient;
import Hw10.q1.backend.manager.Admin;
import Hw10.q1.utility.Input;
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
            UiMenus.selectRuleMenu();
            int option = Input.getInputValue("");
            switch (option) {
                case 1 -> showPatientPrimaryMenu();
                case 2 -> showAdminPrimaryMenu();
                case 3 -> isExit = true;
                default -> System.out.println("Please enter a number in range between 1-3");
            }
        }
    }

    private void showPatientPrimaryMenu() throws ManagerException, SQLException {
        boolean isExit = false;
        while (!isExit) {
            UiMenus.showPatientPrimaryMenu();
            int option = Input.getInputValue("");
            switch (option) {
                case 1 -> registerAPatient();
                case 2 -> patientLogin();
                case 3 -> isExit = true;
                default -> System.out.println("Please enter a number in range between 1-3");
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
        boolean isExit = false;
        while (!isExit) {
            UiMenus.showPatientSecondaryMenu();
            int option = Input.getInputValue("");
            switch (option) {
                case 1 -> uiMethods.addPrescriptionByPatient(username, password);
                case 2 -> uiMethods.printPrescriptionForPatient(username, password);
                case 3 -> uiMethods.printTotalCostOfThePrescriptionForPatient(username,password);
                case 4 -> uiMethods.updateItemByPatient(username, password);
                case 5 -> uiMethods.deleteItemByPatient();
                case 6 -> uiMethods.deletePrescriptionByPatient(username, password);
                case 7 -> isExit = true;
                default -> System.out.println("Please enter a number in range between 1-6");
            }
        }
    }

    private void showAdminPrimaryMenu() throws ManagerException, SQLException {
        boolean isExit = false;
        while (!isExit) {
            UiMenus.showAdminPrimaryMenu();
            int option = Input.getInputValue("");
            switch (option) {
                case 1 -> adminLogin();
                case 2 -> isExit = true;
                default -> System.out.println("Please enter a number in range between 1-2");
            }
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

    private void showAdminSecondaryMenu() throws SQLException {
        boolean isExit = false;
        while (!isExit) {
            UiMenus.showAdminSecondaryMenu();
            int option = Input.getInputValue("");
            switch (option) {
                case 1 -> prescriptionDao.getAll();
                case 2 -> medicineDao.getAll();
                case 3 -> uiMethods.updateMedicineListInStoreByAdmin();
                case 4 -> uiMethods.prescriptionItemChecker();
                case 5 -> uiMethods.updatePrescriptionListByAdmin();
                case 6 -> uiMethods.advancedPrescItemUpdateByAdmin();
                case 7 -> uiMethods.printTotalPriceOfThePrescriptionForAdmin();
                case 8 -> uiMethods.setPriceOfPrescription();
                case 9 -> isExit = true;
                default -> System.out.println("Please enter a number in range between 1-8");
            }
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