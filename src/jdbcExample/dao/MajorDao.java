package jdbcExample.dao;

import jdbcExample.config.DataSourceConfig;
import jdbcExample.dao.core.BaseDao;
import jdbcExample.entity.Major;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MajorDao implements BaseDao<Major, Integer> {

    DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();
    private Connection connection;


    @Override
    public void save(Major entity) {
        try {
            connection = dataSourceConfig.createDataSource().getConnection();
            String SAVE_NEW_MAJOR = "INSERT INTO course (id, name) VALUES(?, ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NEW_MAJOR)) {
                preparedStatement.setInt(1, entity.getId());
                preparedStatement.setString(2, entity.getName());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to insert the major to the database");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Unable to close the connection");
            }
        }
    }

    @Override
    public void update(Integer id, Major newEntity) {
        try {
            connection = dataSourceConfig.createDataSource().getConnection();
            String UPDATE_NEW_MAJOR = "UPDATE course SET name=?  WHERE id=?;";

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NEW_MAJOR)) {
                preparedStatement.setString(1, newEntity.getName());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to update the major to the database");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Unable to close the connection");
            }
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            connection = dataSourceConfig.createDataSource().getConnection();
            String DELETE_THE_NEW_MAJOR = "DELETE FROM major WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_THE_NEW_MAJOR)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to update the major to the database");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Unable to close the connection");
            }
        }
    }

    @Override
    public Major loadById(Integer id) {
        String SELECT_THE_MAJOR = "SELECT * FROM major WHERE id=?";
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_THE_MAJOR)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Major major = null;
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    major = new Major(id, name);
                }
                return major;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cant find data at db");
        }
        return null;
    }

    @Override
    public List<Major> loadAll() {
        String SELECT_ALL_MAJORS = "SELECT * FROM major";
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MAJORS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Major> majorList = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                Major major = new Major(id, name);
                majorList.add(major);
            }
            return majorList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cant find data at db");
        }
        return null;
    }

}

