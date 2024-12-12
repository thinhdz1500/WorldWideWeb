package com.thinne.frontend.models;
import com.thinne.backend.models.*;
import com.thinne.backend.repositories.CandidateRepository;
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
    @Autowired
    private CandidateRepository candidateRepository;

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

    public void addCandidateSkill(Long candidateId, Long skillId, SkillLevel skillLevel) {
        Candidate candidate = candidateRepository.findById(candidateId);
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        CandidateSkill candidateSkill = new CandidateSkill();
        candidateSkill.setId(new CandidateSkillId(candidateId, skillId));
        candidateSkill.setCandidate(candidate);
        candidateSkill.setSkill(skill);
        candidateSkill.setSkillLevel(skillLevel);

        candidateSkillRepository.save(candidateSkill);
    }

    public void addNewSkill(Long candidateId, String skillName, String skillDescription, SkillType skillType, SkillLevel skillLevel) {
        Candidate candidate = candidateRepository.findById(candidateId);
        Skill newSkill = new Skill();
        newSkill.setSkillName(skillName);
        newSkill.setSkillDescription(skillDescription);
        newSkill.setType(skillType);
        skillRepository.save(newSkill);

        CandidateSkill candidateSkill = new CandidateSkill();
        candidateSkill.setId(new CandidateSkillId(candidateId, newSkill.getId()));
        candidateSkill.setCandidate(candidate);
        candidateSkill.setSkill(newSkill);
        candidateSkill.setSkillLevel(skillLevel);

        candidateSkillRepository.save(candidateSkill);
    }
}