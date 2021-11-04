package jdbcExample.UI;
import jdbcExample.db.DbInitializer;
import java.sql.SQLException;

public class Ui {
    public static void main(String[] args) throws SQLException {
        DbInitializer dbInitializer = new DbInitializer();
        dbInitializer.initializeDatabase();
        dbInitializer.courseInit();
        dbInitializer.majorInit();
        UiManager uiManager = new UiManager();
        uiManager.showMainMenu();
    }
}
