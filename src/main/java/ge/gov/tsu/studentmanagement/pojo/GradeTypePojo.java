package ge.gov.tsu.studentmanagement.pojo;

public class GradeTypePojo {
    private Long id;
    private String name;
    private Integer max;
    private Integer type;
    private Long subjectId;
    private Long subjectReleaseId;
    private Long semesterId;

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

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }
}
