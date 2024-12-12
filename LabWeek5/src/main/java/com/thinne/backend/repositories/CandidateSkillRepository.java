package com.thinne.backend.repositories;

import com.thinne.backend.models.CandidateSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill,Long> {
    List<CandidateSkill> getCandidateSkillByCandidate_Id(Long id);
    @Query("select s1_0 \n" +
            "from CandidateSkill s1_0 \n" +
            "where s1_0.skill.id in :skillIds")
    List<CandidateSkill> findCandidatesBySkills(@Param("skillIds") List<Long> skillIds);

    @Query("SELECT DISTINCT cs.candidate.id FROM CandidateSkill cs WHERE cs.skill.id IN :skillIds")
    List<Long> findCandidateIdsBySkills(@Param("skillIds") List<Long> skillIds);

    @Query("SELECT cs FROM CandidateSkill cs WHERE cs.candidate.id IN :candidateIds")
    List<CandidateSkill> findCandidateSkillsByCandidateIds(@Param("candidateIds") List<Long> candidateIds);

    @Query("SELECT cs FROM CandidateSkill cs WHERE cs.candidate.id = :candidateId AND cs.skill.id = :skillId")
    Optional<CandidateSkill> findByCandidateIdAndSkillId(@Param("candidateId") Long candidateId, @Param("skillId") Long skillId);
}
