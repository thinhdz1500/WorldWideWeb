package com.thinne.frontend.models;

import com.thinne.backend.models.Candidate;
import com.thinne.backend.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CandidateModel {
    @Autowired
    private CandidateService candidateService;

    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        return candidateService.findAll(pageNo, pageSize, sortBy, sortDir);
    }
    public List<Candidate> findAll() {
        return candidateService.findAll();
    }
}
