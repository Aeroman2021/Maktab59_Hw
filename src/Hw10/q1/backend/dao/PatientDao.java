package Hw10.q1.backend.dao;

import Hw10.q1.backend.entities.Patient;
import Hw10.q1.utility.CRUDMethods;
import hw8.q4.backend.utilities.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientDao implements CRUDMethods<Patient> {

    @Override
    public void save(Patient patient) throws SQLException {
        String INSERT_PATIENT_INFORMATION = "INSERT INTO pharmacy_management_system.patient " +
                "(id, first_name, last_name, sex, age) " +
                " VALUES(?, ?, ?, ?,?);";
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT_INFORMATION);

        preparedStatement.setInt(1, patient.getId());
        preparedStatement.setString(2, patient.getFirstName());
        preparedStatement.setString(3, patient.getLastName());
        preparedStatement.setString(4, patient.getSex());
        preparedStatement.setInt(5, patient.getAge());

        if (preparedStatement.execute())
            System.out.println("The User added to the database successfully.");
    }



    @Override
    public void getAll() throws SQLException {

    }


    @Override
    public void update(Patient patient) throws SQLException {

    }

    @Override
    public void delete(Patient patient) {

    }
}
