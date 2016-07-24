package com.apress.bookstore.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class RegistrationUserFormDTO {

    @NotEmpty
    @Size(min = 3, max = 30)
    @Setter
    @Getter
    private String name;

//    @NotEmpty
//    @Email
//    private String email;
//
//    @Min(18)
//    private Integer age;

    @NotEmpty
    @Size(min = 4, max = 40)
    @Setter
    @Getter
    private String password;

}
