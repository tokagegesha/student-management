package ge.gov.tsu.studentmanagement.entity;


import javax.persistence.*;

@Entity
@Table(name = "GROUPS")
public class Group  {

    @Column(name = "ID", nullable = false)
    @Id
    @SequenceGenerator(name = "SEQ_GROUPS", sequenceName = "SEQ_GROUPS", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GROUPS")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MAX_STUDENT")
    private Integer maxStudent;

    @Column(name = "TYPE")
    private Integer type;


    //<editor-fold desc="GETTERS AND SETTERS">


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(Integer maxStudent) {
        this.maxStudent = maxStudent;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    //</editor-fold>
}
