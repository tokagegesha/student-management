package ge.gov.tsu.studentmanagement.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SUBJECT_RELEASE_SCHEDULE")
public class SubjectReleaseSchedule {
    @Id
    @SequenceGenerator(name = "SEQ_S_R_SCHEDULE", sequenceName = "SEQ_S_R_SCHEDULE", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_S_R_SCHEDULE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "SUBJECT_RELEASE_ID")
    private Long subjectReleaseId;

    @Column(name = "S_DATE")
    private Date date;

    @Column(name = "GROUP_ID")
    private Long groupId;


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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    //</editor-fold>
}
