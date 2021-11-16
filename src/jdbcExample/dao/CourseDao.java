package jdbcExample.dao;

import jdbcExample.config.DataSourceConfig;
import jdbcExample.dao.core.BaseDao;
import jdbcExample.entity.Course;
import jdbcExample.entity.Major;
import jdbcExample.entity.Student;
import jdbcExample.exception.DataNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao implements BaseDao<Course, Integer> {


    DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();

    @Override
    public void save(Course entity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " +
                     " course (id, name,unit) VALUES(?, ?,?);")) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setInt(3, entity.getUnit());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to insert the course to the database");
        }
    }

    @Override
    public void update(Integer id, Course newEntity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " +
                     " course SET name=?, unit=?  WHERE id=?;")) {
            preparedStatement.setString(1, newEntity.getName());
            preparedStatement.setInt(2, newEntity.getUnit());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to update the course to the database");
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM  course WHERE id=? ")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to delete the course to the database");
        }
    }

    @Override
    public Course loadById(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM course WHERE id=?")) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                Course course = null;
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int unit = resultSet.getInt("unit");
                    course = new Course(id, name, unit);
                }
                return course;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    @Override
    public List<Course> loadAll() {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM course");
             ResultSet resultSet = ps.executeQuery()) {
            List<Course> courseList = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                int unit = resultSet.getInt("unit");
                Course course = new Course(id, name, unit);
                courseList.add(course);
            }
            return courseList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    public void printCourseInformation() {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM course");
             ResultSet resultSet = ps.executeQuery()) {
            System.out.println("id" + " | " + " name " + " | " + " unit");
            System.out.println("__________________");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                int unit = resultSet.getInt("unit");
                System.out.println(id + "| " +  name + " | " + unit);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }

    }

    public void loadCourseByStudentId(Integer studentId){
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     " select course_id ,name from university_management_system.course_student cs "+
                    " join university_management_system.course c on" +
                    " cs.id = c.id    where  student_id = ? order by course_id asc ")) {
            ps.setInt(1, studentId);
            try (ResultSet resultSet = ps.executeQuery()) {
                System.out.println("The course list are :");
                while (resultSet.next()) {
                    int id = resultSet.getInt("course_id");
                    String name = resultSet.getString("name");
                    System.out.println("id= " + id + " -> " + name );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

}
