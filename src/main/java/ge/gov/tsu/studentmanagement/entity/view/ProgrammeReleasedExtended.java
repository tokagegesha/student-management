package ge.gov.tsu.studentmanagement.entity.view;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity(name = "PROGRAMME_RELEASED_EXTENDED")
public class ProgrammeReleasedExtended  {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "UNIVERSITIES_ADDRESS")
    private String universityAddress;

    @Column(name = "UNIVERSITIES_COUNTRY_ID")
    private Long universityCountryId;

    @Column(name = "UNIVERSITIES_NAME")
    private String universityName;

    @Column(name = "UNIVERSITIES_INFO")
    private String universityInfo;

    @Column(name = "UNIVERSITIES_ID")
    private Long universityId;

    @Column(name = "CREDITS")
    private Integer credits;

    @Column(name = "FINISH_DATE")
    private Date finishDate;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "REGISTRATION_START_DATE")
    private Date regStartDate;

    @Column(name = "REGISTRATION_FINISH_DATE")
    private Date regFinishDate;

    @Column(name = "PROGRAMME_ID")
    private Long programId;

    @Column(name = "TYPE")
    private Integer type;

    @Column(name = "VERSION_ID")
    private Long versionId;

    @Column(name = "PROGRAMMES_NAME")
    private String programmeName;

    @Column(name = "PROGRAMMES_DEGREE")
    private Integer programmeDegree;


    public Integer getProgrammeDegree() {
        return programmeDegree;
    }

    public void setProgrammeDegree(Integer programmeDegree) {
        this.programmeDegree = programmeDegree;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniversityAddress() {
        return universityAddress;
    }

    public void setUniversityAddress(String universityAddress) {
        this.universityAddress = universityAddress;
    }

    public Long getUniversityCountryId() {
        return universityCountryId;
    }

    public void setUniversityCountryId(Long universityCountryId) {
        this.universityCountryId = universityCountryId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityInfo() {
        return universityInfo;
    }

    public void setUniversityInfo(String universityInfo) {
        this.universityInfo = universityInfo;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getRegStartDate() {
        return regStartDate;
    }

    public void setRegStartDate(Date regStartDate) {
        this.regStartDate = regStartDate;
    }

    public Date getRegFinishDate() {
        return regFinishDate;
    }

    public void setRegFinishDate(Date regFinishDate) {
        this.regFinishDate = regFinishDate;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }
}
