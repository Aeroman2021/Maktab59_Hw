package jdbcExample.presentation.viewer;


import Hw10.q1.utility.Input;
import jdbcExample.entity.Major;
import jdbcExample.entity.Student;
import jdbcExample.exception.DataNotFoundException;
import jdbcExample.exception.ItemIsExist;
import jdbcExample.service.StudentService;
import jdbcExample.utility.IdGenerator;

public class StudentViewer {
    private final StudentService studentService;

    public StudentViewer() {
        this.studentService = new StudentService();
    }

    public void addStudent() {
        boolean addMore = true;
        while (addMore) {
            int studentId = IdGenerator.idGenerator();
            String name = Input.getStringInputValue("Enter your name");
            String lastName = Input.getStringInputValue("Enter your lastname");
            String majorName = Input.getStringInputValue("Enter your major name");
            int majorId = Input.getInputValue("Enter your major id");
            Major major = new Major(majorId, majorName);
            Student student = Student.builder().id(studentId).name(name).familyName(lastName).major(major).build();

            int inputValue = jdbcExample.utility.Input.getInputValue("Do you want to add more students? 1)yes 2)No");
            if(inputValue==2)
                addMore=false;

        }

    }

    public void updateStudent() {
        boolean updateMore = true;
        while (updateMore) {
            int studentId = jdbcExample.UI.Input.getInputValue("Enter student's ID");
            String firstname = jdbcExample.UI.Input.getStringInputValue("Enter student's firstname");
            String lastName = jdbcExample.UI.Input.getStringInputValue("Enter student's lastName");
            Student updatedStudent = Student.builder().id(studentId).name(firstname).familyName(lastName).build();
            studentService.update(studentId, updatedStudent);

            int inputValue = jdbcExample.utility.Input.getInputValue("Do you want to update more students? 1)yes 2)No");
            if(inputValue==2)
                updateMore=false;

        }

    }

    public void deleteStudent() {
        int studentId = jdbcExample.utility.Input.getInputValue("Enter student id");
        Student student = studentService.getBaseDao().loadById(studentId);
        if (studentService.studentIsExist(student))
            throw new DataNotFoundException("TheStudentDoesNotExistAtDataBase");
        studentService.deleteById(studentId);
    }

    public void loadStudentById() {
        int studentId = jdbcExample.utility.Input.getInputValue("Enter student id");
        studentService.printStudentInformationById(studentId);
    }

    public void loadAllStudents() {
        studentService.loadAll();
    }
}
