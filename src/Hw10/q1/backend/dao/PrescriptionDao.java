package Hw10.q1.backend.dao;

import Hw10.q1.backend.entities.Prescription;
import Hw10.q1.backend.entities.PrescriptionItems;
import Hw10.q1.utility.Constant;
import Hw10.q1.utility.CRUDMethods;
import hw8.q4.backend.utilities.DbConnection;

import java.sql.*;
import java.util.ArrayList;

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


    public void updatePrescriptionItemsByAdmin(int itemId, double itemPrice, boolean doesExist, boolean isConfirmed)
            throws SQLException {
        String UPDATE_PRESCRIPTION_ITEMS_BY_ADMIN = " UPDATE pharmacy_management_system.prescription " +
                " SET price=?, is_exist=?, is_confirmed=? WHERE item_id=?;";

        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRESCRIPTION_ITEMS_BY_ADMIN);
        preparedStatement.setDouble(1, itemPrice);
        preparedStatement.setBoolean(2, doesExist);
        preparedStatement.setBoolean(3, isConfirmed);
        preparedStatement.setInt(4, itemId);

        if (preparedStatement.executeUpdate() == 1)
            System.out.println("The item updated successfully");
        else
            System.out.println("Error while updating the item.");
    }

    public void updatePrescriptionItemsByPatient(int medId, int medForm, int medQuantity) throws SQLException {
        String UPDATE_PRESCRIPTION_ITEMS_BY_PATIENT = "UPDATE  pharmacy_management_system.prescription" +
                "SET form=?, quantity=? WHERE item_id=? ";

        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRESCRIPTION_ITEMS_BY_PATIENT);
        preparedStatement.setInt(1, medForm);
        preparedStatement.setInt(2, medQuantity);
        preparedStatement.setInt(3, medId);

        if (preparedStatement.executeUpdate() == 1)
            System.out.println("The item updated successfully");
        else
            System.out.println("Error while updating the item.");
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
        System.out.println("                          List of all submitted prescription                              ");
        System.out.println("==========================================================================================");
        result.append("| ");
        result.append(Constant.idFormatter("item"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formFormatter("form"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.priceFormatter("price"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append("|");
        System.out.println("==========================================================================================");

        while (rs.next()) {
            String item = rs.getString("item");
            int form = rs.getInt("form");
            double price = rs.getDouble("price");

            System.out.println(item + " | " + form + " | " + price);
        }
        System.out.println();
    }

    @Override
    public void getAll() throws SQLException {
        String PRINT_LIST_OF_PRESCRIPTION = "SELECT item_id,prescription_id, patient_id,item,form,quantity " +
                "FROM pharmacy_management_system.prescription";

        Connection connection = DbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(PRINT_LIST_OF_PRESCRIPTION);

        StringBuilder result = new StringBuilder();
        System.out.println("                            List of submitted prescription                                ");
        System.out.println("==========================================================================================");
        result.append("| ");
        result.append(Constant.idFormatter("item_id"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.idFormatter("prescription_id"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.idFormatter("patient_id"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.nameFormatter("name"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formFormatter("form"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.quantityFormatter("quantity"));
        result.append(" |");

        System.out.println(result.toString());
        System.out.println("==========================================================================================");

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
        System.out.println();
    }


    public ArrayList<PrescriptionItems> getAPrescriptionBYPatientID(int patientID,int prescriptionId) throws SQLException {
        ArrayList<PrescriptionItems> prescriptionItemList = new ArrayList<>();
        String PRINT_LIST_OF_PRESCRIPTION = "SELECT item_id, item, form, quantity, price " +
                " FROM pharmacy_management_system.prescription where patient_id=? and prescription_id=? ";
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(PRINT_LIST_OF_PRESCRIPTION);
        preparedStatement.setInt(1,patientID);
        preparedStatement.setInt(2,prescriptionId);
        ResultSet rs = preparedStatement.executeQuery();
        PrescriptionItems prescriptionItems;

        while (rs.next()) {
            int itemId = rs.getInt("item_id");
            String item = rs.getString("item");
            int form = rs.getInt("form");
            int quantity = rs.getInt("quantity");
            prescriptionItems = new PrescriptionItems(itemId, item, form, null, null,
                    quantity, null);
            prescriptionItemList.add(prescriptionItems);
        }

        return prescriptionItemList;

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

    public void advancedUpdater(int patientId, int prescriptionID) throws SQLException {
        String ADVANCED_UPDATER = "select" +
                " p.patient_id,p.prescription_id,p.item,m.form,p.quantity,m.price,(p.quantity * m.price) as 'total_price'," +
                " p.is_exist,p.is_confirmed " +
                " from pharmacy_management_system.prescription p " +
                " join pharmacy_management_system.medicine m on " +
                " p.item = m.name" +
                " and p.form = m.form" +
                " where patient_id = ? and prescription_id = ?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ADVANCED_UPDATER);
        preparedStatement.setInt(1, patientId);
        preparedStatement.setInt(2, prescriptionID);

        ResultSet resultSet = preparedStatement.executeQuery();

        StringBuilder result = new StringBuilder();
        System.out.println("                            List of items in prescription                                 ");
        System.out.println("==========================================================================================");
        result.append("| ");
        result.append(Constant.idFormatter("patient_id"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.nameFormatter("prescription_id"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formFormatter("item"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.priceFormatter("form"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.quantityFormatter("quantity"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.isExistFormatter("price"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.isExistFormatter("total_price"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.isExistFormatter("is_exist"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.isExistFormatter("is_confirmed"));
        result.append(" |");

        System.out.println(result.toString());
        while (resultSet.next()) {
            patientId = resultSet.getInt("patient_id");
            prescriptionID = resultSet.getInt("prescription_id");
            String item = resultSet.getString("item");
            int form = resultSet.getInt("form");
            int quantity = resultSet.getInt("quantity");
            double unitPrice = resultSet.getDouble("price");
            double totalPrice = resultSet.getDouble("total_price");
            boolean isExist = resultSet.getBoolean("is_exist");
            boolean isConfirmed = resultSet.getBoolean("is_confirmed");

            System.out.println(patientId + "   | " + prescriptionID + " | " + item + " | " + form + " | " + quantity +
                    " | " + unitPrice + " | " + totalPrice + " | " + isExist + " | " + isConfirmed);
        }

    }

    public void printThePrescriptionCost(int patientId, int prescriptionID) throws SQLException {

        String PRINT_PRESCRIPTION_TOTAL_PRICE = " select p.patient_id, p.prescription_id ,p.is_confirmed ," +
                " sum(p.price * p.quantity) as 'total_price' " +
                " from pharmacy_management_system.prescription p  where p.patient_id = ? and prescription_id =?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(PRINT_PRESCRIPTION_TOTAL_PRICE);
        preparedStatement.setInt(1, patientId);
        preparedStatement.setInt(2, prescriptionID);

        ResultSet resultSet = preparedStatement.executeQuery();

        StringBuilder result = new StringBuilder();
        System.out.println("                            total cost of the prescription                                 ");
        System.out.println("==========================================================================================");
        result.append("| ");
        result.append(Constant.idFormatter("patient_id"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.nameFormatter("prescription_id"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.isExistFormatter("is_confirmed"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.isExistFormatter("total_price"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(" |");

        System.out.println(result.toString());
        while (resultSet.next()) {
            patientId = resultSet.getInt("patient_id");
            prescriptionID = resultSet.getInt("prescription_id");
            boolean isConfirmed = resultSet.getBoolean("is_confirmed");
            double totalPrice = resultSet.getDouble("total_price");
            System.out.println(patientId + " | " + prescriptionID + " | " + isConfirmed + " | " + totalPrice);
        }
    }


    public void confirmTheItemByAdmin(int patientId,PrescriptionItems prescriptionItems) throws SQLException {
        String UPDATE_ISEXIST_AND_ISCONFIRMED_STATUS_OF_ITEMS_BY_ADMIN = " UPDATE pharmacy_management_system.prescription " +
                " SET  is_exist=1, is_confirmed=1 WHERE item=? and form=? and patient_id=? ;";

        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ISEXIST_AND_ISCONFIRMED_STATUS_OF_ITEMS_BY_ADMIN);
        preparedStatement.setString(1, prescriptionItems.getName());
        preparedStatement.setInt(2, prescriptionItems.getForm());
        preparedStatement.setInt(3, patientId);

        if (preparedStatement.executeUpdate() == 1)
            System.out.println("The item updated successfully");
        else
            System.out.println("Error while updating the item.");
    }


}
