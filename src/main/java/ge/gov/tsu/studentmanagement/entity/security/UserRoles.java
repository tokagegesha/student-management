package ge.gov.tsu.studentmanagement.entity.security;


import javax.persistence.*;

@Entity
@Table(name = "USER_ROLES")
public class UserRoles  {
    @Id
    @SequenceGenerator(name = "SEQ_USER_ROLES", sequenceName = "SEQ_USER_ROLES", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_ROLES")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column()
    private Long userId;

    @Column(insertable = false, name = "ROLE_ID", updatable=false)
    private Long roleId;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;


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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
//</editor-fold>
}
