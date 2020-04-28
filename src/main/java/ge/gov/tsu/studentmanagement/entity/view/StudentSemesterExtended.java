package ge.gov.tsu.studentmanagement.entity.view;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name = "STUDENT_SEMESTER_EXTENDED")
public class StudentSemesterExtended {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "semester_Id")
    private Long semesterId;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "year")
    private Integer year;

    @Column(name = "season")
    private Integer season;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
