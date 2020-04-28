package ge.gov.tsu.studentmanagement.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ge.gov.tsu.studentmanagement.entity.statics.Country;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "UNIVERSITIES")
public class University {
    @Id
    @SequenceGenerator(name = "SEQ_UNIVERSITIES", sequenceName = "SEQ_UNIVERSITIES", allocationSize = 1, initialValue = 200)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_UNIVERSITIES")
    @Column(name = "ID", nullable = false)
    private Long id;


    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "INFO")
    private String info;

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DEPARTAMENT_HEAD_NAME")
    private String departmentHeadName;

    @Column(name = "RECTOR")
    private String rector;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NAME_GE")
    private String georgianName;

    @Column(name = "ORDER_NUMBER")
    private Integer orderNumber;

    @ManyToOne
    private Country country;


@JsonBackReference

    @OneToMany(mappedBy = "university")
    private List<SemesterUniversity> semesterUniversities;

    //<editor-fold desc="GETTERS AND SETTERS">

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentHeadName() {
        return departmentHeadName;
    }

    public void setDepartmentHeadName(String departamentHeadName) {
        this.departmentHeadName = departamentHeadName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRector() {
        return rector;
    }

    public void setRector(String rector) {
        this.rector = rector;
    }

    public String getGeorgianName() {
        return georgianName;
    }

    public void setGeorgianName(String georgianName) {
        this.georgianName = georgianName;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<SemesterUniversity> getSemesterUniversities() {
        return semesterUniversities;
    }

    public void setSemesterUniversities(List<SemesterUniversity> semesterUniversities) {
        this.semesterUniversities = semesterUniversities;
    }

    //</editor-fold>
}
