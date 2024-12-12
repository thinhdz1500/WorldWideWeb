package com.thinne.frontend.models;

import com.thinne.backend.models.Job;
import com.thinne.backend.models.JobSkill;
import com.thinne.backend.models.Skill;
import com.thinne.backend.repositories.JobRepository;
import com.thinne.backend.repositories.JobSkillRepository;
import com.thinne.backend.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobModel {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private JobRepository jobRepository;

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(Long id) {
        return skillRepository.findSkillById(id);
    }

    public void addJobSkill(JobSkill jobSkill) {
        jobSkillRepository.save(jobSkill);
    }

    public void addJob(Job job) {
        jobRepository.save(job);
    }

    public List<Job> getRecommendedJobs(Long candidateId) {
        return jobRepository.findRecommendedJobsForCandidate(candidateId);
    }
    public List<Job> searchJobs(String keyword) {
        if (keyword == null) {
            return jobRepository.findAll();
        }
        return jobRepository.searchJobs(keyword);
    }
}
