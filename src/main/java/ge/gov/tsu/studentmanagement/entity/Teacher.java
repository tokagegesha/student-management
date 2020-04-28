package ge.gov.tsu.studentmanagement.entity;


import javax.persistence.*;

@Entity
@Table(name = "TEACHERS")
public class Teacher  {
    @Id
    @SequenceGenerator(name = "SEQ_TEACHERS", sequenceName = "SEQ_TEACHERS", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEACHERS")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;


    //<editor-fold desc="GETTERS AND SETTERS">


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




    //</editor-fold>
}
