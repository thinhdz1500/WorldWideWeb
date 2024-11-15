package com.thinne.frontend.models;
import com.thinne.backend.models.Skill;
import com.thinne.backend.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillModel {
    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getCandidateSkills(Long candidateId) {
        return skillRepository.findSkillsByCandidate(candidateId);
    }

    public List<Skill> getRecommendedSkills(Long candidateId) {
        return skillRepository.findRecommendedSkillsForCandidate(candidateId);
    }
}