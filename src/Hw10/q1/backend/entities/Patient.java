package Hw10.q1.backend.entities;
import Hw10.q1.utility.CRUDMethods;
import java.util.HashMap;

public class Patient implements CRUDMethods<Prescription> {

    private Integer id;
    private String firstName;
    private String lastName;
    private String sex;
    private Integer age;
    private String username;
    private String password;
    private static int prescriptionIndex;
    private Integer prescCounter;
    private Integer prescOneTotalCost;
    private Integer prescTwoTotalCost;
    private Integer prescThreeTotalCost;
    private HashMap<Integer, Prescription> prescriptionList;

    public Patient(Integer id, String firstName, String lastName, String sex, Integer age, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
        this.username = username;
        this.password = password;
        this.id = id;
        prescriptionIndex =1;
        prescriptionList = new HashMap<>();
    }

    public Patient(Integer id, String firstName, String lastName, String sex, Integer age, Integer prescCounter,
                   Integer prescOneTotalCost,Integer prescTwoTotalCost,Integer prescThreeTotalCost) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
        this.id = id;
        prescriptionList = new HashMap<>();
        this.prescCounter = prescCounter;
        this.prescOneTotalCost = prescOneTotalCost;
        this.prescTwoTotalCost = prescTwoTotalCost;
        this.prescThreeTotalCost = prescThreeTotalCost;
    }


    public Patient(){}

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public HashMap<Integer, Prescription> getPrescriptionList() {
        return prescriptionList;
    }

    public int getPrescriptionIndex() {
        return prescriptionIndex;
    }

    @Override
    public void save(Prescription prescription) {
        prescriptionList.put(prescriptionIndex++, prescription);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }

    public void printPrescription(){}

}
