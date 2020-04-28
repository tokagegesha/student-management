package ge.gov.tsu.studentmanagement.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause = "ROW_ID IS NULL")
@Table(name = "SUBJECT_GRADES")
public class SubjectGrade {
    @Id
    @SequenceGenerator(name = "SEQ_SUBJECT_GRADES", sequenceName = "SEQ_SUBJECT_GRADES", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SUBJECT_GRADES")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "SUBJECT_RELEASE_ID")
    private Long subjectReleaseId;

    @Column(name = "TYPE")
    private Integer type;

    @Column(name = "MAX_GRADE")
    private Integer max;

    @Column(name = "NAME")
    private String name;

    //<editor-fold desc="GETTERS AND SETTERS">


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectReleaseId() {
        return subjectReleaseId;
    }

    public void setSubjectReleaseId(Long subjectReleaseId) {
        this.subjectReleaseId = subjectReleaseId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer grade) {
        this.max = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//</editor-fold>
}
