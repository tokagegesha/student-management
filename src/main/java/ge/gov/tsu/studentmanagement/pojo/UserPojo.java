package ge.gov.tsu.studentmanagement.pojo;

import ge.gov.tsu.studentmanagement.apiutils.annotations.Required;

public class UserPojo {
    @Required(on="edit")
    private Long id;

    @Required(on="edit")
    private String firstName;

    @Required(on="edit")
    private String lastName;

    private String password;

    @Required(on="edit")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
