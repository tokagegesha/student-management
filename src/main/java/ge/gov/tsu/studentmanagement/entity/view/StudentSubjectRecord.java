package ge.gov.tsu.studentmanagement.entity.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Immutable
@Table(name = "STUDENT_SUBJECT_EXTENDED")
public class StudentSubjectRecord {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "SUBJECT_ID")
    private Long subjectId;

    @Column(name = "SUBJECT_RELEASE_ID")
    private Long subjectReleaseId;

    @Column(name = "SUBJECT_NAME")
    private String subjectName;

    @Column(name = "STUDENT_ID")
    private Long studentId;

    @Column(name = "SEMESTER_ID")
    private Long semesterId;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "PROGRAMME_NAME")
    private String programmeName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PASSPORT_NUMBER")
    private String passportNumber;

    @Column(name = "subject_credits")
    private Integer credits;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "STUDENT_SUBJECT_ID")
    @OrderBy("ID ASC")
    private List<StudentSubjectGradeExtended> grades = new ArrayList<>();


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

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getSubjectReleaseId() {
        return subjectReleaseId;
    }

    public void setSubjectReleaseId(Long subjectReleaseId) {
        this.subjectReleaseId = subjectReleaseId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public List<StudentSubjectGradeExtended> getGrades() {
        return grades;
    }

    public void setGrades(List<StudentSubjectGradeExtended> grades) {
        this.grades = grades;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
//</editor-fold>
}
