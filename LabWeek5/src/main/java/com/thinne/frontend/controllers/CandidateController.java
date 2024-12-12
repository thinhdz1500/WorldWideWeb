package com.thinne.frontend.controllers;

import com.thinne.backend.models.*;
import com.thinne.backend.repositories.CandidateSkillRepository;
import com.thinne.backend.repositories.SkillRepository;
import com.thinne.frontend.models.CandidateModel;
import com.thinne.frontend.models.JobModel;
import com.thinne.frontend.models.SkillModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
public class CandidateController {
    @Autowired
    private CandidateModel candidateModel;
    @Autowired
    private JobModel jobModel;

        @Autowired
    private SkillModel skillModel;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;
    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("/")
    public ModelAndView showIndex() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping("/candidates")
    public ModelAndView showCandidateListPaging(@RequestParam("page") Optional<Integer> pageNo, @RequestParam("size") Optional<Integer> pageSize) {
        ModelAndView mav = new ModelAndView("candidate/candidate-paging");
        int currentPage = pageNo.orElse(1);
        int currentPageSize = pageSize.orElse(10);

        Page<Candidate> candidatePage = candidateModel.findAll(currentPage - 1, currentPageSize, "id", "asc");

        mav.addObject("candidatePage", candidatePage);
        int totalPages = candidatePage.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();

            mav.addObject("pageNumbers", pageNumbers);
        }

        return mav;
    }

    @RequestMapping("/candidate/login")
    public ModelAndView showLogin() {
        ModelAndView mav = new ModelAndView("candidate/candidate-login");
        return mav;
    }

    @RequestMapping(value = "/candidate/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        Candidate candidate = candidateModel.findByEmail(email);
        if (candidate != null && candidate.getPassword().equals(password)) {
            mav.setViewName("redirect:/candidate/candidate-dashboard");
            mav.addObject("candidate", candidate);
            session.setAttribute("candidate", candidate);
        }
        else {
            mav.setViewName("candidate/candidate-login");
            mav.addObject("message", "Incorrect account or password!");
        }
        return mav;
    }



    public void updateCandidateSkill(Long candidateId, Long skillId, String skillLevel) {
        CandidateSkill candidateSkill = candidateSkillRepository
                .findByCandidateIdAndSkillId(candidateId, skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        candidateSkill.setSkillLevel(SkillLevel.valueOf(skillLevel));
        candidateSkillRepository.save(candidateSkill);
    }

    public List<Skill> getAvailableSkills() {
        return skillRepository.findAll();
    }
    @RequestMapping(value = "/candidate/candidate-dashboard")
    public ModelAndView showCandidateDashboard(HttpSession session) {
        Candidate candidate = (Candidate) session.getAttribute("candidate");
        if (candidate == null) {
            return new ModelAndView("redirect:/candidate/login");
        }

        ModelAndView mav = new ModelAndView("candidate/candidate-dashboard");
        mav.addObject("candidate", candidate);

        List<Job> recommendedJobs = jobModel.getRecommendedJobs(candidate.getId());
        mav.addObject("recommendedJobs", recommendedJobs);

        List<Skill> candidateSkills = skillModel.getCandidateSkills(candidate.getId());
        mav.addObject("candidateSkills", candidateSkills);

        List<Skill> recommendedSkills = skillModel.getRecommendedSkills(candidate.getId());
        mav.addObject("recommendedSkills", recommendedSkills);

        return mav;
    }

    @PostMapping("/candidate/skills/update")
    public String updateSkill(@RequestParam Long skillId,
                              @RequestParam String skillLevel,
                              HttpSession session) {
        Candidate candidate = (Candidate) session.getAttribute("candidate");
        skillModel.updateCandidateSkill(candidate.getId(), skillId, skillLevel);
        return "redirect:/candidate/candidate-dashboard";
    }

    @GetMapping("/candidate/skills/add")
    public String showAddSkillPage(Model model) {
        model.addAttribute("availableSkills", skillModel.getAvailableSkills());
        return "candidate/add-skill";
    }

    @GetMapping("/jobs/search")
    public String searchJobs(@RequestParam(required = false) String keyword,
                             Model model) {
        model.addAttribute("jobs", jobModel.searchJobs(keyword));
        return "candidate/job-search";
    }
}
