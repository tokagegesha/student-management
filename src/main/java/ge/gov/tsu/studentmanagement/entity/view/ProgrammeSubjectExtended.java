package ge.gov.tsu.studentmanagement.entity.view;


import ge.gov.tsu.studentmanagement.entity.statics.LanguageType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity(name = "PROGRAMME_SUBJECT_EXTENDED")
public class ProgrammeSubjectExtended {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "SUBJECTS_ID")
    private Long subjectId;

    @Column(name = "PROGRAMME_ID")
    private Long programmeId;

    @Column(name = "SR_ID")
    private Long subjectReleaseId;

    @Column(name = "SM_ID")
    private Long semesterId;

    @Column(name = "SUBJECTS_NAME")
    private String subjectName;

    @Column(name = "SUBJECTS_LANGUAGE")
    private String subjectLanguage;

    @Column(name = "PROGRAMME_NAME")
    private String programmeName;

    @Column(name = "SUBJECTS_CREDITS")
    private Integer subjectCredit;

    @Column(name = "SR_MAX_STUDENT")
    private Integer subjectReleaseMaxStudent;

    @Column(name = "SR_MIN_STUDENT")
    private Integer subjectReleaseMinStudent;

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


    public Long getSubjectReleaseId() {
        return subjectReleaseId;
    }

    public void setSubjectReleaseId(Long programmeReleaseId) {
        this.subjectReleaseId = programmeReleaseId;
    }

    public Long getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(Long programmeId) {
        this.programmeId = programmeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getSubjectCredit() {
        return subjectCredit;
    }

    public void setSubjectCredit(Integer subjectCredit) {
        this.subjectCredit = subjectCredit;
    }

    public Integer getSubjectReleaseMaxStudent() {
        return subjectReleaseMaxStudent;
    }

    public void setSubjectReleaseMaxStudent(Integer subjectReleaseMaxStudent) {
        this.subjectReleaseMaxStudent = subjectReleaseMaxStudent;
    }

    public Integer getSubjectReleaseMinStudent() {
        return subjectReleaseMinStudent;
    }

    public void setSubjectReleaseMinStudent(Integer subjectReleaseMinStudent) {
        this.subjectReleaseMinStudent = subjectReleaseMinStudent;
    }

    public Date getSemesterAcademicRegBegin() {
        return semesterAcademicRegBegin;
    }

    public void setSemesterAcademicRegBegin(Date subjectSemesterAcademicRegBegin) {
        this.semesterAcademicRegBegin = subjectSemesterAcademicRegBegin;
    }

    public Date getSemesterAcademicRegEnd() {
        return semesterAcademicRegEnd;
    }

    public void setSemesterAcademicRegEnd(Date subjectSemesterAcademicRegEnd) {
        this.semesterAcademicRegEnd = subjectSemesterAcademicRegEnd;
    }

    public Date getSemesterAdministrationRegBegin() {
        return semesterAdministrationRegBegin;
    }

    public void setSemesterAdministrationRegBegin(Date subjectSemesterAdministrationRegBegin) {
        this.semesterAdministrationRegBegin = subjectSemesterAdministrationRegBegin;
    }

    public Date getSemesterAdministrationRegEnd() {
        return semesterAdministrationRegEnd;
    }

    public void setSemesterAdministrationRegEnd(Date subjectSemesterAdministrationRegEnd) {
        this.semesterAdministrationRegEnd = subjectSemesterAdministrationRegEnd;
    }

    public Date getSemesterBeginDate() {
        return semesterBeginDate;
    }

    public void setSemesterBeginDate(Date subjectSemesterBeginDate) {
        this.semesterBeginDate = subjectSemesterBeginDate;
    }

    public Date getSemesterEndDate() {
        return semesterEndDate;
    }

    public void setSemesterEndDate(Date subjectSemesterEndDate) {
        this.semesterEndDate = subjectSemesterEndDate;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer subjectReleaseNumber) {
        this.season = subjectReleaseNumber;
    }

    public Integer getSemesterYear() {
        return semesterYear;
    }

    public void setSemesterYear(Integer subjectReleaseYear) {
        this.semesterYear = subjectReleaseYear;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public String getSubjectLanguage() {
        return subjectLanguage;
    }

    public void setSubjectLanguage(String subjectLanguage) {
        this.subjectLanguage = subjectLanguage;
    }
}
