package ge.gov.tsu.studentmanagement.entity.view;

import ge.gov.tsu.studentmanagement.entity.statics.DegreeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "STUDENT_EXTENDED")
public class StudentExtended {
    @Id
    @Column(name = "ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;

    @Column(name = "UNIVERSITY_ID")
    private Long universityId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FIRST_NAME_GE")
    private String georgianFirstName;

    @Column(name = "LAST_NAME_GE")
    private String georgianLastName;

    @Column(name = "university_name_ge")
    private String uniGeorgianName;

    @Column(name = "uni_country_name_ge")
    private String uniCountryGeorgianName;

    @Column(name = "GENDER")
    private Integer gender;

    @Column(name = "CITIZENSHIP")
    private String citizenship;

    @Column(name = "COUNTRY_OF_RESIDENCE")
    private String countryOfResidence;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Column(name = "PASSPORT_NUMBER")
    private String passportNumber;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "CONTACT_PERSON_NAME")
    private String contactPersonName;

    @Column(name = "CONTACT_PERSON_ADDRESS")
    private String contactPersonAddress;

    @Column(name = "CONTACT_PERSON_PHONE")
    private String contactPersonPhone;

    @Column(name = "CONTACT_PERSON_RELATIONSHIP")
    private String contactPersonRelationship;

    @Column(name = "ACCOMMODATION")
    private Boolean accommodation;

    @Column(name = "STATUS", nullable = false)
    private Integer status;

    @Column(name = "LETTER_OF_NOMINATION")
    private String letterOfNominationPath;

    @Column(name = "CV")
    private String cvPath;

    @Column(name = "DIPLOMA")
    private String diplomaPath;

    @Column(name = "LEARNING_AGREEMENT")
    private String learningAgreementPath;

    @Column(name = "UNIVERSITY_RECORD")
    private String universityRecordPath;

    @Column(name = "PICTURE")
    private String picturePath;

    @Column(name = "PASSPORT")
    private String passportPath;

    @Column(name = "PROOF_OF_ENGLISH_SKILL")
    private String proofOfEnglishSkillPath;

    @Column(name = "HEALTH_INSURANCE")
    private String healthInsurancePath;

    @Column(name = "UNIVERSITY_NAME")
    private String universityName;

    @Column(name = "USER_ID")
    private Long userId;


    @Column(name = "DEGREE")
    @Enumerated(EnumType.STRING)
    private DegreeType degree;


    @Column(name = "LETTER_OF_ACCEPTANCE_PATH")
    private String letterOfAcceptancePath;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private List<StudentSemesterExtended> semesters=new ArrayList<>();

    //<editor-fold desc="GETTERS AND SETTERS">

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRegistrationDate() {
        return registrationDate;
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

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
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

    public String getLetterOfNominationPath() {
        return letterOfNominationPath;
    }

    public void setLetterOfNominationPath(String letterOfNominationPath) {
        this.letterOfNominationPath = letterOfNominationPath;
    }

    public String getCvPath() {
        return cvPath;
    }

    public void setCvPath(String cvPath) {
        this.cvPath = cvPath;
    }

    public String getDiplomaPath() {
        return diplomaPath;
    }

    public void setDiplomaPath(String diplomaPath) {
        this.diplomaPath = diplomaPath;
    }

    public String getLearningAgreementPath() {
        return learningAgreementPath;
    }

    public void setLearningAgreementPath(String learningAgreementPath) {
        this.learningAgreementPath = learningAgreementPath;
    }

    public String getUniversityRecordPath() {
        return universityRecordPath;
    }

    public void setUniversityRecordPath(String universityRecordPath) {
        this.universityRecordPath = universityRecordPath;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getPassportPath() {
        return passportPath;
    }

    public void setPassportPath(String passportPath) {
        this.passportPath = passportPath;
    }

    public String getProofOfEnglishSkillPath() {
        return proofOfEnglishSkillPath;
    }

    public void setProofOfEnglishSkillPath(String proofOfEnglishSkillPath) {
        this.proofOfEnglishSkillPath = proofOfEnglishSkillPath;
    }

    public String getHealthInsurancePath() {
        return healthInsurancePath;
    }

    public void setHealthInsurancePath(String healthInsurancePath) {
        this.healthInsurancePath = healthInsurancePath;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<StudentSemesterExtended> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<StudentSemesterExtended> semesters) {
        this.semesters = semesters;
    }

    public String getGeorgianFirstName() {
        return georgianFirstName;
    }

    public void setGeorgianFirstName(String georgianFirstName) {
        this.georgianFirstName = georgianFirstName;
    }

    public String getGeorgianLastName() {
        return georgianLastName;
    }

    public void setGeorgianLastName(String georgianLastName) {
        this.georgianLastName = georgianLastName;
    }

    public String getUniGeorgianName() {
        return uniGeorgianName;
    }

    public void setUniGeorgianName(String uniGeorgianName) {
        this.uniGeorgianName = uniGeorgianName;
    }

    public String getUniCountryGeorgianName() {
        return uniCountryGeorgianName;
    }

    public void setUniCountryGeorgianName(String uniCountryGeorgianName) {
        this.uniCountryGeorgianName = uniCountryGeorgianName;
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

    public String getLetterOfAcceptancePath() {
        return letterOfAcceptancePath;
    }

    public void setLetterOfAcceptancePath(String letterOfAcceptancePath) {
        this.letterOfAcceptancePath = letterOfAcceptancePath;
    }


//</editor-fold>

    /** ეს იყო და წავშალე
     * create view student_extended as
     SELECT c.id,
     c.registration_date,
     c.first_name,
     c.last_name,
     c.email,
     c.address,
     c.birth_date,
     c.citizenship,
     c.city,
     c.country,
     c.country_of_residence,
     c.gender,
     c.contact_person_address,
     c.contact_person_name,
     c.contact_person_phone,
     c.contact_person_relationship,
     c.phone,
     c.status,
     c.passport_number,
     c.university_id,
     c.user_id,
     c.cv,
     c.diploma,
     c.letter_of_nomination,
     c.passport,
     c.picture,
     c.proof_of_english_skill,
     c.health_insurance,
     c.university_record,
     c.learning_agreement,
     sue.name AS university_name,
     sue.year AS semester_year,
     sue.season AS semester_season,
     sue.status AS semester_status,
     sue.visible AS semester_visible
     FROM (students c
     LEFT JOIN semester_universities_extended sue ON ((c.university_id = sue.university_id)))
     WHERE (c.row_id IS NULL);
     */
}
