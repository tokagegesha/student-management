package ge.gov.tsu.studentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ge.gov.tsu.studentmanagement.config.TsuGrantedAuthority;
import ge.gov.tsu.studentmanagement.entity.security.Role;
import ge.gov.tsu.studentmanagement.entity.security.UserRoles;
import ge.gov.tsu.studentmanagement.pojo.session.Executor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User implements UserDetails {

    public enum Type {
        ACTIVE(1),
        VERIFIED(2),
        ACCEPTED_STUDENT(3),
        BLOCKED(4);
        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    @Id
    @SequenceGenerator(name = "SEQ_USERS", sequenceName = "SEQ_USERS", allocationSize = 1, initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERS")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "STATUS")
    private Integer status;

    @JsonIgnore
    @Transient
    private Executor executor;

    @JsonIgnore
    @Transient
    private boolean accountNonExpired;

    @JsonIgnore
    @Transient
    private boolean accountNonLocked;

    @JsonIgnore
    @Transient
    private boolean credentialsNonExpired;

    @JsonIgnore
    @Transient
    private boolean enabled;

    @Transient
    private Collection<GrantedAuthority> authorities;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserRoles userRoles;

    //<editor-fold desc="GETTERS AND SETTERS">

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public UserRoles getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRoles userRoles) {
        this.userRoles = userRoles;
    }

    //</editor-fold>
}
