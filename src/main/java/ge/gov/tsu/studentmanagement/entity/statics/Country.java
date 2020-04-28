package ge.gov.tsu.studentmanagement.entity.statics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRIES")
public class Country {
    @Id
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NAME_GE")
    private String georgianName;

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

    public String getGeorgianName() {
        return georgianName;
    }

    public void setGeorgianName(String georgianName) {
        this.georgianName = georgianName;
    }
}
