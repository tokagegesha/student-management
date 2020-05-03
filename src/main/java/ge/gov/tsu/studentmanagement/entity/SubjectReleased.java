package ge.gov.tsu.studentmanagement.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SUBJECTS_RELEASED")
public class SubjectReleased  {
    @Id
    @SequenceGenerator(name = "SEQ_SUBJECTS_RELEASED", sequenceName = "SEQ_SUBJECTS_RELEASED", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SUBJECTS_RELEASED")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "MIN_STUDENTS")
    private Integer minStudents;

    @Column(name = "MAX_STUDENTS")
    private Integer maxStudents;

    @Column(name = "SYLLABUS_PATH")
    private String syllabusPath;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Semester semester;


    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    //<editor-fold desc="GETTERS AND SETTERS">

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMinStudents() {
        return minStudents;
    }

    public void setMinStudents(Integer minStudents) {
        this.minStudents = minStudents;
    }

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }


    public String getSyllabusPath() {
        return syllabusPath;
    }

    public void setSyllabusPath(String syllabusPath) {
        this.syllabusPath = syllabusPath;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    //</editor-fold>
}
