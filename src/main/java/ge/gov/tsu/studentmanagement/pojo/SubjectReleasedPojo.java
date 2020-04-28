package ge.gov.tsu.studentmanagement.pojo;

import org.springframework.web.multipart.MultipartFile;

public class SubjectReleasedPojo {
    private Long id;
    private Integer minStudent;
    private Integer maxStudent;
    private MultipartFile syllabus;
    private Long subjectId;
    private Long semesterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMinStudent() {
        return minStudent;
    }

    public void setMinStudent(Integer minStudent) {
        this.minStudent = minStudent;
    }

    public Integer getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(Integer maxStudent) {
        this.maxStudent = maxStudent;
    }

    public MultipartFile getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(MultipartFile syllabus) {
        this.syllabus = syllabus;
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
}
