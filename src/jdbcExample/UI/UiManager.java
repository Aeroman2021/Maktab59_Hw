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

public class UiManager {
    private StudentService studentService;
    private MajorService majorService;
    private CourseService courseService;
    private CourseStudentService courseStudentService;
    private Student student;

    public UiManager() {
        studentService = new StudentService();
        majorService = new MajorService();
        courseService = new CourseService();
        courseStudentService = new CourseStudentService();
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
            System.out.println("*** Student MANAGEMENT SYSTEM ***");
            System.out.println("""
                            --- Please select an  action:  ---
                                1- add a student;
                                2- update the student information
                                3- Delete a  student by id
                                4- get a student by id
                                5-print list of all students
                                6-exit
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
        Student student = null;
        int studentId = IdGenerator.idGenerator();
        String name = Input.getStringInputValue("Enter your name");
        String lastName = Input.getStringInputValue("Enter your lastname");
        String majorName = Input.getStringInputValue("Enter your major name");
        int majorId = Input.getInputValue("Enter your major id");
        Major major = new Major(majorId, majorName);

        student = Student.builder().id(studentId).name(name).familyName(lastName).major(major).build();
        studentService.saveOrUpdate(student);
    }

    public void addCourseToStudents() {
        boolean add = true;
        while (add){
            CourseStudent courseStudent = null;
            int studentId = jdbcExample.utility.Input.getInputValue("Enter the student id");
            int courseId = jdbcExample.utility.Input.getInputValue("Enter the course id");
            courseStudent = new CourseStudent(studentId, courseId, null);
            courseStudentService.saveOrUpdate(courseStudent);
            int choice = jdbcExample.utility.Input.getInputValue("Do you want to insert more course? " +
                    "1)yes 2)No");
            if(choice==2)
                add = false;
        }
    }

    public void deleteCourseFromStudent() {
        boolean delete = true;
        while (delete){
        int studentId = jdbcExample.utility.Input.getInputValue("Enter the student id");
        int courseId = jdbcExample.utility.Input.getInputValue("Enter the course id");
        courseStudentService.deleteById(studentId, courseId);
            int choice = jdbcExample.utility.Input.getInputValue("Do you want to delete more course? " +
                    "1)yes 2)No");
            if(choice==2)
                delete = false;
        }
    }

    public void showCourseByIdForStudent(){
        int studentId = jdbcExample.utility.Input.getInputValue("Enter the student id");
        int courseId = jdbcExample.utility.Input.getInputValue("Enter the course id");
        courseStudentService.loadById(studentId,courseId);
    }

    public void showCourseListForStudent(){
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
        studentService.loadByid(studentId);
    }

    public void studentsGetter() {
        studentService.loadAll();
    }


    public void majorActions() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("*** Student MANAGEMENT SYSTEM ***");
            System.out.println("""
                            --- Please select an  action:  ---
                                1- add a Major;
                                2- update the Major information
                                3- Delete a Major by id
                                4- get a major by id
                                5-print list of all Majors
                                6-exit
                    """);
            int option = Input.getInputValue("");
            switch (option) {
                case 1 -> majorCreator();
                case 2 -> majorUpdater();
                case 3 -> majorDeleter();
                case 4 -> majorGetterById();
                case 5 -> majorsGetter();
                case 6 -> isExit = true;
                default -> System.out.println("Please enter a number in range between 1-6");
            }
        }
    }

    public void majorCreator() {
        Major major = null;
        String name = Input.getStringInputValue("Enter major name");
        int id = Input.getInputValue("Enter major id");
        major = new Major(id, name);
        majorService.saveOrUpdate(major);
    }

    public void majorUpdater() {

    }

    public void majorDeleter() {
        int id = Input.getInputValue("Enter major id");
        majorService.deleteById(id);
    }

    public void majorGetterById() {
        int id = Input.getInputValue("Enter major id");
        studentService.loadByid(id);
    }

    public void majorsGetter() {
        majorService.loadAll();
    }


    public void courseActions() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("*** Student MANAGEMENT SYSTEM ***");
            System.out.println("""
                            --- Please select an  action:  ---
                                1- add a course;
                                2- update the course information
                                3- Delete a course by id
                                4- get a course by id
                                5-print list of all course
                                6-exit
                    """);
            int option = Input.getInputValue("");
            switch (option) {
                case 1 -> courseCreator();
                case 2 -> courseUpdater();
                case 3 -> courseDeleter();
                case 4 -> courseGetterById();
                case 5 -> coursesGetter();
                case 6 -> isExit = true;
                default -> System.out.println("Please enter a number in range between 1-6");
            }
        }
    }

    public void courseCreator() {

        int id = Input.getInputValue("Enter course id");
        String name = Input.getStringInputValue("Enter course name");
        int unit = Input.getInputValue("Enter course unit");
        Course course = new Course(id, name, unit);
        courseService.saveOrUpdate(course);
    }

    public void courseUpdater() {

    }

    public void courseDeleter() {
        int id = Input.getInputValue("Enter course id");
        courseService.deleteById(id);
    }

    public void courseGetterById() {
        int id = Input.getInputValue("Enter course id");
        courseService.loadByid(id);
    }

    public void coursesGetter() {
        courseService.loadAll();
    }


}
