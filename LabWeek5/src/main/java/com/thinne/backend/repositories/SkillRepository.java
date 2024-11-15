package com.thinne.backend.repositories;

import com.thinne.backend.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {
    Skill findSkillById(Long id);
    @Query("SELECT s FROM CandidateSkill cs join cs.skill s join cs.candidate c WHERE c.id = :candidateId")
    List<Skill> findSkillsByCandidate(@Param("candidateId") Long candidateId);

    @Query("SELECT DISTINCT s FROM Skill s WHERE s NOT IN " +
            "(SELECT cs.skill FROM CandidateSkill cs WHERE cs.candidate.id = :candidateId) " +
            "AND s IN (SELECT js.skill FROM JobSkill js)")
    List<Skill> findRecommendedSkillsForCandidate(@Param("candidateId") Long candidateId);

}
