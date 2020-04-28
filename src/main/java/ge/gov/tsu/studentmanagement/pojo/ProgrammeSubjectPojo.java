package ge.gov.tsu.studentmanagement.pojo;

import ge.gov.tsu.studentmanagement.apiutils.annotations.Required;

public class ProgrammeSubjectPojo {
    private Long id;
    @Required(on = "programme-subject")
    private Long subjectId;
    private Long semesterNumber;
    private Integer semesterYear;
    @Required(on = "programme-subject")
    private Long semesterId;
    @Required(on = "programme-subject")
    private Long programmeId;
    private Boolean visible;
    private String subjectName;
    private Long studentId;

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(Long semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    public Integer getSemesterYear() {
        return semesterYear;
    }

    public void setSemesterYear(Integer semesterYear) {
        this.semesterYear = semesterYear;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(Long programmeId) {
        this.programmeId = programmeId;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
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
}
