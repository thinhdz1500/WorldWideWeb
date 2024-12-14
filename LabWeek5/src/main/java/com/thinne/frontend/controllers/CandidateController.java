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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
        } else {
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
    public ModelAndView showCandidateDashboard(HttpSession session, @RequestParam(required = false) String keyword) {
        Candidate candidate = (Candidate) session.getAttribute("candidate");
        if (candidate == null) {
            return new ModelAndView("redirect:/candidate/login");
        }

        ModelAndView mav = new ModelAndView("candidate/candidate-dashboard");
        mav.addObject("candidate", candidate);

        List<Job> recommendedJobs = jobModel.getRecommendedJobs(candidate.getId());
        mav.addObject("recommendedJobs", recommendedJobs);

        List<CandidateSkill> candidateSkills = candidateSkillRepository.getCandidateSkillByCandidate_Id(candidate.getId());
        mav.addObject("candidateSkills", candidateSkills);

        List<Skill> recommendedSkills = skillModel.getRecommendedSkills(candidate.getId());
        mav.addObject("recommendedSkills", recommendedSkills);

        // Add job search functionality
        List<Job> searchResults = null;
        if (keyword != null && !keyword.isEmpty()) {
            searchResults = jobModel.searchJobs(keyword);
        }
        mav.addObject("searchResults", searchResults);
        mav.addObject("keyword", keyword);

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

    @GetMapping("/jobs/search")
    public String searchJobs(@RequestParam(required = false) String keyword,
                             Model model) {
        model.addAttribute("jobs", jobModel.searchJobs(keyword));
        return "candidate/job-search";
    }

    @PostMapping("/candidate/skills/add")
    public String addSkill(@RequestParam(required = false) Long skillId,
                           @RequestParam(required = false) String skillName,
                           @RequestParam(required = false) String skillDescription,
                           @RequestParam(required = false) SkillType newSkillType,
                           @RequestParam SkillLevel skillLevel,
                           HttpSession session) {
        Candidate candidate = (Candidate) session.getAttribute("candidate");

        if (skillId != null) {
            skillModel.addCandidateSkill(candidate.getId(), skillId, skillLevel);
        } else if (skillName != null && !skillName.isEmpty()) {
            skillModel.addNewSkill(candidate.getId(), skillName, skillDescription, newSkillType, skillLevel);
        }

        return "redirect:/candidate/candidate-dashboard";
    }

    //profile
    @GetMapping("/candidate/profile")
    public String showProfile(Model model, HttpSession session) {
        Candidate candidate = (Candidate) session.getAttribute("candidate");
        if (candidate == null) {
            return "redirect:/candidate/login";
        }
        model.addAttribute("candidate", candidate);
        List<CandidateSkill> candidateSkills = candidateSkillRepository.getCandidateSkillByCandidate_Id(candidate.getId());
        model.addAttribute("candidateSkills", candidateSkills);
        return "candidate/candidate-profile";
    }

//    @PostMapping("/update-profile")
//    @ResponseBody
//    public ResponseEntity<?> updateProfile(@RequestParam Map<String, String> params, HttpSession session) {
//        Candidate candidate = (Candidate) session.getAttribute("candidate");
//        if (candidate == null) {
//            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Not logged in"));
//        }
//
//        candidate.setFullName(params.get("fullName"));
//        candidate.setEmail(params.get("email"));
//        candidate.setPhone(params.get("phone"));
//        candidate.setDob(LocalDate.parse(params.get("dob")));
//
//        Address address = candidate.getAddress();
//        if (address == null) {
//            address = new Address();
//            candidate.setAddress(address);
//        }
//        address.setStreet(params.get("address"));
//
//        candidateModel.updateCandidate(candidate);
//        session.setAttribute("candidate", candidate);
//
//        return ResponseEntity.ok(Map.of("success", true));
//    }

    @PostMapping("/add-skill")
    @ResponseBody
    public ResponseEntity<?> addSkill(@RequestParam String skillName, @RequestParam SkillLevel skillLevel, HttpSession session) {
        Candidate candidate = (Candidate) session.getAttribute("candidate");
        if (candidate == null) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Not logged in"));
        }

        skillModel.addCandidateSkill(candidate.getId(), skillName, skillLevel);
        return ResponseEntity.ok(Map.of("success", true));
    }

    @PostMapping("/remove-skill/{skillId}")
    @ResponseBody
    public ResponseEntity<?> removeSkill(@PathVariable Long skillId, HttpSession session) {
        Candidate candidate = (Candidate) session.getAttribute("candidate");
        if (candidate == null) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Not logged in"));
        }

        skillModel.removeCandidateSkill(candidate.getId(), skillId);
        return ResponseEntity.ok(Map.of("success", true));
    }

    @PostMapping("/update-skill-level/{skillId}")
    @ResponseBody
    public ResponseEntity<?> updateSkillLevel(@PathVariable Long skillId, @RequestParam SkillLevel skillLevel, HttpSession session) {
        Candidate candidate = (Candidate) session.getAttribute("candidate");
        if (candidate == null) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Not logged in"));
        }

        skillModel.updateCandidateSkillLevel(candidate.getId(), skillId, skillLevel);
        return ResponseEntity.ok(Map.of("success", true));
    }
}