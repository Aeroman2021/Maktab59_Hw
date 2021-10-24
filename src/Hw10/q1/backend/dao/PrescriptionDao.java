package Hw10.q1.backend.dao;

import Hw10.q1.backend.entities.PrescriptionItems;
import Hw10.q1.utility.Constant;
import Hw10.q1.utility.CRUDMethods;
import hw8.q4.backend.utilities.DbConnection;

import java.sql.*;

public class PrescriptionDao implements CRUDMethods<PrescriptionItems> {

    @Override
    public <V, U> void savePrescriptionItem(PrescriptionItems prescriptionItems, V patientId, U prescriptionId) throws SQLException {
        String INSERT_PATIENT_INFORMATION = "INSERT INTO pharmacy_management_system.prescription" +
                "        (item_id,prescription_id, patient_id, item,form, quantity, price, is_exist,is_confirmed)" +
                " VALUES(?,?, ?, ?, ?,?, NULL, 0,0);";
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT_INFORMATION);

        preparedStatement.setInt(1, prescriptionItems.getItemId());
        preparedStatement.setInt(2, (Integer) prescriptionId);
        preparedStatement.setInt(3, (Integer) patientId);
        preparedStatement.setString(4, prescriptionItems.getName());
        preparedStatement.setInt(5, prescriptionItems.getForm());
        preparedStatement.setInt(6, prescriptionItems.getQuantity());

        if (preparedStatement.execute())
            System.out.println("The patient added to the database successfully.");
    }


    public void updatePrescriptionItemsByAdmin(int prescriptionId, int patientId, double itemPrice, boolean isExist) throws SQLException {
        String UPDATE_PRESCRIPTION_ITEMS = "UPDATE  pharmacy_management_system.prescription" +
                "SET price=?, is_exist=? " +
                "WHERE patient_id=? AND id=?;";

        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRESCRIPTION_ITEMS);
        preparedStatement.setDouble(1, itemPrice);
        preparedStatement.setBoolean(2, isExist);
        preparedStatement.setInt(3, patientId);
        preparedStatement.setInt(4, prescriptionId);
    }

    public void updatePrescriptionItemsByPatient(int medId, int medForm, int medQuantity) throws SQLException {
        String UPDATE_PRESCRIPTION_ITEMS = "UPDATE  pharmacy_management_system.prescription" +
                "SET form=?, quantity=? WHERE item_id=? ";

        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRESCRIPTION_ITEMS);
        preparedStatement.setInt(1, medForm);
        preparedStatement.setInt(2, medQuantity);
        preparedStatement.setInt(3, medId);

    }

    public void showListOfPrescriptionForPatient(int patientId, int prescriptionId) throws SQLException {
        String PRINT_LIST_OF_PRESCRIPTION = "SELECT item , form, (quantity * price * is_exist) as 'price', is_confirmed " +
                " FROM pharmacy_management_system.prescription WHERE prescription_id=? AND patient_id=? ";

        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(PRINT_LIST_OF_PRESCRIPTION);
        preparedStatement.setInt(1, prescriptionId);
        preparedStatement.setInt(2, patientId);
        ResultSet rs = preparedStatement.executeQuery();

        StringBuilder result = new StringBuilder();
        System.out.println("==========================================================================================");
        System.out.println("============================List of all submitted prescription============================");
        System.out.println("==========================================================================================");
        result.append("| ");
        result.append(Constant.formatter("item"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formatter("form"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formatter("price"));
        result.append("|");

        System.out.println("__________________________________________________________________________________________");

        while (rs.next()) {
            String item = rs.getString("item");
            int form = rs.getInt("form");
            double price = rs.getDouble("price");

            System.out.println(item + " | " + form + " | " + price);
        }
        System.out.println("======================================================================");

    }

    @Override
    public void getAll() throws SQLException {
        String PRINT_LIST_OF_PRESCRIPTION = "SELECT item_id,prescription_id, patient_id,item,form,quantity " +
                "FROM pharmacy_management_system.prescription";

        Connection connection = DbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(PRINT_LIST_OF_PRESCRIPTION);

        StringBuilder result = new StringBuilder();
        System.out.println("==========================================================================================");
        System.out.println("============================List of all submitted prescription============================");
        System.out.println("==========================================================================================");
        result.append("| ");
        result.append(Constant.formatter("item_id"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formatter("prescription_id"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formatter("patient_id"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formatter("item"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formatter("form"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formatter("quantity"));
        result.append("|");

        System.out.println("__________________________________________________________________________________________");

        while (rs.next()) {
            int itemId = rs.getInt(1);
            int patientId = rs.getInt(2);
            int prescriptionId = rs.getInt(3);
            String item = rs.getString(4);
            int form = rs.getInt(5);
            int quantity = rs.getInt(6);

            System.out.println(itemId + " | " + patientId + " | " + prescriptionId + " | " + item +
                    " | " + form + " | " + quantity);
        }
        System.out.println("======================================================================");
    }


    @Override
    public void deleteByIndex(Integer index) throws SQLException {
        String DELETE_THE_MEDICINE_BY_PATIENT = "DELETE FROM pharmacy_management_system.prescription WHERE item_id=? ";
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_THE_MEDICINE_BY_PATIENT);
        preparedStatement.setInt(1, index);
        preparedStatement.executeUpdate();

    }

    public void deletePrescByUser(int patientId, int prescriptionId) throws SQLException {
        String DELETE_THE_PRESCRIPTION_BY_PATIENT = "DELETE FROM pharmacy_management_system.prescription " +
                " WHERE patient_id=? And prescription_id=?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_THE_PRESCRIPTION_BY_PATIENT);
        preparedStatement.setInt(1, patientId);
        preparedStatement.setInt(2, prescriptionId);

    }


}
