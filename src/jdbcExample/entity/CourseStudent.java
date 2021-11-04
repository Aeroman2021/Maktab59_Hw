package jdbcExample.entity;

import jdbcExample.entity.base.BaseEntity;


public class CourseStudent implements BaseEntity<Integer> {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Double grade;

    public CourseStudent(Integer id, Integer studentId, Integer courseId, Double grade) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
