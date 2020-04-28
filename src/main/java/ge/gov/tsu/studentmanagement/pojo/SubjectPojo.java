package ge.gov.tsu.studentmanagement.pojo;

import ge.gov.tsu.studentmanagement.entity.statics.LanguageType;

public class SubjectPojo {
    private Long id;
    private String name;
    private Integer credits;
    private Long executiveUserId;
    private LanguageType language;

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

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Long getExecutiveUserId() {
        return executiveUserId;
    }

    public void setExecutiveUserId(Long executiveUserId) {
        this.executiveUserId = executiveUserId;
    }

    public LanguageType getLanguage() {
        return language;
    }

    public void setLanguage(LanguageType language) {
        this.language = language;
    }
}
