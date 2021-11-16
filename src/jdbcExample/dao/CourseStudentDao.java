package jdbcExample.dao;

import jdbcExample.config.DataSourceConfig;
import jdbcExample.dao.core.BaseDao;
import jdbcExample.entity.CourseStudent;
import jdbcExample.exception.DataNotFoundException;
import jdbcExample.exception.ModificationDataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseStudentDao implements BaseDao<CourseStudent, Integer> {

    private final DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();

    @Override
    public void save(CourseStudent entity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                     " course_student (student_id, course_id) " +
                     "VALUES(?, ?)")) {
            ps.setInt(1, entity.getStudentId());
            ps.setInt(2, entity.getCourseId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not insert data to db");
        }
    }

    @Override
    public void update(Integer id, CourseStudent newEntity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " +
                     " university_management_system.course_student " +
                     " SET grade=? WHERE id=?")) {
            ps.setDouble(1, newEntity.getGrade());
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    public void updateCourseForStudent(Integer id, Integer courseId) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " +
                     " university_management_system.course_student " +
                     " SET course_id=? WHERE id=?")) {
            ps.setInt(1, courseId);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM course_student " +
                     "WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    @Override
    public void deleteByStudentIdAndCourseID(Integer id1, Integer id2) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM course_student " +
                     "WHERE student_id=? AND course_id=?")) {
            ps.setInt(1, id1);
            ps.setInt(2, id2);
            if (ps.executeUpdate() > 0)
                System.out.println(" Course with id " + id2 + " deleted successfully! ");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }

    }

    @Override
    public CourseStudent loadById(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * " +
                     "FROM course_student WHERE id=?")) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                CourseStudent courseStudent = null;
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                    int studentId = resultSet.getInt("student_id");
                    int courseId = resultSet.getInt("course_id");
                    double grade = resultSet.getDouble("grade");
                    courseStudent = new CourseStudent(id, studentId, courseId, grade);
                }
                return courseStudent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    @Override
    public List<CourseStudent> loadAll() {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * " +
                     " FROM course_student");
             ResultSet resultSet = ps.executeQuery()) {

            List<CourseStudent> courseStudentList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");
                double grade = resultSet.getDouble("grade");
                CourseStudent courseStudent = new CourseStudent(id, studentId, courseId, grade);
                courseStudentList.add(courseStudent);
            }
            return courseStudentList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    public List<CourseStudent> loadCourseOfTheStudent(Integer studentId) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * " +
                     " FROM course_student WHERE student_id=?")) {
            ps.setInt(1, studentId);
            List<CourseStudent> courseStudentList = new ArrayList<>();
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    int courseId = resultSet.getInt("course_id");
                    CourseStudent courseStudent = new CourseStudent(studentId, courseId);
                    courseStudentList.add(courseStudent);
                }
            }
            return courseStudentList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }




}
