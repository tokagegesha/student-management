package ge.gov.tsu.studentmanagement.entity.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "STUDENT_SUBJECT_EXTENDED")
public class StudentSubjectExtended {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "SUBJECT_ID")
    private Long subjectId;

    @Column(name = "SUBJECT_RELEASE_ID")
    private Long subjectReleaseId;

    @Column(name = "SUBJECT_NAME")
    private String subjectName;

    @Column(name = "SUBJECTS_LANGUAGE")
    private String subjectLanguage;

    @Column(name = "subject_credits")
    private Integer credits;

    @Column(name = "STUDENT_ID")
    private Long studentId;

    @Column(name = "SR_MAX_STUDENT")
    private Integer subjectReleaseMaxStudent;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "TAKEN_QUANTITY")
    private Integer takenQuantity;

    @Column(name = "PROGRAMME_NAME")
    private String programmeName;

    @Column(name = "SEMESTER_ID")
    private Long semesterId;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getSubjectReleaseId() {
        return subjectReleaseId;
    }

    public void setSubjectReleaseId(Long subjectReleaseId) {
        this.subjectReleaseId = subjectReleaseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getSubjectReleaseMaxStudent() {
        return subjectReleaseMaxStudent;
    }

    public void setSubjectReleaseMaxStudent(Integer subjectReleaseMaxStudent) {
        this.subjectReleaseMaxStudent = subjectReleaseMaxStudent;
    }

    public Integer getTakenQuantity() {
        return takenQuantity;
    }

    public void setTakenQuantity(Integer takenQuantity) {
        this.takenQuantity = takenQuantity;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }


    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getSubjectLanguage() {
        return subjectLanguage;
    }

    public void setSubjectLanguage(String subjectLanguage) {
        this.subjectLanguage = subjectLanguage;
    }
}
