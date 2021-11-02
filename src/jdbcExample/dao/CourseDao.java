package jdbcExample.dao;

import jdbcExample.config.DataSourceConfig;
import jdbcExample.dao.core.BaseDao;
import jdbcExample.entity.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao implements BaseDao<Course, Integer> {


    DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();
    private Connection connection;

    @Override
    public void save(Course entity) {
        try {
            connection = dataSourceConfig.createDataSource().getConnection();
            String SAVE_NEW_COURSE = "INSERT INTO course (id, name,unit) VALUES(?, ?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NEW_COURSE)) {
                preparedStatement.setInt(1, entity.getId());
                preparedStatement.setString(2, entity.getName());
                preparedStatement.setInt(3, entity.getUnit());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to insert the course to the database");
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
    public void update(Integer id, Course newEntity) {
        try {
            connection = dataSourceConfig.createDataSource().getConnection();
            String UPDATE_NEW_MAJOR = "UPDATE course SET name=?, unit=?  WHERE id=?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NEW_MAJOR)) {
                preparedStatement.setString(1, newEntity.getName());
                preparedStatement.setInt(2, newEntity.getUnit());
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to update the course to the database");
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
            String DELETE_THE_NEW_MAJOR = "DELETE FROM course WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_THE_NEW_MAJOR)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to delete the course to the database");
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
    public Course loadById(Integer id) {
        String SELECT_THE_MAJOR = "SELECT * FROM course WHERE id=?";
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_THE_MAJOR)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Course course = null;
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int unit = resultSet.getInt("unit");
                    course = new Course(id, name,unit);
                }
                return course;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cant find data at db");
        }
        return null;
    }

    @Override
    public List<Course> loadAll() {
        String SELECT_ALL_MAJORS = "SELECT * FROM course";
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MAJORS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Course> courseList = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                int unit = resultSet.getInt("unit");
                Course course = new Course(id, name,unit);
                courseList.add(course);
            }
            return courseList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cant find data at db");
        }
        return null;
    }

}
