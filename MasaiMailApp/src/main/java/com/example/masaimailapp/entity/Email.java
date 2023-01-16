package com.example.masaimailapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Email {

    @Id
    @GeneratedValue
    private Long id;
    private String to;
    private String from;
    private String subject;
    private boolean starred = false;
}
