package ge.gov.tsu.studentmanagement.entity.security;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_ROLES", sequenceName = "SEQ_ROLES", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROLES")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany
    private List<Permission> permissions = new ArrayList<>();

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
}