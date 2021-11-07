package jdbcExample.UI;
import jdbcExample.db.DbInitializer;
import jdbcExample.presentation.Menu;

import java.awt.*;
import java.sql.SQLException;

public class Ui {
    public static void main(String[] args) throws SQLException {
        DbInitializer dbInitializer = new DbInitializer();
        dbInitializer.initializeDatabase();
        dbInitializer.tablesInit();
        Menu menu = new Menu();
        menu.startApplication();

    }
}
