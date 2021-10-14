package Hw10.q1.backend.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Patient{

    private int id = 10;
    private String firstName;
    private String lastName;
    private String sex;
    private int age;
    private String username;
    private String password;
    private int prescriptionIndex;
    private HashMap<Integer, Prescription> prescriptionList;

    public Patient(String firstName, String lastName, String sex, int age, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
        this.username = username;
        this.password = password;
        this.prescriptionList = new HashMap<>(3);
        this.id += 1;
        this.prescriptionIndex=0;
    }

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

    public void AddPrescriptionToPrescriptionList(Prescription prescription) {
        prescriptionList.put(prescriptionIndex++,prescription);
    }




}
