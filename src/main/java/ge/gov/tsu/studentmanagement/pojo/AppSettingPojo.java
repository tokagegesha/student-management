package ge.gov.tsu.studentmanagement.pojo;

import ge.gov.tsu.studentmanagement.apiutils.annotations.Required;

public class AppSettingPojo {
    private Long id;
    @Required(on = "edit")

    private String keyword;
    @Required(on = "edit")
    private String value;

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
