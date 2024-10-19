package com.thinne.spring_web_page.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Candidate_Skill {
    private int candidateId;
    private int skillId;
    private int level;
}
