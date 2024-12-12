package com.thinne.frontend.models;
import com.thinne.backend.models.CandidateSkill;
import com.thinne.backend.models.Skill;
import com.thinne.backend.models.SkillLevel;
import com.thinne.backend.repositories.CandidateSkillRepository;
import com.thinne.backend.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillModel {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    public List<Skill> getCandidateSkills(Long candidateId) {
        return skillRepository.findSkillsByCandidate(candidateId);
    }

    public List<Skill> getRecommendedSkills(Long candidateId) {
        return skillRepository.findRecommendedSkillsForCandidate(candidateId);
    }
    public void updateCandidateSkill(Long candidateId, Long skillId, String skillLevel) {
        CandidateSkill candidateSkill = candidateSkillRepository
                .findByCandidateIdAndSkillId(candidateId, skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        candidateSkill.setSkillLevel(SkillLevel.valueOf(skillLevel));
        candidateSkillRepository.save(candidateSkill);
    }

    public List<Skill> getAvailableSkills() {
        return skillRepository.findAll();
    }
}