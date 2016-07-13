package com.apress.bookstore.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class RegistrationUserFormDTO {

    @NotEmpty
    @Size(min = 3, max = 30)
    private String name;

//    @NotEmpty
//    @Email
//    private String email;
//
//    @Min(18)
//    private Integer age;

    @NotEmpty
    @Size(min = 4, max = 40)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
