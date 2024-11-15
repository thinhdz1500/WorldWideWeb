package com.thinne.backend.repositories;

import com.thinne.backend.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {
    Skill findSkillById(Long id);
    @Query("select cs from CandidateSkill cs  where cs.candidate.id = :id")
    List<Skill> findSkillsBy(Long id);
}
