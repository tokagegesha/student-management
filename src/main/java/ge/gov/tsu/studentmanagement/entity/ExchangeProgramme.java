package ge.gov.tsu.studentmanagement.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EXCHANGE_PROGRAMMES")
public class ExchangeProgramme {

    @Column(name = "ID", nullable = false)
    @Id
    @SequenceGenerator(name = "SEQ_EXCHANGE_PROGRAMMES", sequenceName = "SEQ_EXCHANGE_PROGRAMMES", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EXCHANGE_PROGRAMMES")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "ORDER_NUMBER")
    private Integer orderNumber;


    @Column( nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
