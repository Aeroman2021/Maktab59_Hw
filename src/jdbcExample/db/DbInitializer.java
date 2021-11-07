package jdbcExample.db;


import jdbcExample.config.DataSourceConfig;
import jdbcExample.entity.Course;
import jdbcExample.entity.Major;
import jdbcExample.exception.DbException;
import jdbcExample.service.CourseService;
import jdbcExample.service.MajorService;

import java.sql.Connection;
import java.sql.SQLException;

public class DbInitializer {


    private final DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();
    private final CourseService courseService;
    private final MajorService majorService;


    public DbInitializer() {
        courseService = new CourseService();
        majorService = new MajorService();
    }

    private static class Statement {

        private static class Schema {
            private static final String CREATE = "CREATE DATABASE IF NOT EXISTS university_management_system";
        }

        private static class Student {
            private static final String CREATE_TABLE =
                    "create table if not exists student (" +
                            "  `id` smallint(6) NOT NULL," +
                            "  `name` mediumtext NOT NULL," +
                            " `last_name` mediumtext NOT NULL," +
                            " `major_id` smallint(6) NOT NULL," +
                            "  PRIMARY KEY (`id`)," +
                            "KEY `student_FK` (`major_id`)," +
                            " CONSTRAINT `student_FK` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`)  ON UPDATE CASCADE ON DELETE NO ACTION " +
                            "  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        }

        private static class Course {
            private static final String CREATE_TABLE =
                    "create table if not exists course (" +
                            "`id` smallint(6) NOT NULL," +
                            "`name` mediumtext NOT NULL," +
                            "`unit` int(11) NOT NULL," +
                            "PRIMARY KEY (`id`)" +
                            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        }


        private static class Major {
            private static final String CREATE_TABLE =
                    "create table if not exists major (" +
                            "`id` smallint(6) NOT NULL," +
                            "`name` mediumtext NOT NULL," +
                            "PRIMARY KEY (`id`)" +
                            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        }

        private static class CourseStudent {
            private static final String CREATE_TABLE =
                    " create table if not exists course_student (" +
                            "  `id` int(11) NOT NULL," +
                            "`student_id` smallint(6) NOT NULL," +
                            "`course_id` smallint(6) NOT NULL," +
                            " `grade` int(11) DEFAULT NULL," +
                            "  PRIMARY KEY (`id`)," +
                            " KEY `course_student_FK_1` (`course_id`)," +
                            " KEY `course_student_FK` (`student_id`)," +
                            " CONSTRAINT `course_student_FK` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE, " +
                            " CONSTRAINT `course_student_FK_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE " +
                            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        }
    }

    public void initializeDatabase() throws DbException {
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
        }
    }


    public void courseInit() {
        if(courseTableIsEmpty()){
            Course calculation = new Course(1, "Calculation", 3);
            courseService.save(calculation);
            Course staticCourse = new Course(2, "Static", 3);
            courseService.save(staticCourse);
            Course dynamics = new Course(3, "Dynamics", 4);
            courseService.save(dynamics);
            Course fluidMechanicsOne = new Course(4, "Fluid Mechanics I ", 3);
            courseService.save(fluidMechanicsOne);
            Course fluidMechanicsTwo = new Course(5, "Fluid Mechanics II ", 3);
            courseService.save(fluidMechanicsTwo);
            Course heatTransferOne = new Course(6, "Heat transfer I ", 3);
            courseService.save(heatTransferOne);
            Course heatTransferTwo = new Course(7, "Heat transfer II ", 3);
            courseService.save(heatTransferTwo);
            Course mechanicalVibration = new Course(8, "Mechanical Vibration", 3);
            courseService.save(mechanicalVibration);
            Course strengthOfMaterialsOne = new Course(9, "Strength of Material I ", 3);
            courseService.save(strengthOfMaterialsOne);
            Course strengthOfMaterialsTwo = new Course(10, "Strength of Material II ", 3);
            courseService.save(strengthOfMaterialsTwo);
            Course thermodynamicsOne = new Course(11, "Thermodynamics I ", 3);
            courseService.save(thermodynamicsOne);
            Course thermodynamicsTwo = new Course(12, "Thermodynamics II ", 3);
            courseService.save(thermodynamicsTwo);
        }
    }


    public void majorInit() {
        if (majorTableIsEmpty()){
            Major Mechanics = new Major(1, "Mechanics");
            Major Civil = new Major(2, "Civil");
            majorService.save(Mechanics);
            majorService.save(Civil);
        }
    }

    public boolean majorTableIsEmpty() {
        return (majorService.getBaseDao().loadAll() == null);
    }

    public boolean courseTableIsEmpty() {
        return (courseService.getBaseDao().loadAll() == null);
    }

    public void tablesInit(){
        courseInit();
        majorInit();
    }


}
