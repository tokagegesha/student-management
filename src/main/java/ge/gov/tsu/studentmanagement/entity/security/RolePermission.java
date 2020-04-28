package ge.gov.tsu.studentmanagement.entity.security;


import javax.persistence.*;

@Entity
@Table(name = "ROLE_PERMISSIONS")
public class RolePermission {

    @Id
    @SequenceGenerator(name = "SEQ_ROLE_PERMISSIONS", sequenceName = "SEQ_ROLE_PERMISSIONS", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROLE_PERMISSIONS")
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERMISSION_ID")
    private Permission permission;

    @Column(name = "ACCESS_TYPE")
    private Integer access;

    //<editor-fold desc="GETTERS AND SETTERS">

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }
    //</editor-fold>
}
