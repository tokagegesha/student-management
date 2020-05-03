package ge.gov.tsu.studentmanagement.entity;

import ge.gov.tsu.studentmanagement.entity.statics.DegreeType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "STUDENTS")
public class Student  {

    @Id
    @SequenceGenerator(name = "SEQ_STUDENTS", sequenceName = "SEQ_STUDENTS", allocationSize = 1, initialValue = 200)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDENTS")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGISTRATION_DATE", nullable = false)
    private Date registrationDate;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FIRST_NAME_GE")
    private String georgianFirstName;

    @Column(name = "LAST_NAME_GE")
    private String georgianLastName;

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

    @Column(name = "ZIP_CODE")
    private String zipCode;

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

    @Column(name = "LETTER_OF_ACCEPTANCE_PATH")
    private String letterOfAcceptancePath;

    @Column(name = "ACCOMMODATION")
    private Boolean accommodation;

    @Column(name = "DEGREE")
    @Enumerated(EnumType.STRING)
    private DegreeType degree;

    @OneToOne
    @JoinColumn(name = "EXCHANGE_PROGRAMME_ID")
    private ExchangeProgramme exchangeProgramme;

    @ManyToMany
    @JoinTable(
            name = "student_semester",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "semester_id"))
    private List<Semester> semesters;

    @ManyToOne
    private University university;

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

    public String getLetterOfNominationPath() {
        return letterOfNominationPath;
    }

    public void setLetterOfNominationPath(String letterOfNomination) {
        this.letterOfNominationPath = letterOfNomination;
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

    public void setDiplomaPath(String diploma) {
        this.diplomaPath = diploma;
    }

    public String getUniversityRecordPath() {
        return universityRecordPath;
    }

    public void setUniversityRecordPath(String universityRecord) {
        this.universityRecordPath = universityRecord;
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

    public void setProofOfEnglishSkillPath(String proofOfEnglishSkill) {
        this.proofOfEnglishSkillPath = proofOfEnglishSkill;
    }

    public String getHealthInsurancePath() {
        return healthInsurancePath;
    }

    public void setHealthInsurancePath(String gradeDocumentPath) {
        this.healthInsurancePath = gradeDocumentPath;
    }

    public String getLearningAgreementPath() {
        return learningAgreementPath;
    }

    public void setLearningAgreementPath(String learningAgreement) {
        this.learningAgreementPath = learningAgreement;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ExchangeProgramme getExchangeProgramme() {
        return exchangeProgramme;
    }

    public void setExchangeProgramme(ExchangeProgramme exchangeProgramme) {
        this.exchangeProgramme = exchangeProgramme;
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

    public String getLetterOfAcceptancePath() {
        return letterOfAcceptancePath;
    }

    public void setLetterOfAcceptancePath(String letterOfAcceptancePath) {
        this.letterOfAcceptancePath = letterOfAcceptancePath;
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

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    //</editor-fold>
}
