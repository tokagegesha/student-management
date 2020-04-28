package ge.gov.tsu.studentmanagement.entity;


import javax.persistence.*;

@Entity
@Table(name = "SUBJECT_PREREQUISITE")
public class SubjectPrerequisite {
    @Id
    @SequenceGenerator(name = "SEQ_SUBJECT_PREREQUISITE", sequenceName = "SEQ_SUBJECT_PREREQUISITE", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SUBJECT_PREREQUISITE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "SUBJECT_ID")
    private Long subjectId;

    @Column(name = "PREREQUISITE_SUBJECT_ID")
    private Long prerequisiteSubjectId;


    //<editor-fold desc="GETTERS AND SETTERS">

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long studentId) {
        this.subjectId = studentId;
    }

    public Long getPrerequisiteSubjectId() {
        return prerequisiteSubjectId;
    }

    public void setPrerequisiteSubjectId(Long prerequisiteSubjectId) {
        this.prerequisiteSubjectId = prerequisiteSubjectId;
    }
//</editor-fold>
}
