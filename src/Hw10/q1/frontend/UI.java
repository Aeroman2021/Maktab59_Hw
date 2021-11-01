package Hw10.q1.frontend;
import Hw10.q1.backend.Exception.DbException;
import Hw10.q1.backend.Exception.ManagerException;
import Hw10.q1.backend.Exception.ServiceException;
import Hw10.q1.backend.database.DbManager;
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
        } catch (SQLException | DbException | ServiceException | ManagerException e) {
            e.printStackTrace();
        }

    }
}
