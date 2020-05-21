package ge.gov.tsu.studentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ge.gov.tsu.studentmanagement.entity.statics.LanguageType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SUBJECTS")
public class Subject {
    @Id
    @SequenceGenerator(name = "SEQ_SUBJECTS", sequenceName = "SEQ_SUBJECTS", allocationSize = 1, initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SUBJECTS")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREDITS")
    private Integer credits;

    private Integer defaultMinStudent = 10;

    private Integer defaultMaxStudent = 100;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "EXECUTIVE_USER_ID")
    private Long executiveUserId;

    @Enumerated(EnumType.STRING)
    @Column(name = "LANGUAGE")
    private LanguageType language;


    @JsonBackReference
    @OneToMany(mappedBy = "subject")
    private List<SubjectReleased> subjectReleaseds;


    //<editor-fold desc="GETTERS AND SETTERS">


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Long getExecutiveUserId() {
        return executiveUserId;
    }

    public void setExecutiveUserId(Long executiveUserId) {
        this.executiveUserId = executiveUserId;
    }

    public LanguageType getLanguage() {
        return language;
    }

    public void setLanguage(LanguageType language) {
        this.language = language;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getDefaultMinStudent() {
        return defaultMinStudent;
    }

    public void setDefaultMinStudent(Integer defaultMinStudent) {
        this.defaultMinStudent = defaultMinStudent;
    }

    public Integer getDefaultMaxStudent() {
        return defaultMaxStudent;
    }

    public void setDefaultMaxStudent(Integer defaultMaxStudent) {
        this.defaultMaxStudent = defaultMaxStudent;
    }

    public List<SubjectReleased> getSubjectReleaseds() {
        return subjectReleaseds;
    }

    public void setSubjectReleaseds(List<SubjectReleased> subjectReleaseds) {
        this.subjectReleaseds = subjectReleaseds;
    }

    //</editor-fold>
}
