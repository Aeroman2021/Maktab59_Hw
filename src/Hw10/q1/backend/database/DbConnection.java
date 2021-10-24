package Hw10.q1.backend.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import hw8.q4.backend.utilities.DbConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {
    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        if (dataSource == null){
            dataSource = new MysqlDataSource();
            ((MysqlDataSource) dataSource).setUrl(hw8.q4.backend.utilities.DbConfig.URL);
            ((MysqlDataSource) dataSource).setUser(hw8.q4.backend.utilities.DbConfig.username);
            ((MysqlDataSource) dataSource).setPassword(DbConfig.password);

        }
        return dataSource.getConnection();
    }
}
