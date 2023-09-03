package gr.aueb.cf.schoolapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TeacherReadOnlyDTO extends BaseDTO {

    private String firstname;

    private String lastname;

    public TeacherReadOnlyDTO(Long id, String firstname, String lastname) {
        this.setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public TeacherReadOnlyDTO() {

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
