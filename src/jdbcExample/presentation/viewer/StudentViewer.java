package jdbcExample.presentation.viewer;


import Hw10.q1.utility.Input;
import jdbcExample.entity.Major;
import jdbcExample.entity.Student;
import jdbcExample.service.StudentService;
import jdbcExample.utility.IdGenerator;

public class StudentViewer {
    private final StudentService studentService;

    public StudentViewer() {
        this.studentService = new StudentService();
    }

    public void addStudent(){
        int studentId = IdGenerator.idGenerator();
        String name = Input.getStringInputValue("Enter your name");
        String lastName = Input.getStringInputValue("Enter your lastname");
        String majorName = Input.getStringInputValue("Enter your major name");
        int majorId = Input.getInputValue("Enter your major id");
        Major major = new Major(majorId, majorName);

        Student student = Student.builder().id(studentId).name(name).familyName(lastName).major(major).build();
        studentService.save(student);

    }

    public void updateStudent(){

    }

    public void deleteStudent(){
        int studentId = jdbcExample.utility.Input.getInputValue("Enter student id");
        studentService.deleteById(studentId);
    }

    public void loadStudentById(){
        int studentId = jdbcExample.utility.Input.getInputValue("Enter student id");
        studentService.printStudentInformationById(studentId);
    }

    public void loadAllStudents(){
        studentService.loadAll();
    }
}
