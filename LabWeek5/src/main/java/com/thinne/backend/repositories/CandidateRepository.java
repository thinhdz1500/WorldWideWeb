package com.thinne.backend.repositories;

import com.thinne.backend.models.Candidate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends PagingAndSortingRepository<Candidate, Long> {
    void save(Candidate candidate);
//    @Query("SELECT c FROM Candidate c")
    List<Candidate> findAll();
}
