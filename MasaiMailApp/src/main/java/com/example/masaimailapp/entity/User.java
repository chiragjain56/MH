package com.example.masaimailapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    private String email;
    private String firstName;
    private String lastName;
    private int mobileNumber;
    private Date dateOfBirth;

    @OneToMany
    List<Email> emails = new ArrayList<>();
}
