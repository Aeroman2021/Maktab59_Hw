package Hw10.q1.backend.dao;

import Hw10.q1.backend.entities.Patient;
import Hw10.q1.utility.CRUDMethods;
import Hw10.q1.utility.Constant;
import hw8.q4.backend.utilities.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDao implements CRUDMethods<Patient> {

    @Override
    public void save(Patient patient) throws SQLException {
        String INSERT_PATIENT_INFORMATION = "INSERT INTO pharmacy_management_system.patient " +
                "(id, first_name, last_name, sex, age,username,password) " +
                " VALUES(?,?,?,?,?,?,?);";
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT_INFORMATION);

        preparedStatement.setInt(1, patient.getId());
        preparedStatement.setString(2, patient.getFirstName());
        preparedStatement.setString(3, patient.getLastName());
        preparedStatement.setString(4, patient.getSex());
        preparedStatement.setInt(5, patient.getAge());
        preparedStatement.setString(6, patient.getUsername());
        preparedStatement.setString(7, patient.getPassword());


        if (preparedStatement.execute())
            System.out.println("The User added to the database successfully.");
    }

    public void setTotalPriceOfThePrescription(int patientId, double prescriptionIdOne,
                                               double prescriptionIdTwo, double prescriptionIdThree) throws SQLException {
        String UPDATE_PRESCRIPTION_PRICE = "UPDATE pharmacy_management_system.patient " +
                " SET presc_id_1_total_cost=?, presc_id_2_total_cost=?, presc_id_3_total_cost=? " +
                "WHERE id=?;";

        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRESCRIPTION_PRICE);

        preparedStatement.setDouble(1, prescriptionIdOne);
        preparedStatement.setDouble(2, prescriptionIdTwo);
        preparedStatement.setDouble(3, prescriptionIdThree);
        preparedStatement.setInt(4, patientId);

        if (preparedStatement.executeUpdate() == 1)
            System.out.println("The item updated successfully");
        else
            System.out.println("Error while updating the item.");
    }

    public void readTotalCostOfPrescriptionByPatient(int patientId) throws SQLException {
        String PRINT_PRESCRIPTION_PRICE = "SELECT  " +
                " presc_id_1_total_cost, presc_id_2_total_cost, presc_id_3_total_cost " +
                " FROM pharmacy_management_system.patient WHERE id=?;";

        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(PRINT_PRESCRIPTION_PRICE);
        preparedStatement.setInt(1, patientId);

        ResultSet resultSet = preparedStatement.executeQuery();

        StringBuilder result = new StringBuilder();
        System.out.println("                            total cost of the prescription                                 ");
        System.out.println("==========================================================================================");
        result.append("| ");
        result.append(Constant.idFormatter("patient_id"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.priceFormatter("prescription_id_1_total_price"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.priceFormatter("prescription_id_2_total_price"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.priceFormatter("prescription_id_3_total_price"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(" |");
        System.out.println(result.toString());
        while (resultSet.next()) {
            int prescriptionId1TotalCost = resultSet.getInt("presc_id_1_total_cost");
            int prescriptionId2TotalCost = resultSet.getInt("presc_id_2_total_cost");
            int prescriptionId3TotalCost = resultSet.getInt("presc_id_3_total_cost");

            System.out.println(patientId + " | " + prescriptionId1TotalCost + " | " + prescriptionId2TotalCost +
                    " | " + prescriptionId3TotalCost);
        }
    }

    public boolean patientAuthenticator(String username, String password) throws SQLException {
        String PRINT_PRESCRIPTION_PRICE = " SELECT id,last_name,sex FROM pharmacy_management_system.patient " +
                " WHERE username=? AND password=? ";

        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(PRINT_PRESCRIPTION_PRICE);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String lastName = resultSet.getString("last_name");
            String sex = resultSet.getString("sex");
            if (id != null) {
                if (sex.equalsIgnoreCase("male"))
                    System.out.println("welcome M.r " + lastName);
                else
                    System.out.println("welcome M.rs " + lastName);
            }
            return true;
        }
        return false;
    }

    public Patient getPatientByUsernameAndPassword(String username, String password) throws SQLException {
        String PRINT_PRESCRIPTION_PRICE = " SELECT * FROM pharmacy_management_system.patient " +
                " WHERE username=? AND password=? ";

        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(PRINT_PRESCRIPTION_PRICE);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        Patient patient = null;

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String sex = resultSet.getString(4);
            int age = resultSet.getInt(5);
            Integer prescCounter = resultSet.getInt(8);
            Integer prescOneTotalCost = resultSet.getInt(9);
            Integer prescTwoTotalCost = resultSet.getInt(10);
            Integer prescThreeTotalCost = resultSet.getInt(11);

            patient = new Patient(id,firstName,lastName,sex,age,prescCounter,prescOneTotalCost,
                    prescTwoTotalCost,prescThreeTotalCost);
            return patient;
        }
        return null;
    }



    public int getPrescriptionCounter(int patientId) throws SQLException {
        String PRINT_PRESCRIPTION_COUNTER = "SELECT  prescription_counter FROM pharmacy_management_system.patient " +
                "WHERE id=?;";
        Integer prescriptionCounter = null;
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(PRINT_PRESCRIPTION_COUNTER);
        preparedStatement.setInt(1, patientId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
             prescriptionCounter = resultSet.getInt("prescription_counter");
        }
        return prescriptionCounter;
    }

    public void updatePrescriptionCounter(int newPrecsCounter,int patientId) throws SQLException {
        String UPDATE_PRESCRIPTION_COUNTER = " UPDATE pharmacy_management_system.patient " +
                " SET prescription_counter=?  WHERE id=?; ";

        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRESCRIPTION_COUNTER);
        preparedStatement.setInt(1,newPrecsCounter);
        preparedStatement.setInt(2,patientId);
        preparedStatement.executeUpdate();

    }


}
