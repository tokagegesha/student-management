package ge.gov.tsu.studentmanagement.pojo;

import java.util.Date;

public class SemesterPojo{
    private Long id;
    private Integer year;
    private Integer season;
    private Boolean visible;
    private Date beginDate;
    private Date endDate;
    private Date academicRegBegin;
    private Date academicRegEnd;
    private Date administrationRegBegin;
    private Date administrationRegEnd;
    private Date currentDate;


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

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
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

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    //</editor-fold>
}
