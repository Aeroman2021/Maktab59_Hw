package hw8.q4.frontend;

import hw8.q4.backend.exceptions.ManagerException;

import java.sql.SQLException;

public class UI {
    public static void main(String[] args) throws SQLException, ManagerException {
        UIManager manager = new UIManager();
        manager.Run();
    }
}
