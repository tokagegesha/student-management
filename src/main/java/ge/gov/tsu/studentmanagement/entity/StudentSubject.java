package ge.gov.tsu.studentmanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT_SUBJECTS")
public class StudentSubject {

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public enum Type {
        ACTIVE(1),
        DELETED(2);
        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    @Id
    @SequenceGenerator(name = "SEQ_STUDENT_SUBJECTS", sequenceName = "SEQ_STUDENT_SUBJECTS", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDENT_SUBJECTS")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "STUDENT_ID")
    private Long studentId;

    @Column(name = "SUBJECT_RELEASE_ID")
    private Long subjectReleaseId;

    @Column(name = "STATUS")
    private Integer status;


    //<editor-fold desc="GETTERS AND SETTERS">


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

    public Long getSubjectReleaseId() {
        return subjectReleaseId;
    }

    public void setSubjectReleaseId(Long subjectReleaseId) {
        this.subjectReleaseId = subjectReleaseId;
    }

    //</editor-fold>
}
