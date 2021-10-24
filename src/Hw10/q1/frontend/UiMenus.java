package Hw10.q1.frontend;

public class UiMenus {

    public void showTheMainMenu(){
        System.out.println();
        System.out.println(">> Please choose A number from list bellow : ");
        System.out.println();
        System.out.println("1) Enter as user");
        System.out.println("2) Enter as the administer");
        System.out.println("3) exit");
        System.out.println();
    }


    public void showPatientPrimaryMenu() {
        System.out.println(
                """
                        Please choose a number\040
                        1) register an account
                        2) Enter your account
                        """);
    }

    public void showPatientSecondaryMenu() {
        System.out.println("""
                Please Choose a Number
                1) Insert your prescription
                2) See the total cost of your prescription
                3) Edit an item in prescription
                4) Delete an item in prescription
                5) Delete A prescription
                6) Exit
                """);
    }

    public void showAdminMenu() {
        System.out.println("""
                        Select a number please:\040
                        1) Print list of prescription
                        2) Print list of medicines in store");
                        3) Update the list of medicines in store");
                        4) Update the list of prescriptions");
                        5) Exit");
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
