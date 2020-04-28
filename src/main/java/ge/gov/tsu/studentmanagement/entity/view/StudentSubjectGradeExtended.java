package ge.gov.tsu.studentmanagement.entity.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "STUDENT_SUBJECT_GRADE_EXTENDED")
public class StudentSubjectGradeExtended {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "STUDENT_SUBJECT_ID")
    private Long studentSubjectId;

    @Column(name = "SUBJECT_RELEASE_ID")
    private Long subjectReleaseId;

    @Column(name = "STUDENT_ID")
    private Long studentId;

    @Column(name = "GRADE")
    private Integer grade;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "MAX_GRADE")
    private Integer maxGrade;

    @Column(name = "MAX_TYPE")
    private Integer maxType;

    @Column(name = "GRADE_NAME")
    private String gradeName;

    @Column(name = "GRADE_ID")
    private Long gradeId;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getStudentSubjectId() {
        return studentSubjectId;
    }

    public void setStudentSubjectId(Long studentSubjectId) {
        this.studentSubjectId = studentSubjectId;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(Integer maxGrade) {
        this.maxGrade = maxGrade;
    }

    public Integer getMaxType() {
        return maxType;
    }

    public void setMaxType(Integer maxType) {
        this.maxType = maxType;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }
}
