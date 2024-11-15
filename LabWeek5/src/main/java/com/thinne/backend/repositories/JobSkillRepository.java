package com.thinne.backend.repositories;

import com.thinne.backend.models.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill,Long> {
}
