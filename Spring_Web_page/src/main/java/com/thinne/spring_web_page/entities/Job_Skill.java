package com.thinne.spring_web_page.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Job_Skill {
    private int jobId;
    private int skillId;
    private int level;
}
