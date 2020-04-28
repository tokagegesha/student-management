package ge.gov.tsu.studentmanagement.entity;


import javax.persistence.*;

@Entity
@Table(name = "STUDENT_SEMESTERS")
public class StudentSemester {

    @Id
    @SequenceGenerator(name = "SEQ_STUDENT_SEMESTERS", sequenceName = "SEQ_STUDENT_SEMESTERS", allocationSize = 1,initialValue = 200)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDENT_SEMESTERS")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "STUDENT_ID")
    private Long studentId;

    @Column(name = "SEMESTER_ID")
    private Long semesterId;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }
//</editor-fold>
}
