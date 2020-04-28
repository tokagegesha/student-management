package ge.gov.tsu.studentmanagement.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SEMESTERS")
@Where(clause = "ROW_ID IS NULL")
public class Semester {

    @Id
    @SequenceGenerator(name = "SEQ_SEMESTERS", sequenceName = "SEQ_SEMESTERS", allocationSize = 1, initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SEMESTERS")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "SEASON")
    private Integer season;

    @Column(name = "BEGIN_DATE")
    private Date beginDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "ACADEMIC_REG_BEGIN")
    private Date academicRegBegin;

    @Column(name = "ACADEMIC_REG_END")
    private Date academicRegEnd;

    @Column(name = "ADMINISTRATION_REG_BEGIN")
    private Date administrationRegBegin;

    @Column(name = "ADMINISTRATION_REG_END")
    private Date administrationRegEnd;

    @Column(name = "VISIBLE")
    private Boolean visible;

    @Column( nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    //<editor-fold desc="GETTERS AND SETTERS">

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
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

    public void setSeason(Integer number) {
        this.season = number;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getAcademicRegBegin() {
        return academicRegBegin;
    }

    public void setAcademicRegBegin(Date academicRegBegin) {
        this.academicRegBegin = academicRegBegin;
    }

    public Date getAcademicRegEnd() {
        return academicRegEnd;
    }

    public void setAcademicRegEnd(Date academicRegEnd) {
        this.academicRegEnd = academicRegEnd;
    }

    public Date getAdministrationRegBegin() {
        return administrationRegBegin;
    }

    public void setAdministrationRegBegin(Date administrationRegBegin) {
        this.administrationRegBegin = administrationRegBegin;
    }

    public Date getAdministrationRegEnd() {
        return administrationRegEnd;
    }

    public void setAdministrationRegEnd(Date administrationRegEnd) {
        this.administrationRegEnd = administrationRegEnd;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    //</editor-fold>
}
