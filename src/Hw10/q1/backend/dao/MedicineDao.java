package Hw10.q1.backend.dao;

import Hw10.q1.backend.entities.Medicine;
import Hw10.q1.utility.Constant;
import Hw10.q1.utility.CRUDMethods;
import hw8.q4.backend.utilities.DbConnection;

import java.sql.*;

public class MedicineDao implements CRUDMethods<Medicine> {

    private int baseMedQuantity = 0;

    public int getBaseMedQuantity() {
        return baseMedQuantity;
    }

    public void updatePrescriptionItems(String name, int form, double itemPrice, int quantity, boolean isExist) throws SQLException {
        String UPDATE_PRESCRIPTION_ITEMS = "UPDATE medicine.pharmacy_management_system SET " +
                "name=?, form=?, price=?, quantity=?, is_exist=? " +
                "WHERE id=? ";
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRESCRIPTION_ITEMS);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, form);
        preparedStatement.setDouble(3, itemPrice);
        preparedStatement.setInt(4, quantity);
        preparedStatement.setBoolean(5, isExist);
    }


    @Override
    public void getAll() throws SQLException {
        String PRINT_LIST_OF_MEDICINES = "SELECT id,name,form,price,quantity,is_exist FROM " +
                "pharmacy_management_system.medicine";

        Connection connection = DbConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(PRINT_LIST_OF_MEDICINES);
        ResultSet rs = ps.executeQuery();

        StringBuilder result = new StringBuilder();
        System.out.println("==========================================================================================");
        System.out.println("============================List of all submitted prescription============================");
        System.out.println("==========================================================================================");
        result.append("| ");
        result.append(Constant.formatter("id"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formatter("name"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formatter("form"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formatter("price"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formatter("quantity"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formatter("is_exist"));
        result.append("|");

        System.out.println(result.toString());
        System.out.println("__________________________________________________________________________________________");

        while (rs.next()) {
            int id = rs.getInt("id");
            String item = rs.getString("name");
            int form = rs.getInt("form");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            boolean isExist = rs.getBoolean("is_exist");
            System.out.println(id + " | " + item + " | " + form + " | " + price + " | " + quantity + " | " + isExist);
        }
    }


    @Override
    public void update(Medicine medicine) throws SQLException {
        String UPDATE_PRESCRIPTION_ITEMS = "UPDATE medicine.pharmacy_management_system SET " +
                "name=?, form=?, price=?, quantity=?, is_exist=? " +
                "WHERE id=? ";

        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRESCRIPTION_ITEMS);

        preparedStatement.setString(1, medicine.getName());
        preparedStatement.setInt(2, medicine.getForm());
        preparedStatement.setDouble(3, medicine.getPrice());
        preparedStatement.setInt(4, medicine.getQuantity());
        preparedStatement.setBoolean(5, medicine.getDoesExist());
        preparedStatement.setInt(6, medicine.getId());
    }

    public void updateMedicineQuantity(String medicineName, int soldQuantity, int form) throws SQLException {
        String UPDATE_PRESCRIPTION_ITEMS = "UPDATE pharmacy_management_system.medicine " +
                "SET quantity=? WHERE name=? AND form=?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRESCRIPTION_ITEMS);

        Integer baseMedicineQuantity = findMedicineQuantity(medicineName, form);
        preparedStatement.setInt(1, baseMedicineQuantity - soldQuantity);
        preparedStatement.setString(2, medicineName);
        preparedStatement.setInt(3, form);
    }

    private Integer findMedicineQuantity(String medname, int medForm) throws SQLException {
        String PRINT_QUANTITY_OF_SOLD_MEDICINES = "SELECT quantity FROM " +
                "pharmacy_management_system.medicine WHERE name=? AND form=?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(PRINT_QUANTITY_OF_SOLD_MEDICINES);
        preparedStatement.setString(1, medname);
        preparedStatement.setInt(2, medForm);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next())
            baseMedQuantity = rs.getInt("quantity");

        return baseMedQuantity;
    }

    public boolean findMedicineByNameAndForm(String medName, int medForm) throws SQLException {
        String FIND_MED_BY_NAME_AND_FORM = "SELECT quantity FROM " +
                "pharmacy_management_system.medicine WHERE name=? AND form=?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(FIND_MED_BY_NAME_AND_FORM);
        ps.setString(1, medName);
        ps.setInt(2, medForm);
        ResultSet rs = ps.executeQuery();

        return (rs.next());

    }


}
