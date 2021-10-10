package hw8.q4.frontend;

import hw8.q4.backend.db.dbManager;
import hw8.q4.backend.exceptions.DbException;
import hw8.q4.backend.exceptions.ManagerException;

import java.sql.SQLException;

public class UI {


    public static void main(String[] args) throws SQLException, ManagerException, DbException {
        dbManager dbmanager = new dbManager();
        dbmanager.initializeDatabase();
        dbmanager.InsertCityData();
        dbmanager.InsertTeamsData();
        dbmanager.InsertCoachData();
        dbmanager.InsertMatchData();
        dbmanager.InsertPlayerData();
        dbmanager.InsertStadiumData();
        dbmanager.InsertTeamsMatchPerformanceData();

        UIManager manager = new UIManager();
        manager.Run();
    }
}
