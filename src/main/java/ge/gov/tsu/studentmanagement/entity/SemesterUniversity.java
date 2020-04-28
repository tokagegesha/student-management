package ge.gov.tsu.studentmanagement.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SEMESTER_UNIVERSITIES")
public class SemesterUniversity {
    @Id
    @SequenceGenerator(name = "SEQ_SEMESTER_UNIVERSITIES", sequenceName = "SEQ_SEMESTER_UNIVERSITIES", allocationSize = 1, initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SEMESTER_UNIVERSITIES")
    @Column(name = "ID", nullable = false)
    private Long id;

    @JsonManagedReference
    @ManyToOne
    private University university;

    @ManyToOne
    private Semester semester;


    @Column(name = "VISIBLE")
    private Boolean visible;

    @Column(name = "STATUS", nullable = false)
    private Integer status;


    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    //<editor-fold desc="GETTERS AND SETTERS">

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    //</editor-fold>
}
