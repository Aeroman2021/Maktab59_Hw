package Hw10.q1.frontend;

import Hw10.q1.backend.Exception.ServiceExeption;
import Hw10.q1.backend.database.DbManager;
import hw8.q4.backend.exceptions.DbException;
import hw8.q4.backend.exceptions.ManagerException;

import java.sql.SQLException;

public class UI {

    public static void main(String[] args) {
        DbManager dbManager = new DbManager();
        try {
            dbManager.resettable();
            dbManager.initializeDatabase();
            dbManager.initPharmacyStore();
            NewUIManager uiManager = new NewUIManager();
            uiManager.showMainMenu();
        } catch (SQLException | DbException | ServiceExeption | ManagerException e) {
            e.printStackTrace();
        }

    }
}
