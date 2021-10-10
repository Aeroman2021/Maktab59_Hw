package hw8.q4.frontend;

import hw8.q4.backend.db.dbManager;
import hw8.q4.backend.exceptions.DbException;
import hw8.q4.backend.exceptions.ManagerException;

import java.sql.SQLException;

public class UI {


    public static void main(String[] args) throws SQLException, ManagerException, DbException {
        dbManager dbmanager = new dbManager();
        dbmanager.initializeDatabase();
        dbmanager.insertCityData();
        dbmanager.insertTeamsData();
        dbmanager.insertCoachData();
        dbmanager.insertMatchData();
        dbmanager.insertPlayerData();
        dbmanager.insertStadiumData();
        dbmanager.insertTeamsMatchPerformanceData();

        UIManager manager = new UIManager();
        manager.Run();
    }
}
