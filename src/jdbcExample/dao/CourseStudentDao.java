package jdbcExample.dao;

import jdbcExample.config.DataSourceConfig;
import jdbcExample.dao.core.SecondaryBaseDao;
import jdbcExample.entity.CourseStudent;
import jdbcExample.exception.DataNotFoundException;
import jdbcExample.exception.ModificationDataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseStudentDao implements SecondaryBaseDao<CourseStudent, Integer, Integer> {

    private final DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();
    private Connection connection;


    @Override
    public void save(CourseStudent entity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO course_student " +
                    "(student_id, course_id, grade) " +
                    "VALUES(?, ?, ?)")) {
                ps.setInt(1, entity.getIdOne());
                ps.setInt(2, entity.getIdTwo());
                ps.setDouble(3, entity.getGrade());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not insert data to db");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Integer idOne, Integer idTwo, CourseStudent newEntity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE course_student " +
                     "SET grade=? WHERE student_id=? AND course_id=?")) {
            ps.setDouble(1, newEntity.getGrade());
            ps.setInt(2, idOne);
            ps.setInt(3, idTwo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    @Override
    public void delete(Integer idOne, Integer idTwo) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM course_student " +
                     "WHERE student_id=? AND course_id=?")) {
            ps.setInt(1, idOne);
            ps.setInt(1, idTwo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    @Override
    public CourseStudent loadById(Integer idOne, Integer idTwo) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * " +
                     "FROM Student WHERE student_id=? AND course_id=?")) {
            ps.setInt(1, idOne);
            ps.setInt(1, idTwo);
            try (ResultSet resultSet = ps.executeQuery()) {
                CourseStudent courseStudent = null;
                while (resultSet.next()) {
                    int studentId = resultSet.getInt("student_id");
                    int courseId = resultSet.getInt("course_id");
                    double grade = resultSet.getDouble("grade");
                    courseStudent = new CourseStudent(studentId, courseId, grade);
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
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");
                double grade = resultSet.getDouble("grade");
                CourseStudent courseStudent = new CourseStudent(studentId, courseId, grade);
                courseStudentList.add(courseStudent);
            }
            return courseStudentList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }


}
