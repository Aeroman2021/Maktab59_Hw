package jdbcExample.dao;

import jdbcExample.config.DataSourceConfig;
import jdbcExample.dao.core.BaseDao;
import jdbcExample.entity.Major;
import jdbcExample.entity.Student;
import jdbcExample.exception.DataNotFoundException;
import jdbcExample.exception.ModificationDataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements BaseDao<Student, Integer> {

    private final DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();

    @Override
    public void save(Student entity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO student " +
                     " (id, name, last_name, major_id) VALUES(?,?, ?, ?)")) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getLastName());
            ps.setInt(4, entity.getMajor().getId());
            if (ps.executeUpdate() > 0)
                System.out.println("The data inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not insert data to db");
        }
    }

    @Override
    public void update(Integer id, Student newEntity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE student " +
                     " SET name=?, last_name=?, major_id=? WHERE id=?  ")) {
            ps.setString(1, newEntity.getName());
            ps.setString(2, newEntity.getLastName());
            ps.setInt(3, newEntity.getMajor().getId());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(" DELETE FROM student WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    @Override
    public Student loadById(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * " +
                     " FROM student WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                Student student = null;
                while (resultSet.next()) {
                    int studentId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String familyName = resultSet.getString("last_name");
                    int majorId = resultSet.getInt("major_id");
                    student = Student.builder()
                            .id(studentId)
                            .name(name)
                            .familyName(familyName)
                            .major(new Major(majorId))
                            .build();
                }
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    @Override
    public List<Student> loadAll() {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(" SELECT * FROM student");
             ResultSet resultSet = ps.executeQuery()) {
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String familyName = resultSet.getString("last_name");
                int majorId = resultSet.getInt("major_id");
                Student student = Student.builder()
                        .id(studentId)
                        .name(name)
                        .familyName(familyName)
                        .major(new Major(majorId))
                        .build();
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    public void printStudentInformationById(int id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(" select s.id,\n " +
                     " s.name,\n" +
                     " s.last_name ,\n" +
                     " s.major_id ,\n" +
                     " m.name as 'major' \n" +
                     " from university_management_system.student s \n" +
                     " join university_management_system.major m on\n" +
                     " s.major_id = m.id " +
                     " where s.id=?" )) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String lastName = resultSet.getString("last_name");
                    int majorId = resultSet.getInt("major_id");
                    String major = resultSet.getString("major");

                    System.out.printf("%2s %8s %8s %2s %8s\n","id", "first_name", "last_name",  "major_id",  "major_name");
                    System.out.printf("%2d %8s %8s %2s %8s\n",id,name,lastName,majorId,major);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    public void printStudentInformation() {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(" select s.id,\n " +
                     " s.name,\n" +
                     " s.last_name ,\n" +
                     " s.major_id ,\n" +
                     " m.name as 'major' \n" +
                     " from university_management_system.student s \n" +
                     " join university_management_system.major m on\n" +
                     " s.major_id = m.id " +
                     " where s.id=?" )) {
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String lastName = resultSet.getString("last_name");
                    int majorId = resultSet.getInt("major_id");
                    String major = resultSet.getString("major");

                    System.out.printf("%2s %8s %8s %2s %8s\n","id", "first_name", "last_name",  "major_id",  "major_name");
                    System.out.printf("%2d %8s %8s %2s %8s\n",id,name,lastName,majorId,major);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    public boolean studentIsExist(Student student){
        List<Student> students = loadAll();
        for(Student student1 : students){
            if(student1.getName().equalsIgnoreCase(student.getName())&&
            student1.getLastName().equalsIgnoreCase(student.getLastName()))
                return true;
        }
        return false;
    }


}
