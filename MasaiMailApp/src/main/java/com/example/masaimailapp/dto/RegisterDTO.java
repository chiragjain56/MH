package com.example.masaimailapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    private String email;
    private String firstName;
    private String lastName;
    private int mobileNumber;
    private Date dateOfBirth;
    private String password;
}
