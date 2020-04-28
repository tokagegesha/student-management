package ge.gov.tsu.studentmanagement.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PROGRAMME_SUBJECTS")
public class ProgrammeSubject {

    @Column(name = "ID", nullable = false)
    @Id
    @SequenceGenerator(name = "SEQ_PROGRAMME_SUBJECTS", sequenceName = "SEQ_PROGRAMME_SUBJECTS", allocationSize = 1, initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROGRAMME_SUBJECTS")
    private Long id;

    @ManyToOne
    private Programme programme;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Semester semester;

    @Column( nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    //<editor-fold desc="GETTERS AND SETTERS">

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
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
