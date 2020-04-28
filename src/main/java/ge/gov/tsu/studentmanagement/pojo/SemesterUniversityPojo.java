package ge.gov.tsu.studentmanagement.pojo;

public class SemesterUniversityPojo {
    private Long universityId;
    private Long semesterId;
    private Boolean visible;
    private Boolean semesterVisibility;

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getSemesterVisibility() {
        return semesterVisibility;
    }

    public void setSemesterVisibility(Boolean semesterVisibility) {
        this.semesterVisibility = semesterVisibility;
    }
}
