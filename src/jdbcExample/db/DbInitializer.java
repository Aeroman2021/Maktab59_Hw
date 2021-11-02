package jdbcExample.db;

//import hw8.q4.backend.exceptions.DbException;
import jdbcExample.config.DataSourceConfig;
import jdbcExample.entity.Course;
import jdbcExample.exception.DbException;
import jdbcExample.service.CourseService;
import jdbcExample.service.MajorService;
import jdbcExample.service.StudentService;

import java.sql.Connection;
import java.sql.SQLException;

public class DbInitializer {


    private final DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();
    private Connection connection;
    private StudentService studentService;
    private CourseService courseService;
    private MajorService majorService;


    public DbInitializer() {
        studentService = new StudentService();
        courseService = new CourseService();
        majorService = new MajorService();
    }

    private static class Statement {

        private static class Schema {
            private static String CREATE = "CREATE DATABASE IF NOT EXISTS university_management_system";
        }

        private static class Student {
            private static String CREATE_TABLE =
                    "create table if not exists university_management_system.student (" +
                            "  `id` smallint(6) NOT NULL," +
                            "  `name` mediumtext NOT NULL," +
                            " `last_name` mediumtext NOT NULL," +
                            " `major_id` smallint(6) NOT NULL," +
                            "  PRIMARY KEY (`id`)," +
                            "KEY `student_FK` (`major_id`)," +
                            " CONSTRAINT `student_FK` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`)" +
                            "  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        }

        private static class Course {
            private static String CREATE_TABLE =
                    "create table if not exists university_management_system.course (" +
                            "`id` smallint(6) NOT NULL," +
                            "`name` mediumtext NOT NULL," +
                            "`unit` int(11) NOT NULL," +
                            "PRIMARY KEY (`id`)" +
                            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        }


        private static class Major {
            private static String CREATE_TABLE =
                    "create table if not exists university_management_system.major (" +
                            "`id` smallint(6) NOT NULL," +
                            "`name` mediumtext NOT NULL," +
                            "PRIMARY KEY (`id`)" +
                            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        }

        private static class CourseStudent {
            private static String CREATE_TABLE =
                    "create table if not exists university_management_system.course_student (" +
                            "`student_id` smallint(6) NOT NULL," +
                            "`course_id` smallint(6) NOT NULL," +
                            "`grade` int(11) DEFAULT NULL," +
                            "PRIMARY KEY (`student_id`,`course_id`)" +
                            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4; ";
        }
    }

    public void initializeDatabase() throws SQLException, DbException {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             java.sql.Statement statement = connection.createStatement()) {
            try {
                statement.execute(Statement.Schema.CREATE);
            } catch (SQLException e) {
                throw new jdbcExample.exception.DbException("Error while creating scheme");
            }

            try {
                statement.execute(Statement.Student.CREATE_TABLE);
            } catch (SQLException e) {
                throw new jdbcExample.exception.DbException("Error while creating student table");
            }

            try {
                statement.execute(Statement.Major.CREATE_TABLE);
            } catch (SQLException e) {
                throw new jdbcExample.exception.DbException("Error while creating major table");
            }

            try {
                statement.execute(Statement.Course.CREATE_TABLE);
            } catch (SQLException e) {
                throw new jdbcExample.exception.DbException("Error while creating course table");
            }

            try {
                statement.execute(Statement.CourseStudent.CREATE_TABLE);
            } catch (SQLException e) {
                throw new jdbcExample.exception.DbException("Error while creating course_student table");
            }

        } catch (SQLException e) {
            throw new DbException("Error while connecting to the database");
        } catch (jdbcExample.exception.DbException e) {
            throw e;
        }
    }


    public void courseInit() {
        Course calculation = new Course(1, "Calculation", 3);
        courseService.saveOrUpdate(calculation);
        Course staticCourse = new Course(2, "Static", 3);
        courseService.saveOrUpdate(staticCourse);
        Course dynamics = new Course(3, "Dynamics", 4);
        courseService.saveOrUpdate(dynamics);
        Course fluidMechanicsOne = new Course(4, "Fluid Mechanics I ", 3);
        courseService.saveOrUpdate(fluidMechanicsOne);
        Course fluidMechanicsTwo = new Course(5, "Fluid Mechanics II ", 3);
        courseService.saveOrUpdate(fluidMechanicsTwo);
        Course heatTransferOne = new Course(6, "Heat transfer I ", 3);
        courseService.saveOrUpdate(heatTransferOne);
        Course heatTransferTwo = new Course(7, "Heat transfer II ", 3);
        courseService.saveOrUpdate(heatTransferTwo);
        Course mechanicalVibration = new Course(8, "Mechanical Vibration", 3);
        courseService.saveOrUpdate(mechanicalVibration);
        Course strengthOfMeterialsOne = new Course(9, "Strength of Material I ", 3);
        courseService.saveOrUpdate(strengthOfMeterialsOne);
        Course strengthOfMeterialsTwo = new Course(10, "Strength of Material I ", 3);
        courseService.saveOrUpdate(strengthOfMeterialsTwo);
    }

    public void resettable() throws DbException {
        resetCourseTable();
        resetMajorTable();
    }


    private void resetCourseTable() throws DbException {
        String TRUNCATE_COURSE_TABLE = " TRUNCATE university_management_system.course ";
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             java.sql.Statement statement = connection.createStatement()) {
            statement.execute(TRUNCATE_COURSE_TABLE);
        } catch (jdbcExample.exception.DbException e) {
            throw new jdbcExample.exception.DbException("Error while truncating course table");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void resetMajorTable() throws DbException {
        String TRUNCATE_MAJOR_TABLE = " TRUNCATE university_management_system.major ";
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             java.sql.Statement statement = connection.createStatement()) {
            statement.execute(TRUNCATE_MAJOR_TABLE);
        } catch (jdbcExample.exception.DbException e) {
            throw new jdbcExample.exception.DbException("Error while truncating major table");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
