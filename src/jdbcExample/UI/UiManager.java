package jdbcExample.UI;

import Hw10.q1.utility.Input;
import jdbcExample.entity.Course;
import jdbcExample.entity.CourseStudent;
import jdbcExample.entity.Major;
import jdbcExample.entity.Student;
import jdbcExample.service.CourseService;
import jdbcExample.service.CourseStudentService;
import jdbcExample.service.MajorService;
import jdbcExample.service.StudentService;
import jdbcExample.utility.IdGenerator;

import java.util.List;

public class UiManager {
    private StudentService studentService;
    private MajorService majorService;
    private CourseService courseService;
    private CourseStudentService courseStudentService;
    private Student student;

    public UiManager() {
        studentService = new StudentService();
        majorService = new MajorService();
        courseStudentService = new CourseStudentService();
        courseService = new CourseService();
        student = new Student();
    }

    public void showMainMenu() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("*** Student MANAGEMENT SYSTEM ***");
            System.out.println("""
                            --- Please select an  action:  ---
                                1- student;
                                2- major
                                3- course
                                4-exit
                    """);
            int option = Input.getInputValue("");
            switch (option) {
                case 1 -> studentActions();
                case 2 -> majorActions();
                case 3 -> courseActions();
                case 4 -> isExit = true;
                default -> System.out.println("Please enter a number in range between 1-4");
            }
        }
    }

    public void studentActions() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("""
                            --- Please select an  action:  ---
                                1- add a student;
                                2- add course for a student
                                3- delete course for a student
                                4- show course by id
                                5- show course list for student
                                6- update the student information
                                7- Delete a  student by id
                                8- get a student by id
                                9-print list of all students
                                10-exit
                    """);
            int option = Input.getInputValue("");
            switch (option) {
                case 1 -> studentCreator();
                case 2 -> addCourseToStudents();
                case 3 -> deleteCourseFromStudent();
                case 4 -> showCourseByIdForStudent();
                case 5 -> showCourseListForStudent();
                case 6 -> studentUpdater();
                case 7 -> studentDeleter();
                case 8 -> studentGetterById();
                case 9 -> studentsGetter();
                case 10 -> isExit = true;
                default -> System.out.println("Please enter a number in range between 1-10");
            }
        }
    }

    public void studentCreator() {
        int studentId = IdGenerator.idGenerator();
        String name = Input.getStringInputValue("Enter your name");
        String lastName = Input.getStringInputValue("Enter your lastname");
        String majorName = Input.getStringInputValue("Enter your major name");
        int majorId = Input.getInputValue("Enter your major id");
        Major major = new Major(majorId, majorName);

        Student student = Student.builder().id(studentId).name(name).familyName(lastName).major(major).build();
        studentService.getBaseDao().save(student);
    }

    public void addCourseToStudents() {
        boolean add = true;
        while (add) {
            CourseStudent courseStudent = null;
            int id = jdbcExample.utility.Input.getInputValue("Enter the id");
            int studentId = jdbcExample.utility.Input.getInputValue("Enter the student id");
            int courseId = jdbcExample.utility.Input.getInputValue("Enter the course id");
            courseStudent = new CourseStudent(id, studentId, courseId, null);
            courseStudentService.save(courseStudent);
            int choice = jdbcExample.utility.Input.getInputValue("Do you want to insert more course? " +
                    "1)yes 2)No");
            if (choice == 2)
                add = false;
        }
    }

    public void deleteCourseFromStudent() {
        boolean delete = true;
        while (delete) {
            int id = jdbcExample.utility.Input.getInputValue("Enter the id");
            courseStudentService.deleteById(id);
            int choice = jdbcExample.utility.Input.getInputValue("Do you want to delete more course? " +
                    "1)yes 2)No");
            if (choice == 2)
                delete = false;
        }
    }

    public void showCourseByIdForStudent() {
        int id = jdbcExample.utility.Input.getInputValue("Enter the id");
        courseStudentService.loadById(id);
    }

    public void showCourseListForStudent() {
        courseStudentService.loadAll();
    }

    public void studentUpdater() {
        int studentId = jdbcExample.utility.Input.getInputValue("Enter student id");
    }

    public void studentDeleter() {
        int studentId = jdbcExample.utility.Input.getInputValue("Enter student id");
        studentService.deleteById(studentId);
    }

    public void studentGetterById() {
        int studentId = jdbcExample.utility.Input.getInputValue("Enter student id");
        studentService.printStudentInformationById(studentId);
    }

    public void studentsGetter() {
        studentService.printAllStudentsInformation();
    }


    public void majorActions() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("""
                            --- Please select an  action:  ---
                                1-print list of all Majors
                                2-exit
                    """);
            int option = Input.getInputValue("");
            switch (option) {
                case 1 -> majorsGetter();
                case 2 -> isExit = true;
                default -> System.out.println("Please enter a number in range between 1-2");
            }
        }
    }


    public void majorsGetter() {
        majorService.printMajorInformation();
    }


    public void courseActions() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("""
                            --- Please select an  action:  ---
                                1-print list of all course
                                2-exit
                    """);
            int option = Input.getInputValue("");
            switch (option) {
                case 1 -> coursesGetter();
                case 2 -> isExit = true;
                default -> System.out.println("Please enter a number in range between 1-2");
            }
        }
    }

    public void coursesGetter() {
        List<Course> courses = courseService.getBaseDao().loadAll();
        for (Course course : courses)
            System.out.println(course);
    }


}
