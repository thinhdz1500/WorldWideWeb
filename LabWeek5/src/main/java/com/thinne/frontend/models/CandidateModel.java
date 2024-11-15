package com.thinne.frontend.models;

import com.thinne.backend.models.Candidate;
import com.thinne.backend.models.CandidateSkill;
import com.thinne.backend.repositories.CandidateRepository;
import com.thinne.backend.repositories.CandidateSkillRepository;
import com.thinne.backend.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CandidateModel {
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;
    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        return candidateService.findAll(pageNo, pageSize, sortBy, sortDir);
    }
    public List<Candidate> findAll() {
        return candidateService.findAll();
    }
    public Candidate findByEmail(String email) {
        return candidateService.findByEmail(email);
    }
    public List<CandidateSkill> getCandidateSkillByCandidate_Id(Long id) {
        return candidateSkillRepository.getCandidateSkillByCandidate_Id(id);
    }
    public List<CandidateSkill> findCandidateBySkills(List<Long> skillIds) {
        return candidateSkillRepository.findCandidatesBySkills(skillIds);
    }
    public Map<Long, List<CandidateSkill>> findCandidatesBySkills(List<Long> skillIds) {
        List<Long> candidateIds = candidateSkillRepository.findCandidateIdsBySkills(skillIds);
        List<CandidateSkill> allCandidateSkills = candidateSkillRepository.findCandidateSkillsByCandidateIds(candidateIds);

        return allCandidateSkills.stream()
                .collect(Collectors.groupingBy(cs -> cs.getCandidate().getId()));
    }
}
