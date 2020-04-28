package ge.gov.tsu.studentmanagement.entity;


import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;

@Entity
@Table(name = "SETTINGS")
public class Setting {
  /*  public enum Target{
        CLIENT_USER(1),
        CLIENT_ADMIN(2),
        MSDA_ADMIN(3);


        private final int value;

        Target(int value) {
            this.value = value;
        }

        public int getValue(){
            return value;
        }

        public String toString(){
            switch (value){
                case 1: return "CLIENT_USER";
                case 2: return "CLIENT";
                case 3: return "GLOBAL";
                default: return null;
            }
        }
    }*/

    @Id
    @SequenceGenerator(name = "SEQ_SETTINGS", sequenceName = "SEQ_SETTINGS", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SETTINGS")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "VALUE_TYPE", nullable = false)
    private Integer valueType;

    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;

    //<editor-fold desc="SETTING DESTINATION">
    /**
     * ვისთვის არის განკუთვნილი ეს სეთინგი.
     * შეიძლება რამდენიმესთვის იყოს
     * მაგ.: კლიენტისთვისაც და მომხმარებლისთვისაც
     */
    @Column(name = "FOR_APP")
    private Boolean forApplication;

    @Column(name = "FOR_CLIENT")
    private Boolean forClient;

    @Column(name = "FOR_USER")
    private Boolean forUser;
    //</editor-fold>

    @Column(name = "META",length = 4000)
    private String meta;

    /*-------------------------------------*/

    public static class ValueType {
        public static final int INTEGER = 0;
        public static final int DECIMAL = 1;
        public static final int STRING = 2;
        public static final int DATE = 3;
        public static final int BOOLEAN = 4;
    }

    public static void validateValue(String value, int valueType) {
        try {
            switch (valueType) {
                case ValueType.INTEGER:
                    Integer.valueOf(value);
                    return;
                case ValueType.DECIMAL:
                    Double.valueOf(value);
                    return;
                case ValueType.DATE:
                    new Date(Long.valueOf(value));
                    return;
                case ValueType.BOOLEAN:
                    new Boolean(value);
                    return;
                case ValueType.STRING:
                    return;
                default:
                    throw new ParseException("Unknown value type", 0);
            }
        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
           // throw ErrorService.initializeErrorResponse("INCONVERTIBLE_VALUE", e);
        }
    }

    /*-------------------------------------*/

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

    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getForApplication() {
        return forApplication;
    }

    public void setForApplication(Boolean forApplication) {
        this.forApplication = forApplication;
    }

    public Boolean getForClient() {
        return forClient;
    }

    public void setForClient(Boolean forClient) {
        this.forClient = forClient;
    }

    public Boolean getForUser() {
        return forUser;
    }

    public void setForUser(Boolean forUser) {
        this.forUser = forUser;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }
}
