package Hw10.q1.frontend;

public class UiMenus {


    public static void selectRuleMenu() {
        System.out.println("*** WELCOME PHARMACY MANAGEMENT SYSTEM ***");
        System.out.println("""
                        --- Select your role:  ---
                            1- Patient
                            2- Admin
                            3- exit
                """);
    }


    public static void showPatientPrimaryMenu() {
        System.out.println(
                """
                        Please select number\040
                        1) register an account
                        2) Login to your account
                        3) Exit to the main menu
                        """);
    }

    public static void showPatientSecondaryMenu() {
        System.out.println("""
                Please Choose a Number
                1) Insert your prescription
                2) Print the the detail of confirmed prescription
                3) Print the total cost of your prescription
                4) Edit an item in prescription
                5) Delete an item in prescription
                6) Delete A prescription
                7) Exit to the previous Menu
                """);
    }

    public static void showAdminPrimaryMenu(){
        System.out.println("""
                    Please Choose a Number
                    1) Login to your account
                    2) Exit to the Main Menu
                    """);
    }

    public static void showAdminSecondaryMenu() {
        System.out.println("""
                            Select a number please:\040
                            1) Print list of prescription
                            2) Print list of medicines in store
                            3) Update the list of medicines in store
                            4) Update the is_exist and is_confirmed status of medicine
                            5) Update The price of items in prescription
                            6) Advanced Update (insert patientId and prescId)
                            7) Calculate total cost of each prescription (insert patientId and prescId)
                            8) Set total cost of each prescription 
                            9) Exit
                    """);
    }

    public void printMedicineForm() {
        System.out.println("""
                  Choose form of Medicine
                  1) Tablet
                  2) liquid oral
                  3) solid oral
                  4) Injection
                """);
    }


}
