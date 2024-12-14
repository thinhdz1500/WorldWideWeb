package com.thinne.frontend.controllers;

import com.thinne.backend.models.*;
import com.thinne.backend.services.EmailService;
import com.thinne.frontend.models.CandidateModel;
import com.thinne.frontend.models.CompanyModel;
import com.thinne.frontend.models.JobModel;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyModel companyModel;
    @Autowired
    private JobModel jobModel;
    @Autowired
    private CandidateModel candidateModel;
    @Autowired
    private EmailService emailService;
    @RequestMapping("")
    public String showCompanyDashboard(HttpSession session){
        Company company = (Company) session.getAttribute("company");
        if(company == null)
            return "company/company-login";
        return "company/company-dashboard";
    }
    @RequestMapping("/login")
    public String showLogin() {
        return "company/company-login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        Company company = companyModel.findCompanyByEmail(email);
        if (company != null) {
            if (company.getPassword().equals(password)) {
                mav.setViewName("company/company-dashboard");
                mav.addObject("company", company);
                session.setAttribute("company", company);
            } else {
                mav.setViewName("company/company-login");
                mav.addObject("message", "Invalid email or password");
            }
        } else {
            mav.setViewName("company/company-login");
            mav.addObject("message", "Invalid email or password");
        }
        return mav;
    }
    @RequestMapping(value = "/post-job")
    public String ShowPostJob(Model model, HttpSession session){
        model.addAttribute("skill",jobModel.getAllSkills());
        Company company = (Company) session.getAttribute("company");
        if(company==null){
            return "company/company-login";
        }
        model.addAttribute("company",company);
        return "company/post-job";
    }
    @RequestMapping(value = "/post-job", method = RequestMethod.POST)
    public ModelAndView addJob(@RequestParam("jobName") String jobName,
                               @RequestParam("jobDesc") String jobDesc,
                               @RequestParam("skills[]") List<String> skillIds,
                               @RequestParam("skillLevels[]") List<String> skillLevels,
                               @RequestParam("moreInfos[]") List<String> moreInfos,
                               HttpSession session) {
        ModelAndView mav = new ModelAndView();
        Company company = (Company) session.getAttribute("company");
        if (company == null) {
            mav.setViewName("company/company-login");
            mav.addObject("message", "Invalid email or password");
            return mav;
        }

        Job job = new Job();
        job.setCompany(company);
        job.setJobDesc(jobDesc);
        job.setJobName(jobName);

        // Thêm job trước khi thêm JobSkill để tránh lỗi
        jobModel.addJob(job);

        for (int i = 0; i < skillIds.size(); i++) {
            Skill skill = jobModel.getSkillById(Long.parseLong(skillIds.get(i)));
            JobSkill jobSkill = new JobSkill();
            JobSkillId jobSkillId = new JobSkillId();
            jobSkillId.setJobId(job.getId());
            jobSkillId.setSkillId(skill.getId());
            jobSkill.setId(jobSkillId);
            jobSkill.setJob(job);
            jobSkill.setSkill(skill);
            jobSkill.setSkillLevel(SkillLevel.valueOf(skillLevels.get(i)));
            jobSkill.setMoreInfos(moreInfos.get(i));
            jobModel.addJobSkill(jobSkill);
        }

        mav.setViewName("company/company-dashboard");
        mav.addObject("company", company);
        return mav;
    }

    @RequestMapping(value = "/company-search")
    public String showCompanySearch(Model model,HttpSession session){
        Company company = (Company) session.getAttribute("company");
        model.addAttribute("skill",jobModel.getAllSkills());
        if (company == null) {
            return "company/company-login";
        }
        else return "company/company-search";
    }
    @RequestMapping(value = "/company-search", method = RequestMethod.POST)
    public String searchCandidateBySkills(@RequestParam("skills") List<String> skillIds, Model model, HttpSession session) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) {
            return "company/company-login";
        }
        List<Long> skillIdsLong = skillIds.stream()
                .map(id -> Long.parseLong(id.replaceAll("[\\[\\]]", "")))
                .collect(Collectors.toList());

        Map<Long, List<CandidateSkill>> candidateSkillsMap = candidateModel.findCandidatesBySkills(skillIdsLong);

        model.addAttribute("candidateSkillsMap", candidateSkillsMap);
        model.addAttribute("skill", jobModel.getAllSkills());
        return "company/company-search";
    }
    @PostMapping("/send-invitation")
    @ResponseBody
    public ResponseEntity<?> sendInvitation(@RequestParam String candidateEmail,
                                            @RequestParam String subject,
                                            @RequestParam String message,
                                            HttpSession session) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) {
            return ResponseEntity.badRequest().body("Company not logged in");
        }

        try {
            Candidate candidate = candidateModel.findByEmail(candidateEmail);
            String fullMessage = "Dear "+candidate.getFullName()+",\n\n" + message + "\n\nBest regards,\n" + company.getCompName();
            emailService.sendInvitationEmail(candidateEmail, subject, fullMessage);
            return ResponseEntity.ok("Invitation sent successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.badRequest().body("Failed to send invitation: " + e.getMessage());
        }
    }
}
