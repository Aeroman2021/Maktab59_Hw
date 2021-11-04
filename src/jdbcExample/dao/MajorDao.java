package jdbcExample.dao;

import jdbcExample.config.DataSourceConfig;
import jdbcExample.dao.core.BaseDao;
import jdbcExample.entity.Major;
import jdbcExample.exception.DataNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MajorDao implements BaseDao<Major, Integer> {

    DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();

    @Override
    public void save(Major entity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " +
                     "major (id, name) VALUES(?, ?);")) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to insert the major to the database");
        }
    }

    @Override
    public void update(Integer id, Major newEntity) {
        try ( Connection connection = dataSourceConfig.createDataSource().getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement("UPDATE major " +
                      "SET name=?  WHERE id=?;")) {
                preparedStatement.setString(1, newEntity.getName());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to update the major to the database");
        }
    }

    @Override
    public void delete(Integer id) {
        try ( Connection connection = dataSourceConfig.createDataSource().getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM major WHERE id=?")) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to update the major to the database");
        }
    }

    @Override
    public Major loadById(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * " +
                     " FROM major WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
               Major major = null;
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    major = new Major(id, name);
                }
                return major;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    @Override
    public List<Major> loadAll() {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM major");
             ResultSet resultSet = ps.executeQuery()) {

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
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    public void printCourseInformation() {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM major");
             ResultSet resultSet = ps.executeQuery()) {

            System.out.printf("%2s %8s \n", "id", "major");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");

                System.out.printf("%2d %8s \n",id,name);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

}

