package com.thinne.spring_web_page.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Skill {
    private int id;
    private String name;
    private String description;
    private String field;
}
