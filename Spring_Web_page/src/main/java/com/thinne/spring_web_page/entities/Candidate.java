package com.thinne.spring_web_page.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Candidate {
    private int id;
    private String fullName;
    private LocalDate dob;
    private String address;
    private String phone;
    private String email;
}
