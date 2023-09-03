package gr.aueb.cf.schoolapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TeacherInsertDTO {
    @NotBlank(message = "Please fill in the firstname")
    @Size(min = 3 , max = 50, message = "firstname length must be between 3-20 chars")
    private String firstname;
    @NotBlank(message = "Please fill in the lastname")
    @Size(min = 3 , max = 50, message = "lastname length must be between 3-20 chars")
    private String lastname;


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
