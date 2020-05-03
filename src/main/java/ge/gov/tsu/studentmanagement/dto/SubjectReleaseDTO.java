package ge.gov.tsu.studentmanagement.dto;

import java.util.Date;

public class SubjectReleaseDTO {

    private Long id;

    private String name;

    private Integer credits;

    private Long subjectId;

    private String language;

    private Long semesterId;

    private Integer maxStudent;

    private Integer minStudent;

    private Date semesterAcademicRegBegin;

    private Date semesterAcademicRegEnd;

    private Date semesterAdministrationRegBegin;

    private Date semesterAdministrationRegEnd;
    private Date semesterBeginDate;

    private Date semesterEndDate;

    private Integer season;

    private Integer semesterYear;

    public SubjectReleaseDTO(Long id, String name, Integer credits, Long subjectId, String language, Long semesterId, Integer maxStudent, Integer minStudent, Date semesterAcademicRegBegin, Date semesterAcademicRegEnd, Date semesterAdministrationRegBegin, Date semesterAdministrationRegEnd, Date semesterBeginDate, Date semesterEndDate, Integer season, Integer semesterYear) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.subjectId = subjectId;
        this.language = language;
        this.semesterId = semesterId;
        this.maxStudent = maxStudent;
        this.minStudent = minStudent;
        this.semesterAcademicRegBegin = semesterAcademicRegBegin;
        this.semesterAcademicRegEnd = semesterAcademicRegEnd;
        this.semesterAdministrationRegBegin = semesterAdministrationRegBegin;
        this.semesterAdministrationRegEnd = semesterAdministrationRegEnd;
        this.semesterBeginDate = semesterBeginDate;
        this.semesterEndDate = semesterEndDate;
        this.season = season;
        this.semesterYear = semesterYear;
    }

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