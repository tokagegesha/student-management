package ge.gov.tsu.studentmanagement.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PROGRAMMES")
public class Programme {

    @Column(name = "ID", nullable = false)
    @Id
    @SequenceGenerator(name = "SEQ_PROGRAMMES", sequenceName = "SEQ_PROGRAMMES", allocationSize = 1,initialValue = 200)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROGRAMMES")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column( nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "DEGREE")
    private Long degree;

    @Column(name = "ORDER_NUMBER")
    private Integer orderNumber;

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

    public Long getDegree() {
        return degree;
    }

    public void setDegree(Long degree) {
        this.degree = degree;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    //</editor-fold>
}
