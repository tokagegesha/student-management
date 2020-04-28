package ge.gov.tsu.studentmanagement.pojo;

import ge.gov.tsu.studentmanagement.apiutils.annotations.Required;
import ge.gov.tsu.studentmanagement.entity.statics.DegreeType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentPojo implements Serializable {
    private List<Integer> statuses;
    private String searchString;

    @Required(on = "edit")
    private Long id;
    private Long userId;
    private Date registrationDate;
    private Long universityId;
    private List<Long> semesterIds;
    private String firstName;
    private String lastName;
    private Integer gender;
    private String citizenship;
    private String countryOfResidence;
    private Date birthDate;
    private String address;
    private String passportNumber;
    private String city;
    private String zipCode;
    private String country;
    private String email;
    private String phone;
    private String contactPersonName;
    private String contactPersonAddress;
    private String contactPersonPhone;
    private String contactPersonRelationship;
    private Boolean accommodation;
    private DegreeType degree;
    private Integer status;

    private String letterOfNomination;
    private String cv;
    private String diploma;
    private String learningAgreement;
    private String universityRecord;
    private String picture;
    private String passport;
    private String proofOfEnglishSkill;
    private String gradeDocument;


    @Required(on = "candidate-registration")
    private Long exchangeProgrammeId;

    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public void setStatuses(List<Integer> statuses) {
        this.statuses = statuses;
    }

    public List<Long> getSemesterIds() {
        return semesterIds;
    }

    public void setSemesterIds(List<Long> semesterIds) {
        this.semesterIds = semesterIds;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonAddress() {
        return contactPersonAddress;
    }

    public void setContactPersonAddress(String contactPersonAddress) {
        this.contactPersonAddress = contactPersonAddress;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }

    public String getContactPersonRelationship() {
        return contactPersonRelationship;
    }

    public void setContactPersonRelationship(String contactPersonRelationship) {
        this.contactPersonRelationship = contactPersonRelationship;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLetterOfNomination() {
        return letterOfNomination;
    }

    public void setLetterOfNomination(String letterOfNomination) {
        this.letterOfNomination = letterOfNomination;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getLearningAgreement() {
        return learningAgreement;
    }

    public void setLearningAgreement(String learningAgreement) {
        this.learningAgreement = learningAgreement;
    }

    public String getUniversityRecord() {
        return universityRecord;
    }

    public void setUniversityRecord(String universityRecord) {
        this.universityRecord = universityRecord;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getProofOfEnglishSkill() {
        return proofOfEnglishSkill;
    }

    public void setProofOfEnglishSkill(String proofOfEnglishSkill) {
        this.proofOfEnglishSkill = proofOfEnglishSkill;
    }

    public String getGradeDocument() {
        return gradeDocument;
    }

    public void setGradeDocument(String gradeDocument) {
        this.gradeDocument = gradeDocument;
    }

    public Long getExchangeProgrammeId() {
        return exchangeProgrammeId;
    }

    public void setExchangeProgrammeId(Long exchangeProgrammeId) {
        this.exchangeProgrammeId = exchangeProgrammeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Integer> getStatuses() {
        if (statuses == null) {
            this.statuses = new ArrayList<>();
        }
        if (statuses.size() == 0) {
            this.statuses.add(1);
            this.statuses.add(2);
            this.statuses.add(3);//fixme
        }
        return statuses;
    }


    public String getSearchString() {
        if (searchString != null) {
            return "%" + searchString.replaceAll("\\s", "%") + "%";
        }
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public Boolean getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Boolean accommodation) {
        this.accommodation = accommodation;
    }

    public DegreeType getDegree() {
        return degree;
    }

    public void setDegree(DegreeType degree) {
        this.degree = degree;
    }
}
