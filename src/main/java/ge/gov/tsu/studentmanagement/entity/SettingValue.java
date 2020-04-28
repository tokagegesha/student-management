package ge.gov.tsu.studentmanagement.entity;


import javax.persistence.*;

@Entity
@Table(name = "SETTING_VALUES")
public class SettingValue  {

    private Long recordId;

    @Id
    @SequenceGenerator(name = "SEQ_SETTING_VALUES", sequenceName = "SEQ_SETTING_VALUES", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SETTING_VALUES")
    @Column(name = "ID")
    private Long id;

    @Column(name = "SETTING_ID")
    private Long settingId;

    @Column(name = "CLIENT_ID")
    private Long clientId;

    @Column(name = "USER_ID")
    private Long userId;

    @Lob
    @Column(name = "VALUE")
    private String value;

    /*-------------------------------------*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSettingId() {
        return settingId;
    }

    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
