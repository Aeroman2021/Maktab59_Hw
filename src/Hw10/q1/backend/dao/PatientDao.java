package Hw10.q1.backend.dao;

import Hw10.q1.backend.manager.Admin;
import hw8.q4.backend.utilities.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PatientDao {
    private Admin admin;
    private String INSERT_PATIENT_INFORMATION = "INSERT INTO patient" +
            "(id, first_name, last_name, prescription_id)" +
            " VALUES(?, ?, ?, ?);";

    public void addPatientInformation(int id, String firstName, String lastName, int age, String sex) {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_A_PLAYER);

        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.setDouble(4, age);
        preparedStatement.setString(5, String.valueOf(position));
        preparedStatement.setInt(6, teamId);
        preparedStatement.setDouble(7, contract);
        preparedStatement.setDouble(8, salary);

        if (preparedStatement.execute())
            System.out.println("The player added to the team successfully.");

    }
}
