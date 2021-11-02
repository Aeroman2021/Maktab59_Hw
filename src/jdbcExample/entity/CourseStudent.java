package jdbcExample.entity;

import jdbcExample.entity.base.BaseEntity;
import jdbcExample.entity.base.SecondaryBaseEntity;

public class CourseStudent implements SecondaryBaseEntity<Integer,Integer> {
    private Integer studentId;
    private Integer courseId;
    private Double grade;

    public CourseStudent(Integer studentId, Integer courseId, Double grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    @Override
    public void setIdOne(Integer id) {
        this.studentId = id;
    }

    @Override
    public Integer getIdOne() {
        return studentId;
    }

    @Override
    public void setIdTwo(Integer id) {
        this.courseId = id;

    }

    @Override
    public Integer getIdTwo() {
        return courseId;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
