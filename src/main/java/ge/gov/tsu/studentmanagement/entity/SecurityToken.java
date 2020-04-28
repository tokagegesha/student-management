package ge.gov.tsu.studentmanagement.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SECURITY_TOKENS")
public class SecurityToken {

    public static class Status {
        public static final int ACTIVE = 1;
        public static final int USED = -1;
        public static final int EXPIRED = -2;
        public static final int OVERRIDDEN = -3;

        public static String getStringValue(int intValue){
            switch (intValue){
                case 1: return "ACTIVE";
                case -1: return "USED";
                case -2: return "EXPIRED";
                case -3: return "OVERRIDDEN";
                default: return "NO_STATUS_NAME";
            }
        }
    }
    public enum  ActionType {
        EMAIL_VERIFICATION(1),
        PHONE_VERIFICATION(2),
        PASSWORD_RESET(3),
        CLIENT_USER_ADD_VERIFICATION(4);


        private final int value;

        ActionType(int value) {
            this.value = value;
        }

        public int getValue(){
            return value;
        }

        /*public static final int PASSWORD_RESET = -1;
        public static final int EXPIRED = -2;*/
    }

    @Id
    @SequenceGenerator(name = "SEQ_SECURITY_TOKENS", sequenceName = "SEQ_SECURITY_TOKENS", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SECURITY_TOKENS")
    private Long id;

    @Column(name = "STATUS", nullable = false)
    private Integer status;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "TARGET")
    private String target;

    @Column(name = "TOKEN", nullable = false)
    private String token;

    @Column(name = "ACTION_TYPE", nullable = false)
    private ActionType actionType;

    @Column(name = "CREATION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "EXPIRATION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    @Column(name = "USAGE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usageDate;

    /*--------------------------*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(Date usageDate) {
        this.usageDate = usageDate;
    }
}
