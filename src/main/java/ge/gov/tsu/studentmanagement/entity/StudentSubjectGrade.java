package ge.gov.tsu.studentmanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT_SUBJECT_GRADES")
public class StudentSubjectGrade {

    @Id
    @SequenceGenerator(name = "SEQ_STUDENT_SUBJECT_GRADES", sequenceName = "SEQ_STUDENT_SUBJECT_GRADES", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDENT_SUBJECT_GRADES")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "SUBJECT_GRADE_ID")
    private Long subjectGradeId;

    @Column(name = "STUDENT_ID")
    private Long studentId;

    private Integer grade;
    

    //<editor-fold desc="GETTERS AND SETTERS">


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectGradeId() {
        return subjectGradeId;
    }

    public void setSubjectGradeId(Long subjectGradeId) {
        this.subjectGradeId = subjectGradeId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
//</editor-fold>
}
