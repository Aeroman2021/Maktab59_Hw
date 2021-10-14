package Hw10.q1.frontend;


import Hw10.q1.backend.entities.Prescription;
import Hw10.q1.backend.entities.PrescriptionItems;
import Hw10.q1.backend.entities.PrescriptionStore;
import Hw10.q1.backend.manager.Admin;
import hw8.q4.backend.exceptions.ManagerException;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UIManager<Patient> {

    private Admin admin;
    private Scanner input;
    private static int select;
    private Patient patient;
    private PrescriptionItems<Integer> newItem;
    private Prescription prescription;
    private PrescriptionStore prescriptionStore;


    public UIManager() throws SQLException {
        admin = new Admin();
        input = new Scanner(System.in);
        prescriptionStore = new PrescriptionStore();
    }

    public void showMenu() {
        System.out.println();
        System.out.println(">> Please choose A number from list bellow : ");
        System.out.println();
        System.out.println("1) Enter as user");
        System.out.println("2) Enter as the administer");
        System.out.println("3) exit");
        System.out.println();

    }

    public void Run() throws SQLException, ManagerException {
        System.out.println("====================================================================================");
        System.out.println("===========================WELCOME TO PHARMACY APPLICATION==========================");
        System.out.println("====================================================================================");

        while (true) {


            showMenu();
            try {
                select = input.nextInt();
            } catch (InputMismatchException exception) {
                exception.printStackTrace();
                System.out.println(exception.getMessage());
            }

            switch (select) {

                case 1 -> {
                    System.out.println("Please choose a number");
                    System.out.println("1) Register");
                    System.out.println("2) Enter your account");
                    int choice = input.nextInt();

                    switch (choice) {

                        case 1 -> {
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


                            admin.Register(firstName, lastName, age, sex, username, password);
                        }

                        case 2 -> {
                            System.out.println("Enter your username");
                            String username = input.next();
                            System.out.println("Enter your password");
                            String password = input.next();

                            if (!admin.UserPassValidator(username, password)) {
                                throw new ManagerException("InvalidUserNameOrPassword");
                            } else {
                                System.out.println("Welcome" + admin.findPatientByUserAndPass(username, password).getFirstName());
                                System.out.println();
                                System.out.println("Please Choose Number");
                                System.out.println("1) Insert your prescription");
                                System.out.println("2) See the total cost of your prescription");
                                System.out.println("3) Edit A prescription");
                                System.out.println("4) Delete A prescription");
                                System.out.println();
                                choice = input.nextInt();

                                switch (choice) {
                                    case 1 -> {
                                        int patientId = admin.patientIdFinder();
                                        prescription = new Prescription();

                                        int counter = 0;
                                        boolean stopProcess = false;
                                        while (counter < 10 || stopProcess) {
                                            System.out.println("Enter the name of your medicine:");
                                            String medicine = input.next();
                                            System.out.println("Choose form of Medicine:");
                                            System.out.println("1) Tablet");
                                            System.out.println("2) liquid oral");
                                            System.out.println("3) solid oral");
                                            System.out.println("4) Injection");
                                            int medicineForm = input.nextInt();
                                            System.out.println("Enter the quantity of your medicine:");
                                            int medicineQuantity = input.nextInt();
                                            newItem = new PrescriptionItems(medicine, medicineQuantity, medicineForm, null);
                                            prescription.addItemsToPrescription(newItem);

                                            System.out.println("Do you want to insert more medicine?");
                                            System.out.println("1) yes, 2)No");
                                            choice = input.nextInt();
                                            if (choice == 2) {
                                                stopProcess = true;
                                            } else
                                                counter++;
                                        }

                                        admin.setPrescriptionToThePatient(patientId, prescription);
                                        prescriptionStore.addPrescriptionToStore(patientId, prescription);
                                        admin.addPrescriptionToPrescriptionStoreDao(patientId, prescription);
                                    }

                                    case 2 -> {


                                    }

                                    case 3 -> {
                                        System.out.println(">> Enter your id");
                                        int id = input.nextInt();
                                        System.out.println(">> Enter the number of prescription you want to edit:");
                                        int prescriptionNumber = input.nextInt();
                                        Prescription prescription = admin.FindPatientById(id).getPrescriptionList().get(prescriptionNumber);
                                        System.out.println("Enter the id of the item you want to edit");
                                        int itemNumber = input.nextInt();
                                        System.out.println("select which one you want to edit");
                                        System.out.println("1)item | 2)form | 3)quantity");

                                        prescription.updatePrescriptionItems(itemNumber,);


                                    }

                                    case 4 -> {
                                        System.out.println(">> Enter your id");
                                        int id = input.nextInt();
                                        System.out.println(">> Enter the number of prescription you want to delete:");
                                        int inputNumber = input.nextInt();
                                        Prescription prescription = admin.FindPatientById(id).getPrescriptionList().get(inputNumber);
                                        prescription.removePrescriptionItems(inputNumber);
                                    }


                                }

                            }

                        }


                    }


                }


                case 2 -> {

                }

                case 3 -> {
                    System.out.println("Exiting the application...");
                    System.exit(0);

                }
            }
        }

    }
}
