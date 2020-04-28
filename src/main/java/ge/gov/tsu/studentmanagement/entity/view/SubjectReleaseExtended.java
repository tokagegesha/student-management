package ge.gov.tsu.studentmanagement.entity.view;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "SUBJECT_RELEASE_EXTENDED")
public class SubjectReleaseExtended {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREDITS")
    private Integer credits;

    @Column(name = "SUBJECT_ID")
    private Long subjectId;

    @Column(name = "LANGUAGE")
    private String language;

    @Column(name = "SEMESTER_ID")
    private Long semesterId;

    @Column(name = "MAX_STUDENTS")
    private Integer maxStudent;

    @Column(name = "MIN_STUDENTS")
    private Integer minStudent;

    @Column(name = "SM_ACADEMIC_REG_BEGIN")
    private Date semesterAcademicRegBegin;

    @Column(name = "SM_ACADEMIC_REG_END")
    private Date semesterAcademicRegEnd;

    @Column(name = "SM_ADMINISTRATION_REG_BEGIN")
    private Date semesterAdministrationRegBegin;

    @Column(name = "SM_ADMINISTRATION_REG_END")
    private Date semesterAdministrationRegEnd;

    @Column(name = "SM_BEGIN_DATE")
    private Date semesterBeginDate;

    @Column(name = "SM_END_DATE")
    private Date semesterEndDate;

    @Column(name = "SM_SEASON")
    private Integer season;

    @Column(name = "SM_YEAR")
    private Integer semesterYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String subjectName) {
        this.name = subjectName;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Integer getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(Integer maxStudent) {
        this.maxStudent = maxStudent;
    }

    public Integer getMinStudent() {
        return minStudent;
    }

    public void setMinStudent(Integer minStudent) {
        this.minStudent = minStudent;
    }

    public Date getSemesterAcademicRegBegin() {
        return semesterAcademicRegBegin;
    }

    public void setSemesterAcademicRegBegin(Date semesterAcademicRegBegin) {
        this.semesterAcademicRegBegin = semesterAcademicRegBegin;
    }

    public Date getSemesterAcademicRegEnd() {
        return semesterAcademicRegEnd;
    }

    public void setSemesterAcademicRegEnd(Date semesterAcademicRegEnd) {
        this.semesterAcademicRegEnd = semesterAcademicRegEnd;
    }

    public Date getSemesterAdministrationRegBegin() {
        return semesterAdministrationRegBegin;
    }

    public void setSemesterAdministrationRegBegin(Date semesterAdministrationRegBegin) {
        this.semesterAdministrationRegBegin = semesterAdministrationRegBegin;
    }

    public Date getSemesterAdministrationRegEnd() {
        return semesterAdministrationRegEnd;
    }

    public void setSemesterAdministrationRegEnd(Date semesterAdministrationRegEnd) {
        this.semesterAdministrationRegEnd = semesterAdministrationRegEnd;
    }

    public Date getSemesterBeginDate() {
        return semesterBeginDate;
    }

    public void setSemesterBeginDate(Date semesterBeginDate) {
        this.semesterBeginDate = semesterBeginDate;
    }

    public Date getSemesterEndDate() {
        return semesterEndDate;
    }

    public void setSemesterEndDate(Date semesterEndDate) {
        this.semesterEndDate = semesterEndDate;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getSemesterYear() {
        return semesterYear;
    }

    public void setSemesterYear(Integer semesterYear) {
        this.semesterYear = semesterYear;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
