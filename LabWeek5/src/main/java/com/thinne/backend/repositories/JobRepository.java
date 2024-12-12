package com.thinne.backend.repositories;

import com.thinne.backend.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT DISTINCT j FROM Job j JOIN j.skills s WHERE s IN " +
            "(SELECT s FROM CandidateSkill cs join cs.skill s WHERE cs.candidate.id = :candidateId)")
    List<Job> findRecommendedJobsForCandidate(@Param("candidateId") Long candidateId);
    @Query("SELECT j FROM Job j WHERE " +
            "(:keyword IS NULL OR LOWER(j.jobName) LIKE LOWER(CONCAT('%', :keyword, '%'))) ")
    List<Job> searchJobs(@Param("keyword") String keyword);
}
