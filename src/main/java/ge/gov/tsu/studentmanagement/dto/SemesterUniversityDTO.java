package ge.gov.tsu.studentmanagement.dto;

public class SemesterUniversityDTO {

    private Long id;
    private Long universityId;
    private Boolean visible;
    private Long semesterId;
    private Long countryId;
    private String name;
    private String address;
    private String countryName;
    private String info;

    public SemesterUniversityDTO(Long id, Long universityId, Boolean visible, Long semesterId, Long countryId, String name, String address, String countryName, String info) {
        this.id = id;
        this.universityId = universityId;
        this.visible = visible;
        this.semesterId = semesterId;
        this.countryId = countryId;
        this.name = name;
        this.address = address;
        this.countryName = countryName;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
