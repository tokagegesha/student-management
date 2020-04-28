package ge.gov.tsu.studentmanagement.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "APP_SETTINGS")
@Where(clause = "ROW_ID IS NULL")
public class AppSetting {

    @Id
    @SequenceGenerator(name = "SEQ_APP_SETTINGS", sequenceName = "SEQ_APP_SETTINGS", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APP_SETTINGS")
    private Long id;

    //@Lob
    @Column(name = "SETTING_VALUE", columnDefinition = "text")
    private String value;

    @Column(name = "SETTING_KEYWORD")
    private String keyword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
