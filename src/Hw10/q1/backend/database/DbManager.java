package Hw10.q1.backend.database;

import Hw10.q1.backend.Exception.DbException;
import Hw10.q1.backend.Exception.ServiceException;
import Hw10.q1.backend.entities.Medicine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbManager {
    private static class Statement {

        private static class Schema {
            private static String CREATE = "CREATE DATABASE IF NOT EXISTS pharmacy_management_system";
        }

        private static class Prescription {
            private static String CREATE_TABLE =
                    "create table if not exists pharmacy_management_system.prescription (" +
                            "id smallint not null," +
                            "patient_id smallint not null," +
                            "item varchar(10) not null," +
                            "form smallint not null," +
                            "quantity int not null," +
                            "price double null," +
                            "is_exist tinyint(1) null," +
                            "primary key (id, patient_id, form, item)" +
                            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        }

        private static class Patient {
            private static String CREATE_TABLE =
                    "create table if not exists pharmacy_management_system.patient (" +
                            " `id` smallint(6) NOT NULL," +
                            "`first_name` varchar(10) NOT NULL," +
                            "`last_name` varchar(10) NOT NULL," +
                            "`sex` varchar(7) NOT NULL," +
                            " `age` smallint(6) NOT NULL," +
                            "`username` mediumtext NOT NULL," +
                            "`password` mediumtext NOT NULL," +
                            "`prescription_counter` int(11) DEFAULT 1," +
                            "`presc_id_1_total_cost` double DEFAULT NULL," +
                            "`presc_id_2_total_cost` double DEFAULT NULL," +
                            "`presc_id_3_total_cost` double DEFAULT NULL," +
                            " PRIMARY KEY (`id`)" +
                            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        }


        private static class Medicine {
            private static String CREATE_TABLE =
                    "create table if not exists pharmacy_management_system.medicine (" +
                            " `id` smallint(6) NOT NULL," +
                            "`name` mediumtext NOT NULL," +
                            "`form` smallint(6) NOT NULL," +
                            "`price` double NOT NULL," +
                            "`quantity` int(11) NOT NULL," +
                            "`is_exist` tinyint(1) DEFAULT NULL," +
                            "  PRIMARY KEY (`id`)" +
                            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        }


    }

    private Connection getConnection() throws SQLException {
        return DbConnection.getConnection();
    }

    public void initializeDatabase() throws SQLException, DbException {

        try (Connection connection = getConnection();
             java.sql.Statement statement = connection.createStatement()) {
            try {
                statement.execute(Statement.Schema.CREATE);
            } catch (SQLException e) {
                throw new DbException("Error while creating Scheme");
            }

            try {
                statement.execute(Statement.Medicine.CREATE_TABLE);
            } catch (SQLException e) {
                throw new DbException("Error while creating medicine table");

            }

            try {
                statement.execute(Statement.Patient.CREATE_TABLE);
            } catch (SQLException e) {
                throw new DbException("Error while creating patient table");
            }

            try {
                statement.execute(Statement.Prescription.CREATE_TABLE);
            } catch (SQLException e) {
                throw new DbException("Error while creating prescription table");
            }


        } catch (SQLException e) {
            throw new DbException("Error while connecting to the database");
        } catch (DbException e) {
            throw e;
        }
    }


    public void insertMedicine(Medicine medicine) throws DbException {
        String INSERT_MEDICINE = "INSERT INTO pharmacy_management_system.medicine " +
                "(id, name,form,price,quantity,is_exist) " +
                " VALUES(?, ?, ?, ?,?,?);";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_MEDICINE)) {
            statement.setInt(1, medicine.getId());
            statement.setString(2, medicine.getName());
            statement.setInt(3, medicine.getForm());
            statement.setDouble(4, medicine.getPrice());
            statement.setInt(5, medicine.getQuantity());
            statement.setBoolean(6, medicine.getDoesExist());

            statement.execute();

        } catch (SQLException e) {
            throw new DbException("Error while inserting medicine: " + medicine.getName());
        }
    }

    public void initPharmacyStore() throws ServiceException, DbException {
        Medicine lidocaineOral = new Medicine(1, "lidocaine", 1, 20000d, 50, true);
        insertMedicine(lidocaineOral);
        Medicine lidocaineSolidOral = new Medicine(2, "lidocaine", 2, 25000d, 40, true);
        insertMedicine(lidocaineSolidOral);
        Medicine lidocaineLiquidOral = new Medicine(3, "lidocaine", 3, 35000d, 60, true);
        insertMedicine(lidocaineLiquidOral);
        Medicine MorphineInjection = new Medicine(4, "Morphine", 1, 20000d, 50, true);
        insertMedicine(MorphineInjection);
        Medicine MorphineInTablet = new Medicine(5, "Morphine", 2, 15000d, 70, true);
        insertMedicine(MorphineInTablet);
        Medicine MorphineOralLiquid = new Medicine(6, "Morphine", 4, 18980d, 40, true);
        insertMedicine(MorphineOralLiquid);
        Medicine codeineTablet = new Medicine(7, "codeine", 1, 42000d, 50, true);
        insertMedicine(codeineTablet);
        Medicine dexamethasoneTablet = new Medicine(8, "dexamethasone", 1, 32000d, 30, true);
        insertMedicine(dexamethasoneTablet);
        Medicine dexamethasoneLiquidOral = new Medicine(9, "dexamethasone", 2, 16000d, 90, true);
        insertMedicine(dexamethasoneLiquidOral);
        Medicine dexamethasoneInjection = new Medicine(10, "dexamethasone", 4, 19000d, 70, true);
        insertMedicine(dexamethasoneInjection);
        Medicine diazepamInjection = new Medicine(11, "diazepam", 1, 20000d, 60, true);
        insertMedicine(diazepamInjection);
        Medicine diazepamTablet = new Medicine(12, "diazepam", 2, 26000d, 30, true);
        insertMedicine(diazepamTablet);
        Medicine diazepamOralLiquid = new Medicine(13, "diazepam", 4, 30000d, 50, true);
        insertMedicine(diazepamOralLiquid);
        Medicine atropineInjection = new Medicine(14, "atropine", 4, 20000d, 60, true);
        insertMedicine(atropineInjection);
        Medicine midazolamInjection = new Medicine(15, "midazolam", 2, 19000d, 40, true);
        insertMedicine(midazolamInjection);
        Medicine midazolamOralLiquid = new Medicine(16, "midazolam", 3, 17000d, 50, true);
        insertMedicine(midazolamOralLiquid);
        Medicine midazolamOralSolid = new Medicine(17, "midazolam", 4, 19500d, 60, true);
        insertMedicine(midazolamOralSolid);
    }

    public void resettable() throws DbException {
        resetMedicineTable();
    }

    private void resetMedicineTable() throws DbException {
        String TRUNCATE_MEDICINE_TABLE = " TRUNCATE pharmacy_management_system.medicine ";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(TRUNCATE_MEDICINE_TABLE)) {

            statement.execute();

        } catch (SQLException e) {
            throw new DbException("Error while truncating medicine table ");
        }
    }

}
