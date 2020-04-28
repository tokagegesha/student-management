package ge.gov.tsu.studentmanagement.entity;


import javax.persistence.*;

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

    @Column(name = "SUBJECT_ID")
    private Long subjectId;

    @Column(name = "SEMESTER_ID")
    private Long semesterId;

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

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSyllabusPath() {
        return syllabusPath;
    }

    public void setSyllabusPath(String syllabusPath) {
        this.syllabusPath = syllabusPath;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

//</editor-fold>
}
