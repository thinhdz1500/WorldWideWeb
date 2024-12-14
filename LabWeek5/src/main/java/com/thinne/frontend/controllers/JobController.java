package com.thinne.frontend.controllers;

import com.thinne.backend.models.Candidate;
import com.thinne.backend.models.Job;
import com.thinne.backend.repositories.CandidateRepository;
import com.thinne.backend.repositories.JobRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;
    private CandidateRepository candidateRepository;
    @GetMapping("/{id}")
    public String showJobDetail(@PathVariable Long id, Model model, HttpSession session) {
        Candidate candidate = (Candidate) session.getAttribute("candidate");
        if (candidate == null) {
            return "redirect:/candidate/login";
        }
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        model.addAttribute("job", job);
        model.addAttribute("candidate", candidate);
        return "job/job-detail";
    }
}