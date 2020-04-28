package ge.gov.tsu.studentmanagement.entity;


import javax.persistence.*;

@Entity
@Table(name = "STUDENT_GROUPS")
public class StudentGroup{

    @Id
    @SequenceGenerator(name = "SEQ_STUDENT_GROUPS", sequenceName = "SEQ_STUDENT_GROUPS", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDENT_GROUPS")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "GROUP_ID")
    private Long groupId;

    @Column(name = "STUDENT_ID")
    private Long studentID;


    //<editor-fold desc="GETTERS AND SETTERS">


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
//</editor-fold>
}
