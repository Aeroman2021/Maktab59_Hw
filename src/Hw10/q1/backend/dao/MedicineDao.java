package Hw10.q1.backend.dao;

import Hw10.q1.backend.entities.Medicine;
import Hw10.q1.utility.Constant;
import Hw10.q1.utility.CRUDMethods;
import hw8.q4.backend.exceptions.ManagerException;
import hw8.q4.backend.utilities.DbConnection;

import java.sql.*;

public class MedicineDao implements CRUDMethods<Medicine> {
    private int medicineId;
    private int initialMedicineQuantity;






    public void updateMedicineItemsAtStoreByAdmin(int id, double itemPrice, int quantity, boolean isExist) throws SQLException {
        String UPDATE_MEDICINE_ITEMS_AT_STORE_BY_ADMIN = "UPDATE pharmacy_management_system.medicine SET " +
                " price=?, quantity=?, is_exist=? WHERE id=? ";
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MEDICINE_ITEMS_AT_STORE_BY_ADMIN);
        preparedStatement.setDouble(1, itemPrice);
        preparedStatement.setInt(2, quantity);
        preparedStatement.setBoolean(3, isExist);
        preparedStatement.setInt(4, id);
        if (preparedStatement.executeUpdate() == 1)
            System.out.println("The item updated successfully");
        else
            System.out.println("Unable to update the item");
    }

    @Override
    public void getAll() throws SQLException {
        String PRINT_LIST_OF_MEDICINES = "SELECT id,name,form,price,quantity,is_exist FROM " +
                " pharmacy_management_system.medicine";

        Connection connection = DbConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(PRINT_LIST_OF_MEDICINES);
        ResultSet rs = ps.executeQuery();

        StringBuilder result = new StringBuilder();
        System.out.println("                            List of available Medicine                                    ");
        System.out.println("==========================================================================================");
        result.append("| ");
        result.append(Constant.idFormatter("id"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.nameFormatter("name"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.formFormatter("form"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.priceFormatter("price"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.quantityFormatter("quantity"));
        result.append(Constant.COLUMN_SEPARATOR);
        result.append(Constant.isExistFormatter("is_exist"));
        result.append(" |");

        System.out.println(result.toString());

        System.out.println("==========================================================================================");
        while (rs.next()) {
            int id = rs.getInt("id");
            String item = rs.getString("name");
            int form = rs.getInt("form");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            boolean isExist = rs.getBoolean("is_exist");
            System.out.println(id + "   | " + item + " | " + form + " | " + price + " | " + quantity + " | " + isExist);
        }
        System.out.println();
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

        if (preparedStatement.executeUpdate() == 1)
            System.out.println("The item updated successfully");
        else
            System.out.println("Unable to update the item");
    }

    public void updateMedicineQuantity(String name, int soldQuantity, int form) throws SQLException, ManagerException {
        String UPDATE_PRESCRIPTION_ITEMS = "UPDATE pharmacy_management_system.medicine " +
                "SET quantity=? WHERE id=?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRESCRIPTION_ITEMS);

        Integer medicineQuantity = findMedicineQuantity(name, form);
        Integer foundMedId = findMedicineItemByNameAndForm(name, form);
        int updatedMedicineQuantity = medicineQuantity - soldQuantity;

        if(updatedMedicineQuantity >= 0 ){
            preparedStatement.setInt(1, updatedMedicineQuantity);
            preparedStatement.setInt(2, foundMedId);
        }else
            throw new ManagerException("Not enough medicine exist");


        if (preparedStatement.executeUpdate() == 1)
            System.out.println("The item updated successfully");
        else
            System.out.println("Unable to update the item");
    }

    private Integer findMedicineQuantity(String name, int form) throws SQLException {
        String PRINT_QUANTITY_OF_SOLD_MEDICINES = "SELECT quantity FROM " +
                "pharmacy_management_system.medicine WHERE id=?";
        Connection connection = DbConnection.getConnection();
        if(findMedicineItemByNameAndForm(name,form)!= null){
            Integer medId = findMedicineItemByNameAndForm(name,form);
            PreparedStatement preparedStatement = connection.prepareStatement(PRINT_QUANTITY_OF_SOLD_MEDICINES);
            preparedStatement.setInt(1, medId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                initialMedicineQuantity = rs.getInt("quantity");
            }
            return initialMedicineQuantity;
        }
        return null;
    }


    public Integer findMedicineItemByNameAndForm(String name, int form) throws SQLException {
        String PRINT_THE_MEDICINES = "SELECT id FROM " +
                " pharmacy_management_system.medicine where name=? and form=?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(PRINT_THE_MEDICINES);
        ps.setString(1,name);
        ps.setInt(2,form);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            medicineId = rs.getInt("id");
        }
        return medicineId;
    }
}
